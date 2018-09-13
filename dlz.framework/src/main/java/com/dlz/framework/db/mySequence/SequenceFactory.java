//package com.dlz.framework.db.mySequence;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.dlz.framework.db.mySequence.bean.SequenceBo;
//
//public class SequenceFactory {
//
//	private final Lock lock = new ReentrantLock();
//
//	/** */
//	private Map<String, SequenceHolder> holderMap = new ConcurrentHashMap<>();
//
//	@Autowired
//	private SequenceDAO msqlSequenceDAO;
//	/** 单个sequence初始化乐观锁更新失败重试次数 */
////	@Value("${seq.init.retry:5}")
//	private int initRetryNum=5;
//	/** 单个sequence更新序列区间乐观锁更新失败重试次数 */
////	@Value("${seq.get.retry:20}")
//	private int getRetryNum=10;
//
////	@PostConstruct
////	private void init() {
////		// 初始化所有sequence
////		initAll();
////	}
//	
//	/**
//	 * <p>
//	 * 加载表中所有sequence，完成初始化
//	 * </p>
//	 * 
//	 * @return void
//	 * @author coderzl
//	 */
//	public void initAll() {
//		try {
//			lock.lock();
//			List<SequenceBo> boList = msqlSequenceDAO.getAll();
//			if (boList == null) {
//				throw new IllegalArgumentException("The sequenceRecord is null!");
//			}
//			for (SequenceBo bo : boList) {
//				SequenceHolder holder = new SequenceHolder(msqlSequenceDAO, bo, initRetryNum, getRetryNum);
//				holder.init();
//				holderMap.put(bo.getName(), holder);
//			}
//		} finally {
//			lock.unlock();
//		}
//	}
//
//	/**
//	 * <p>
//	 * </p>
//	 * 
//	 * @param seqName
//	 * @return long
//	 * @author coderzl
//	 */
//	public long getNextVal(String seqName) {
//		SequenceHolder holder = holderMap.get(seqName);
//		if (holder == null) {
//			try {
//				lock.lock();
//				holder = holderMap.get(seqName);
//				if (holder != null) {
//					return holder.getNextVal();
//				}
//				SequenceBo bo = msqlSequenceDAO.getSequence(seqName);
//				holder = new SequenceHolder(msqlSequenceDAO, bo, initRetryNum, getRetryNum);
//				holder.init();
//				holderMap.put(seqName, holder);
//			} finally {
//				lock.unlock();
//			}
//		}
//		return holder.getNextVal();
//	}
//
//}