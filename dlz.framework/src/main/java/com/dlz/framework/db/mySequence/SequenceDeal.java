package com.dlz.framework.db.mySequence;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.dlz.comm.json.JSONMap;
import com.dlz.framework.db.modal.InsertParaMap;
import com.dlz.framework.db.mySequence.bean.SequenceBo;
import com.dlz.framework.db.service.ICommService;

@Service
@DependsOn("commServiceImpl")
@Lazy
public class SequenceDeal {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(SequenceDeal.class);
	@Autowired
	ICommService commServiceImpl;

	_Updater updater = null;
	_SeqDao seqDao = null;

	private final Lock lock = new ReentrantLock();
	private Map<String, AtomicLong> holderMap = new ConcurrentHashMap<>();

	@PostConstruct
	private void init() {
		seqDao = new _SeqDao();
		// 加载表中所有sequence，完成初始化
		List<SequenceBo> boList = seqDao.getAll();
		for (SequenceBo bo : boList) {
			holderMap.put(bo.getName(), new AtomicLong(bo.getVal()));
		}
		updater = new _Updater();
	}

	public long nextVal(String seqName) {
		AtomicLong valCounter = holderMap.get(seqName);
		Long val = -1l;
		if (valCounter == null) {
			try {
				lock.lock();
				valCounter = holderMap.get(seqName);
				if (valCounter == null) {
					// 取不到则从数据库中取得，数据库中没有则创建
					SequenceBo bo = seqDao.getSequence(seqName);
					if (bo == null) {
						bo = new SequenceBo();
						bo.setName(seqName);
						bo.setMin(1L);
						bo.setMax(Long.MAX_VALUE);
						bo.setStep(1L);
						bo.setCt(new Date());
						seqDao.createSequence(bo);
					}
					valCounter = new AtomicLong(bo.getVal());
					holderMap.put(bo.getName(), valCounter);
				}
			} finally {
				lock.unlock();
			}
		}
		try {
			val = valCounter.incrementAndGet();
			return val;
		} finally {
			updater.add(seqName, val);
		}
	}

	private class _Updater implements Runnable {
		protected boolean running = true;
		private final BlockingDeque<String> dealQuque = new LinkedBlockingDeque<>();
		private final Map<String, Long> valMap = new ConcurrentHashMap<>();

		// public void stop() {
		// running = false;
		// }
		_Updater() {
			Thread sequenceUpdatorThread = new Thread(this, "SequenceDeal");
			// messageSenderThread.setDaemon(true);
			sequenceUpdatorThread.start();
		}

		/**
		 * Add message for sending.
		 */
		public void add(String name, Long value) {
			dealQuque.add(name);
			valMap.put(name, value);
		}

		public void run() {
			while (running) {
				try {
					String name = dealQuque.take();
					final Long newValue = valMap.get(name);
					if (newValue == null) {
						continue;
					}
					seqDao.updSequence(name, newValue);
					valMap.remove(name);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	private class _SeqDao {
		/**
		 * 
		 * 
		 * CREATE TABLE `t_sequence` ( `name` varchar(128) CHARACTER SET utf8
		 * NOT NULL COMMENT '序列名称', `val` bigint(20) NOT NULL COMMENT '目前序列值',
		 * `min` bigint(20) NOT NULL COMMENT '最小值', `max` bigint(20) NOT NULL
		 * COMMENT '最大值', `step` bigint(20) NOT NULL COMMENT '每次取值的数量', `ct`
		 * datetime NOT NULL COMMENT '创建时间', `ut` datetime NOT NULL DEFAULT
		 * CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间', PRIMARY
		 * KEY (`name`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
		 * COMMENT='流水号生成表';
		 * 
		 */
		public int updSequence(String seqName, long newValue) {
			return commServiceImpl.excuteSql("update t_sequence set val=? where name=?", newValue, seqName);
		}

		public SequenceBo getSequence(String seqName) {
			return commServiceImpl.getBean("select * from t_sequence where name=?", SequenceBo.class, seqName);
		}

		public List<SequenceBo> getAll() {
			return commServiceImpl.getBeanList("select * from t_sequence", SequenceBo.class);
		}

		public int createSequence(SequenceBo bo) {
			InsertParaMap in = new InsertParaMap("t_sequence");
			in.addValues(new JSONMap(bo));
			return commServiceImpl.excuteSql(in);
		}
	}
}