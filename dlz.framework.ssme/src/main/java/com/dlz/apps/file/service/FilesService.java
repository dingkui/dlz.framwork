package com.dlz.apps.file.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.dlz.apps.file.enums.FileTypeEnum;
import com.dlz.apps.file.model.Files;
import com.dlz.framework.ssme.base.service.BaseService;

public interface FilesService extends BaseService<Files, Long> {
	
	/**
	 * 文件上传
	 * 
	 * @param fi InpuStream
	 * @param fileTypeEnum 存储类型
	 * @param m.fSurfix 文件后缀：    *必须
	 * @param m.fName 文件名： 默认"noneName"
	 * @param m.baseFolder 基本根目录： 默认“upload”
	 * @param m.dataId 数据ID：* 必须传入
	 * @param m.fOrd 文件序号：传入时会覆盖对应文件
	  Map<String,Object> m1=new HashMap<String,Object>();
		m.put("fSurfix", "");
		m.put("fName", "");
		m.put("saveFolder", "");
		m.put("dataId", "");
		m.put("fOrd", "");
	 * @return
	 * @throws Exception
	 */
	Files saveFile(InputStream fi,  FileTypeEnum fileTypeEnum,Map<String,Object> m) throws Exception;
	/**
	 * 查询文件信息
	 * @param dataId
	 * @param fOrd
	 * @param fileTypeEnum
	 * @return
	 * @throws Exception
	 */
	List<Files> getFiles(Long dataId, String fOrd, FileTypeEnum fileTypeEnum) throws Exception;
	/**
	 * 查询资料
	 * @param dataId
	 * @param fOrd
	 * @param fileTypeEnum
	 * @return
	 * @throws Exception
	 */
	List<Files> getzlFiles(String fName, String zlType, FileTypeEnum fileTypeEnum) throws Exception;
	/**
	 * 删除图片
	 * @param id
	 * @throws Exception
	 */
	void delFile(Long id) throws Exception; 
	/**
	 * 创建二维码
	 * @param url
	 * @param dataId
	 * @param ccbPath
	 * @return
	 * @throws Exception
	 */
	Files createQRCode(String url, Long dataId, String ccbPath, String fOrd) throws Exception;
	/**
	 * 取得文件的网络地址
	 * @param path
	 * @return
	 * @throws Exception
	 */
	Map<String, String> getFileUrls(String path) throws Exception;
	Files getFile(Long dataId, String fOrd, FileTypeEnum fileTypeEnum) throws Exception;
	void delFile(Long dataId, Long fOrd) throws Exception;
	
	/**
	 * 根据id获取文件
	 */
	public Files get(Integer id);
	
	public Files getF(Integer id);
}