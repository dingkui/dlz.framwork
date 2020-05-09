package com.dlz.quartz;

import com.dlz.comm.json.JSONMap;
import lombok.extern.slf4j.Slf4j;

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
