package com.dlz.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.ParseUtil.ParserEnum;
import com.swetake.util.Qrcode;

public class CreateQrs {
	private static MyLogger logger = MyLogger.getLogger(CreateQrs.class);
	
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
	public void creatDiz() throws Exception {
		System.out.println(ParserEnum.D623.encode(1001));
	}
}

