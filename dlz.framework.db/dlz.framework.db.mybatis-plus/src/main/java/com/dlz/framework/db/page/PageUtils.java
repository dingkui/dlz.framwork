package com.dlz.framework.db.page;

public class PageUtils {

	public static int DEFAULT_PAGENUM=1;
	public static int DEFAULT_PAGESIZE=20;

//	public static Integer getPageSize() {
//		return ValUtil.getInt(HttpUtils.getRequest().getParameter("pageSize"), DEFAULT_PAGESIZE);
//	}
//
//	public static Integer getPageNum() {
//		return ValUtil.getInt(HttpUtils.getRequest().getParameter("pageNum"),DEFAULT_PAGENUM);
//	}
//
//	public static Integer getOffset() {
//		Integer pageNum =  getPageNum();
//		if(pageNum==null || pageNum<=0) {
//			pageNum=DEFAULT_PAGENUM;
//		}
//		Integer pageSize =  getPageSize();
//		if(pageSize==null || pageSize<=0) {
//			pageSize=DEFAULT_PAGESIZE;
//		}
//		return (pageNum-1)*pageSize;
//	}


//	public static void startPage(Integer pageNum,Integer pageSize) {
//		if(pageNum==null || pageNum<=0) {
//			pageNum=DEFAULT_PAGENUM;
//		}
//		if(pageSize==null || pageSize<=0) {
//			pageSize=DEFAULT_PAGESIZE ;
//		}
//		PageHelper.startPage(pageNum, pageSize);
//	}
//	public static void startPage(PagePara page) {
//		PageHelper.startPage(page.getPageNum()<1?DEFAULT_PAGENUM:page.getPageNum(), page.getPageSize()<1?DEFAULT_PAGESIZE:page.getPageSize());
//	}
//
////	public static void init() {
////		startPage(getPageNum(),getPageSize());
////	}
//
//	public  static <T> Page<T> buildPage(List<T> list){
//		return Page.getInstance(new PageInfo<T>(list));
//	}
//
//	public static <T,OUT> Page<OUT> buildPage(List<T> list,Class<OUT> convert){
//		Page<T> trans = Page.getInstance(new PageInfo<T>(list));
//		return convert(trans, convert);
//	}
//
//	public static <T,OUT> Page<OUT> convert(Page<T> trans,Class<OUT> convert){
//		Page<OUT> page = new Page<OUT>();
//		page.setPageNum(trans.getPageNum());
//		page.setHasNextPage(trans.isHasNextPage());
//		page.setPages(trans.getPages());
//		page.setPageSize(trans.getPageSize());
//		page.setTotal(trans.getTotal());
//		List<OUT> rows = new LinkedList<OUT>();
//		List<T> transRows =  trans.getRows();
//
//		if(CollectionUtils.isNotEmpty(transRows)) {
//			for (T obj : transRows) {
//				try {
//					OUT out= convert.newInstance();
//					OrikaUtils.copy(obj,out);
//					rows.add(out);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		page.setRows(rows);
//		return page;
//	}
}
