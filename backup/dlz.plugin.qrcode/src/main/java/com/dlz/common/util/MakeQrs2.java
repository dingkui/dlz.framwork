package com.dlz.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.util.ParseUtil.ParserEnum;
import com.swetake.util.Qrcode;

public class MakeQrs2 {
	private static MyLogger logger =  MyLogger.getLogger(MakeQrs2.class);
	
	static Map<String,String> paraMap=new HashMap<String,String>();
	
	static void initParas(String[] args,Map<String,String> paraMap){
		if(args==null){
			logger.warn("无参数");
			return;
		}
		for(int i=0;i<args.length;i++){
			if(args[i].startsWith("-")){
				paraMap.put(args[i].substring(1), args[++i]);
			}
		}
		System.out.println(paraMap);
	}
	
	public static void main(String[] args) throws Exception {
		initParas(args, paraMap);
		
		MakeQrs2 mq=new MakeQrs2();
		String path=paraMap.get("path");//eg:d:/1/
		String logoFile=paraMap.get("logoFile");//eg"d:/1/logo.png
		String templateFile=paraMap.get("templateFile");//eg:d:/1/2.png
		String glUrl=paraMap.get("glUrl");//eg:http://h.shunliannet.com/sb.do?QRCode=03000581307&custom=gls_
		String kjUrl=paraMap.get("kjUrl");//eg:http://s.shunliannet.com/{0}
		String glCap=paraMap.get("glCap");//eg:扫码开机
		String kjCap=paraMap.get("kjCap");//eg:SN
		String mkmethod=paraMap.get("mkmethod");//eg:二维码方式 1：1张二维码  2：2张二维码
		
		if(logoFile!=null)
		mq.logoFile=new File(logoFile);
		
		if(templateFile!=null)
		mq.templateFile=new File(templateFile);
		
		if(glUrl!=null)
		mq.glUrl=glUrl;
		
		if(kjUrl!=null)
		mq.kjUrl=kjUrl;
		
		if(kjCap!=null)
		mq.kjCap=kjCap;
		
		if(glCap!=null)
		mq.glCap=glCap;
		
		if(path!=null)
		mq.path=path;
		
		if(mkmethod!=null)
		mq.mkmethod=mkmethod;
		
		
		String startStr=paraMap.get("start");//eg:101001
		String endStr=paraMap.get("end");//eg:105999
		if(startStr!=null)
		mq.start=Integer.parseInt(startStr);
		if(endStr!=null)
		mq.end=Integer.parseInt(endStr);
		
		long start=mq.start;
		long end=mq.end;
		long every=1000;
		while(start<end){
			end=end<start+every-1?end:start+every-1;
			mq.creatQr(start,end);
			start+=every;
		}
	}
	
	File logoFile=new File("logo.png");
	File templateFile=new File("tpl.png");
	String path="";
//	String glUrl="http://h.shunliannet.com/sb?QRCode=03000581307&custom=gls_";
	String glUrl="http://h.shunliannet.com/sb?QRCode=03000616301&custom=gls_";
	String kjUrl="http://s.shunliannet.com/{0}";
	String kjCap="扫码开机";
	String glCap="SN";
	String mkmethod="2";
	long start=1;
	long end=2;
	static Qrcode qrcodeHandler = new Qrcode();
	static{
		qrcodeHandler.setQrcodeErrorCorrect('Q');
		qrcodeHandler.setQrcodeEncodeMode('B');
		//qrcodeHandler.setQrcodeVersion(4);
	}
	
	static int x16=157;//初始左偏移
	static int y570=771;
	
	static int x_add=596;//宽度
	static int y_add=644;//高度
	
	
	
	public void addQrs2(Graphics gs,int row,int col,long id,String glUrl,String sn,String kjUrl,String uid) throws Exception {
		try {
			int textX1=x16+x_add*col*2+col;
			int texty1=y570+y_add*row;
			
			int qrX1=textX1;
			int qry1=texty1-y_add+93;
			
			gs.drawString(glCap+":"+sn+"_"+uid+"_"+id, textX1,texty1);
			gs.drawString(kjCap+"（"+sn+")", textX1+x_add+80,texty1);
			
			BufferedImage bi= getQrCode(glUrl.getBytes("GBK"));
			if(bi!=null){
			  gs.drawImage(bi, qrX1,qry1, 500, 500, null);
			}
			 
			bi= getQrCode(kjUrl.getBytes("GBK"));
			if(bi!=null){
				 gs.drawImage(bi, qrX1+x_add, qry1, 500, 500, null);
				 Image img = ImageIO.read(logoFile);// 实例化一个Image对象。
				 gs.drawImage(img, qrX1+x_add+190, qry1+190, 120, 120, null);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void addQrs1(Graphics gs,int row,int col,long id,String glUrl,String sn) throws Exception {
		try {
			int textX1=x16+x_add*col*2+col;
			int texty1=y570+y_add*row;
			int qry1=texty1-y_add+93;
			
			BufferedImage bi= getQrCode(glUrl.getBytes("GBK"));
			if(bi!=null){
				gs.drawImage(bi, textX1+x_add, qry1, 500, 500, null);
				Image img = ImageIO.read(logoFile);// 实例化一个Image对象。
				gs.drawImage(img, textX1+x_add+190, qry1+190, 120, 120, null);
				gs.drawString(kjCap+"("+sn+id+")", textX1,texty1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public long createQRCode(long id,String path) throws Exception {
		try {
			BufferedImage image = ImageIO.read(templateFile);
			
			// 获取图形上下文
			Graphics gs = image.getGraphics();
			// 设定字体
			gs.setColor(Color.WHITE);
			gs.setFont(new Font("宋体", Font.CENTER_BASELINE, 44));
			
			long idEnd=id-1;
			for(int col=0;col<4;col++){
				for(int row=0;row<5;row++){
					idEnd++;
					if("2".equals(mkmethod)){
						String sn=ParserEnum.D523.encode(idEnd);
						String uid=ParserEnum.D623.encode(idEnd);
						String glUrls=glUrl+sn;
						String kjUrls=MessageFormat.format(kjUrl,uid,sn);
						addQrs2(gs, row, col, idEnd, glUrls, sn, kjUrls, uid);
					}else if("1".equals(mkmethod)){
						String sn=ParserEnum.D523.encode(idEnd);
						String glUrls=glUrl+sn;
						addQrs1(gs, row, col, idEnd, glUrls, sn);
					}
				}
			}
			gs.dispose();
			image.flush();
			// 生成二维码QRCode图片
			ImageIO.write(image, "png",  new File(path+id+"_"+idEnd+".png"));
			
			System.out.println(path+id+"_"+idEnd+".png");
			return ++idEnd;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return 0;
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
	
	
	public void creatQr(long start,long end) throws Exception {
		String folder=path+start+"_"+end+"/";
		new File(folder).mkdirs();
		while(start<end){
			start=createQRCode(start,folder);
		}
	}
}