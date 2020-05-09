#### 定时任务插件
```java 
public class QuartzTest2 {
    public static JobMethod jobMethod;

    @BeforeClass
    public static void before() throws Exception {
        SpringHolder.init();
        jobMethod = SpringHolder.getBean(JobMethod.class);
    }

    /**
     * 按设定的cron定时执行 ScheduleJobCron
     */
    @Test
    public void test1() throws InterruptedException {
        ScheduleJobCron scheduleJob = new ScheduleJobCron();
        //JobName为spring已经初始化的bean名
        scheduleJob.setJobName("test1");
        //指定运行的方法,可选。不添加该参数默认方法为run
        scheduleJob.setExecuteMethod("deal");
        //给运行的方法添加参数,可选。不添加该参数则执行无参方法
        scheduleJob.setPara(new JSONMap("id", 123));
        //定时cron必填
        scheduleJob.setCronExpression("0/1 * * * * ? *");
        jobMethod.addAndResetScheduleJob(scheduleJob);
        Thread.sleep(10000);
    }

    /**
     * 只运行一次的定时执行 ScheduleJobSimple
     */
    @Test
    public void test2() throws InterruptedException {
        ScheduleJobSimple scheduleJob = new ScheduleJobSimple();
        scheduleJob.setJobName("test1");
        //指定运行时间，可选，不添加该参数则立即执行
        scheduleJob.setStartTime(new Date(new Date().getTime() + 5000));
        jobMethod.addAndResetScheduleJob(scheduleJob);
        Thread.sleep(10000);
    }

    /**
     * 自定义执行类
     */
    @Test
    public void test3() throws InterruptedException {
        ScheduleJobSimple scheduleJob = new ScheduleJobSimple();
        //自己命名
        scheduleJob.setJobName("dealGroupBuy");
        //自定义类，不依赖spring初始化，可选。添加该参数则自定义初始化，不添加则使用spring初始化的类
        scheduleJob.setBeanClass(Test2.class.getName());
        scheduleJob.setPara(new JSONMap("id", 123));
        //指定的方法立即执行
        jobMethod.addAndResetScheduleJob(scheduleJob);
    }

	/**
	 * 自定义执行类
	 */
	@Test
	public void test4() throws InterruptedException {
		ScheduleJobCron scheduleJob = new ScheduleJobCron();
		//自己命名
		scheduleJob.setJobName("dealGroupBuy");
		//自定义类，不依赖spring初始化，可选。添加该参数则自定义初始化，不添加则使用spring初始化的类
		scheduleJob.setBeanClass(Test2.class.getName());
		scheduleJob.setPara(new JSONMap("id", 123));
		scheduleJob.setCronExpression("0/1 * * * * ? *");
		//指定运行时间，可选，不添加该参数则立即执行
		jobMethod.addAndResetScheduleJob(scheduleJob);
		Thread.sleep(10000);
	}

}
```

```java 
@Slf4j
public class Test2 {
	public void run(){
		log.info("run Without para");
	}

	public void run(JSONMap m){
		log.info("run With para:"+m);
	}

	public void deal(){
		log.info("deal Without para");
	}

	public void deal(JSONMap m){
		log.info("deal With para:"+m);
	}
}
``` 

```java 
@Component
public class Test1 extends Test2{

}
``` 