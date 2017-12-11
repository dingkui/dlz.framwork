package com.dlz.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.ParseUtil.ParserEnum;
import com.swetake.util.Qrcode;

public class DulplicateFiles {
	private static MyLogger logger =  MyLogger.getLogger(DulplicateFiles.class);
	
	static File centerFile=new File("d:/1/logo.png");
	static File imgFile=new File("d:/1/2.png");
	static Qrcode qrcodeHandler = new Qrcode();
	static{
		qrcodeHandler.setQrcodeErrorCorrect('Q');
		qrcodeHandler.setQrcodeEncodeMode('B');
		//qrcodeHandler.setQrcodeVersion(4);
	}
	public void createQRCode(String glUrl,long id,String sn,String kjUrl,String uid) throws Exception {
		try {
			BufferedImage image = ImageIO.read(imgFile);
			
			// 获取图形上下文
			Graphics gs = image.getGraphics();
			// 设定字体
			gs.setColor(Color.white);
			gs.setFont(new Font("宋体", Font.CENTER_BASELINE, 44));
			gs.drawString("SN:"+sn+"_"+uid+"_"+id, 16,570);
			BufferedImage bi= getQrCode(glUrl.getBytes("GBK"));
			if(bi!=null){
			  gs.drawImage(bi, 20, 22, 500, 500, null);
			}
			 
			 bi= getQrCode(kjUrl.getBytes("GBK"));
			 if(bi!=null){
				 gs.drawImage(bi, 622, 22, 500, 500, null);
				 Image img = ImageIO.read(centerFile);// 实例化一个Image对象。
				 gs.drawImage(img, 820, 220, 120, 120, null);
			 }
			
			gs.dispose();
			image.flush();
			
			// 生成二维码QRCode图片
			ImageIO.write(image, "png",  new File("d:/1/2/"+id+"_"+sn+"_"+uid+".png"));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	private BufferedImage getQrCode(byte[] contentBytes ){
		 if (contentBytes.length > 0 && contentBytes.length < 120) {  
             boolean[][] s = qrcodeHandler.calQrcode(contentBytes);  
             BufferedImage bi = new BufferedImage(s.length, s[0].length,BufferedImage.TYPE_BYTE_BINARY);  
             Graphics2D g = bi.createGraphics();  
             g.setBackground(Color.WHITE);  
             g.clearRect(0, 0, s.length, s[0].length);  
             g.setColor(Color.BLACK);  
             int mulriple=1;  
             for (int i = 0; i < s.length; i++) {  
                 for (int j = 0; j < s.length; j++) {  
                     if (s[j][i]) {  
                         g.fillRect(j * mulriple, i * mulriple, mulriple, mulriple);  
                     }  
                 }  
             }  
             g.dispose();  
             bi.flush();  
             return bi;
         }
		 return null;
	}
	
	
	@Test
	public void creatQr() throws Exception {
		for(int i=100001;i<100011;i++){
			String sn=ParserEnum.D523.encode(i);
			String uid=ParserEnum.D623.encode(i);
			String glUrl="http://192.168.1.155:8080/hotel/sb.do?QRCode=03000581307&custom=gls_"+sn;
			String kjUrl="http://192.168.1.155/"+uid;
			createQRCode(glUrl, Long.valueOf(i), sn, kjUrl,uid);
		}
	}
	
	@Test
	public void getFiles() throws Exception {
		getFileInfo(new File("E:\\资料\\5工作资料\\新建文件夹"));
		Set<Long> keySet=m.keySet();
		int i=0;
		for(Long k:keySet){
			if(m.get(k).size()>1){
				List<String> fs=m.get(k);
				for(String fp:fs){
					String scr=getFileCRCCode(new File(fp));
					if(m2.containsKey(scr)){
						m2.get(scr).add(fp);
					}else{
						List<String> l=new ArrayList<String>();
						l.add(fp);
						m2.put(scr, l);
					}
				}
				i+=fs.size();
				System.out.println(i);
			}
		}
		System.out.println(i);
		
		i=0;
		Set<String> keySet2=m2.keySet();
		for(String k:keySet2){
			if(m2.get(k).size()>1){
				List<String> fs=m2.get(k);
				i+=fs.size();
				System.out.println(fs);
			}
		}
		System.out.println(i);
	}
	
	
	private void getFileInfo(File f) throws Exception{
		if(f.isDirectory()){
			File[] files=f.listFiles();
			for(File fi:files){
				getFileInfo(fi);
			}
		}else{
			long scr=f.length();
			String fp=f.getAbsolutePath();
			if(m.containsKey(scr)){
				m.get(scr).add(fp);
			}else{
				List<String> l=new ArrayList<String>();
				l.add(fp);
				m.put(scr, l);
			}
		}
	}
	
	int i=0;
	private static Map<Long,List<String>> m=new HashMap<Long,List<String>>();
	private static Map<String,List<String>> m2=new HashMap<String,List<String>>();
	
    @SuppressWarnings("resource")
	public static String getFileCRCCode(File file) throws Exception {
        FileInputStream fileinputstream = new FileInputStream(file);
        CRC32 crc32 = new CRC32();
        for (CheckedInputStream checkedinputstream = new CheckedInputStream(fileinputstream, crc32);checkedinputstream.read() != -1;) {
        }
        return Long.toHexString(crc32.getValue());
    }
}

