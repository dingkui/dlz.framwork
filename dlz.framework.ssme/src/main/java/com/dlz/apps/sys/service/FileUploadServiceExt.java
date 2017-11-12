package com.dlz.apps.sys.service;

import java.io.InputStream;

public interface FileUploadServiceExt {

	
	/**
	 * 文件上传
	 * @param fi  fileInpuStream
	 * @param prefix  文件后缀 如  .jpg
	 * @param baseFolder 文件夹
	 * @param userId 所属用户ID
	 * @param saveType 存储类型  1：用户logo  2：职场风采    3：用户视频  4：用户身份证正面  5：身份证反面  6：学生证  7：营业执照
	 * @return
	 * @throws Exception
	 */
	String saveFile(InputStream fi, String prefix, String baseFolder,
			Long userId, String saveType) throws Exception;
	
} 