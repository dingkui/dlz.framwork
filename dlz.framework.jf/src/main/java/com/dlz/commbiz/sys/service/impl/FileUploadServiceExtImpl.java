package com.dlz.commbiz.sys.service.impl;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.commbiz.sys.service.FileUploadServiceExt;
import com.dlz.common.util.config.ConfigUtil;
import com.dlz.common.util.office.ImageUtil;
import com.dlz.framework.db.service.ICommService;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileUploadServiceExtImpl implements FileUploadServiceExt {
	@Autowired
	protected ICommService commService;

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
	@Override
	public String saveFile(InputStream fi, String prefix, String baseFolder,
			Long userId, String saveType) throws Exception {
		// ShiroUser loginUser = (ShiroUser)
		// SecurityUtils.getSubject().getPrincipal();
		// 文件存放根目录
		String imgUploadPath = ConfigUtil.Configs.Sys.getImgUploadPath();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String cdate = sf.format(new Date());
		String year = cdate.substring(0, 4);
		String month = cdate.substring(4, 6);
		String day = cdate.substring(6, 8);
		String folderPath = baseFolder + File.separator + saveType + File.separator + year + File.separator
				+ month + File.separator + day;
		String filePath = cdate + "_" + userId + prefix;
		String targetPath = imgUploadPath + File.separator + folderPath
				+ File.separator + filePath;
		FileUtils.copyInputStreamToFile(fi, new File(targetPath));
		if (prefix.substring(1).equalsIgnoreCase("jpg")
				|| prefix.substring(1).equalsIgnoreCase("gif")
				|| prefix.substring(1).equalsIgnoreCase("png")
				|| prefix.substring(1).equalsIgnoreCase("bmp")) {
			ImageUtil.getNewSizePic(targetPath, 300, 300);
		}
		String url = folderPath + File.separator + filePath;
		
		return url;
	}
}