package com.dlz.apps.file.service.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.apps.file.dao.FilesMapper;
import com.dlz.apps.file.enums.FileTypeEnum;
import com.dlz.apps.file.model.Files;
import com.dlz.apps.file.model.FilesCriteria;
import com.dlz.apps.file.service.FilesService;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.util.config.ConfigUtil;
import com.dlz.framework.ssme.util.office.ImageUtil;
import com.dlz.framework.util.ParseUtil.ParserEnum;
import com.dlz.framework.util.StringUtils;
import com.swetake.util.Qrcode;

@Service
@Transactional(rollbackFor = Exception.class)
public class FilesServiceImpl extends BaseServiceImpl<Files, Long> implements FilesService {
	private static Logger logger = LoggerFactory.getLogger(FilesServiceImpl.class);
	private static String UPLOAD_PATH = "sys.img.upload.path";
	private static String NGINX_PATH = "nginxServer";

	@Autowired
	public void setMapper(FilesMapper mapper) {
		this.mapper = mapper;
	}

	@Autowired
	protected ICommService commService;

	private boolean isPic(String prefix) {
		return prefix.length() == 0 || prefix.substring(1).equalsIgnoreCase("jpg") || prefix.substring(1).equalsIgnoreCase("gif") || prefix.substring(1).equalsIgnoreCase("png")
				|| prefix.substring(1).equalsIgnoreCase("bmp");
	}

	/**
	 * 文件上传
	 * 
	 * @param fi
	 *          InpuStream
	 * @param fileTypeEnum
	 *          存储类型
	 * @param m
	 *          .fSurfix 文件后缀： *必须
	 * @param m
	 *          .fName 文件名： 默认"noneName"
	 * @param m
	 *          .baseFolder 基本根目录： 默认“upload”
	 * @param m
	 *          .dataId 数据ID：* 必须传入
	 * @param m
	 *          .fOrd 文件序号：传入时会覆盖对应文件 Map<String,Object> m=new
	 *          HashMap<String,Object>(); m.put("fSurfix", ""); m.put("fName",
	 *          ""); m.put("saveFolder", ""); m.put("dataId", ""); m.put("fOrd",
	 *          "");
	 * @return
	 * @throws Exception
	 */
	public Files saveFile(InputStream fi, FileTypeEnum fileTypeEnum, Map<String, Object> m) throws Exception {
		String prefix = StringUtils.ObjNVLString(m.get("fSurfix"), ".");
		String fileName = StringUtils.ObjNVLString(m.get("fName"), "noneName");
		String saveFolder = StringUtils.ObjNVLString(m.get("saveFolder"), "");
		Long dataId = StringUtils.ObjNVLLong(m.get("dataId"), -1l);
		Long fOrd = StringUtils.ObjNVLLong(m.get("fOrd"), -1l);
		boolean saveDb = StringUtils.ObjNVL(m.get("saveDb"), true,Boolean.class);
		if (dataId == -1) {
			Files f = new Files();
			f.setfPath("/none" + prefix);
			return f;
		}

		// 构建文件保存路径
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String cdate = sf.format(new Date());
		String year = cdate.substring(0, 4);
		String month = cdate.substring(4, 6);
//		String day = cdate.substring(6, 8);
		StringBuilder folderPath = new StringBuilder();
		folderPath.append(File.separator + "upload");
		folderPath.append(File.separator + year+month);
		folderPath.append(File.separator + fileTypeEnum.getTypeCode()+"_"+fileTypeEnum);
		if(org.apache.commons.lang3.StringUtils.isNotBlank(saveFolder)){
			folderPath.append(File.separator + saveFolder);
		}
		folderPath.append(File.separator + dataId);
//		folderPath.append(File.separator + month);
//		folderPath.append(File.separator + day);
		folderPath.append(File.separator + cdate + prefix);
		String filePath = folderPath.toString();

		// 文件绝对路径
		String targetPath = ConfigUtil.getConfig(UPLOAD_PATH) + filePath;
		FileUtils.copyInputStreamToFile(fi, new File(targetPath));
		if (isPic(prefix)) {
			ImageUtil.getNewSizePic(targetPath, 300, 300);
		}

		Files files = new Files();
		files.setfPath(filePath);// 存储地址
		if(saveDb){
			
			files.setId(commService.getSeq(Files.class));// 编号
			files.setdType(fileTypeEnum.getTypeCode());// 业务类型
			files.setDataId(dataId);// 数据ID
			files.setfName(fileName);// 名称
			files.setfSurfix(prefix);// 文件后缀
			
			synchronized (this) {
				prefix = prefix.indexOf(".") != 0 ? ("." + prefix) : prefix;
				if(saveDb){
					if (fOrd > -1) {
						Map<String, Object> pms=new HashMap();
						pms.put("dataId", dataId);
						pms.put("fOrd", fOrd);
						List<ResultMap> rms = getFileUrls(pms, fileTypeEnum);
						for (ResultMap r : rms) {
							delFile(r.getLong("id"), r.getStr("fPath"));
						}
					} else {
						ParaMap pm = new ParaMap("key.files.getMaxOrd");
						pm.addPara("dataId", dataId);
						pm.addPara("dType", fileTypeEnum.getTypeCode());
						fOrd = ((BigDecimal) commService.getColum(pm)).longValue();
					}
				}
				files.setfOrd(fOrd);// 序号
				files.setfDel(0l);// 是否删除
				insert(files);
			}
		}
		files.setHttpfPath(getImgPath(filePath));
		return files;
	}

	// @Override
	// public String saveFile(String base64Code, FileTypeEnum fileTypeEnum) throws
	// Exception {
	// byte[] b = Base64.decode(base64Code.toCharArray());
	// InputStream fi = new
	// ByteArrayInputStream(Base64.decode(base64Code.toCharArray()));
	// return saveFile(fi, prefix, fileName, baseFolder, dataId, fileTypeEnum);
	// }

	/**
	 * 取得多个文件
	 * 
	 * @param fid
	 * @return
	 * @throws Exception
	 */
	private List<ResultMap> getFileUrls(Map<String, Object> paras, FileTypeEnum dType) throws Exception {
		ParaMap pm = new ParaMap("key.files.getfiles");
		pm.getPara().putAll(paras);
		pm.addPara("dType", dType.getTypeCode());
		return commService.getMapList(pm);
	}

	// /**
	// * 取得单个文件
	// * @param fid
	// * @return
	// * @throws Exception
	// */
	// private List<Map<String,String>> getFileUrl(Map<String, String> paras)
	// throws Exception{
	// ParaMap pm = new ParaMap("key.files.getfiles");
	// pm.getPara().putAll(paras);
	// List<Map<String,String>> r=new ArrayList<Map<String,String>>();
	// List<ResultMap> rmList = commService.getMapList(pm);
	// for(ResultMap rm:rmList){
	// Map<String,String> m= new HashMap<String,String>();
	// m.put("fName", rm.getStr("fName"));
	// m.put("fSurfix", rm.getStr("fSurfix"));
	// m.put("fPath",rm.getStr("fPath"));
	// r.add(m);
	// }
	// return null;
	// }

	/**
	 * 根据相对路径获取网络路径
	 * 
	 * @param fPath
	 * @return
	 */
	private static String getImgPath(String fPath) {
		String showPath = ConfigUtil.getConfig(NGINX_PATH);
		String srcImgPath = showPath + File.separator + fPath;
		// 替换反斜杠为双反斜杠，去掉换行符
		srcImgPath = srcImgPath.replaceAll("\\\\+", "/").replaceAll("([^:])/+", "$1/").replaceAll("\r|\n", "");
		return srcImgPath;
	}

	private Files cover2Files(ResultMap rm) {
		Files files = new Files();
		files.setfName(rm.getStr("fName"));
		files.setId(rm.getLong("id"));
		files.setfSurfix(rm.getStr("fSurfix"));
		files.setfPath(rm.getStr("fPath"));
		files.setfOrd(rm.getLong("fOrd"));
		files.setHttpfPath(getImgPath(files.getfPath()));
		return files;
	}

	/**
	 * 根据相对路径获取网络路径
	 * 
	 * @param fPath
	 * @return
	 * @throws Exception
	 */
	private void delFile(Long id, String fPath) throws Exception {
		deleteByPrimaryKey(id);
		File f = new File(ConfigUtil.getConfig(UPLOAD_PATH) + fPath);
		if (f.isAbsolute()) {
			f.delete();
		}
		File f2 = new File(ImageUtil.getNewNameBySize(f.getAbsolutePath(), 300, 300));
		if (f2.isAbsolute()) {
			f2.delete();
		}
	}

	public List<Files> getFiles(Long dataId, String fOrd, FileTypeEnum fileTypeEnum) throws Exception {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("dataId", dataId);
		m.put("fOrd", fOrd);
		List<ResultMap> rs = getFileUrls(m, fileTypeEnum);
		List<Files> fl = new ArrayList<Files>();
		for (ResultMap r : rs) {
			fl.add(cover2Files(r));
		}
		return fl;
	}

	public Files getFile(Long dataId, String fOrd, FileTypeEnum fileTypeEnum) throws Exception {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("dataId", dataId);
		m.put("fOrd", fOrd);
		List<ResultMap> rs = getFileUrls(m, fileTypeEnum);
		for (ResultMap r : rs) {
			return cover2Files(r);
		}
		return null;
	}

	public Map<String, String> getFileUrls(String path) throws Exception {
		if (path == null) {
			return null;
		}
		Map<String, String> m = new HashMap<String, String>();
		String fullUrl = getImgPath(path);
		m.put("url", fullUrl);
		m.put("smallUrl", ImageUtil.getNewNameBySize(fullUrl, 300, 300));
		return m;
	}

	public void delFile(Long id) throws Exception {
		Files f = new Files();
		f.setId(id);
		f.setfDel(1l);
		updateByPrimaryKeySelective(f);
	}
	public void delFile(Long dataId,Long fOrd) throws Exception {
		Files f = new Files();
		f.setfDel(1l);
		FilesCriteria fc = new FilesCriteria();
		fc.createCriteria().andDataIdEqualTo(dataId).andFOrdEqualTo(fOrd);
		updateByExampleSelective(f, fc);
	}

	/**
	 * 生成二维码图片
	 * 
	 */
	/**
	 * 生成二维码图片
	 * 
	 * @param url
	 *          二维码对应的地址
	 * @param dataId
	 *          二维码对应的数据
	 * @param ccbPath
	 *          中间添加的图片地址
	 * @return
	 * @throws Exception
	 */
	public Files createQRCode(String url, Long dataId, String ccbPath, String order) throws Exception {
		try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);

			byte[] contentBytes = (url +  ParserEnum.D367.encode(dataId)).getBytes("gb2312");
			BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();

			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 140, 140);

			// 设定图像颜色 > BLACK
			gs.setColor(Color.BLACK);

			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容 > 二维码
			if (contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ");
				return null;
			}
			if (ccbPath != null) {
				File ccbFile=new File(ccbPath);
				if(ccbFile.exists()){
					Image img = ImageIO.read(ccbFile);// 实例化一个Image对象。
					gs.drawImage(img, 55, 55, 35, 35, null);
					gs.dispose();
				}
			}
			bufImg.flush();

			// 生成二维码QRCode图片
			File imgFile = new File(dataId + "temp.png");
			ImageIO.write(bufImg, "png", imgFile);

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("fSurfix", ".png");
			m.put("fName", "");
			m.put("dataId", dataId);
			m.put("fOrd", order);
			m.put("saveDb", false);
			Files f= saveFile(new FileInputStream(imgFile), FileTypeEnum.ewm, m);
			imgFile.delete();
			return f;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}