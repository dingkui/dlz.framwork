package com.dlz.framework.ssme.util.office;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtil {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	
	public static void thumbnail(InputStream in, OutputStream out, int width, int height) throws IOException {
		Thumbnails.of(in).size(width, height)
		.toOutputStream(out);
	}
	public static void getNewSizePic(String file, int width, int height) throws IOException {
		if(file==null){
			return;
		}
		InputStream in=null;
		OutputStream out=null;
		try{
			in=new FileInputStream(file);
			out=new FileOutputStream(getNewNameBySize(file, width, height));
			Thumbnails.of(in).size(width, height)
			.toOutputStream(out);
		}finally{
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
		}
	}
	
	public static String getNewNameBySize(String file,int width, int height){
		if(file==null || file.indexOf(".")==-1){
			return "";
		}
		return file.substring(0,file.lastIndexOf("."))+width+"X"+height+file.substring(file.lastIndexOf("."));
	}
	
	public static BufferedImage Rotate(Image src, int angel) {
		int src_width = src.getWidth(null);
		int src_height = src.getHeight(null);
		// calculate the new image size
		Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
				src_width, src_height)), angel);

		BufferedImage res = null;
		res = new BufferedImage(rect_des.width, rect_des.height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = res.createGraphics();
		// transform
		g2.translate((rect_des.width - src_width) / 2,
				(rect_des.height - src_height) / 2);
		g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

		g2.drawImage(src, null, null);
		return res;
	}

	public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
		// if angel is greater than 90 degree, we need to do some conversion
		if (angel >= 90) {
			if(angel / 90 % 2 == 1){
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel % 90;
		}

		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
		double angel_dalta_width = Math.atan((double) src.height / src.width);
		double angel_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
				- angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
				- angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}
	
	public static void main(String[] args) throws IOException {
		getNewSizePic("D:\\mysystem\\desktop\\新建文件夹\\房产认证\\mmexport1411274578099.jpg", 700, 700);
		getNewSizePic("D:\\mysystem\\desktop\\新建文件夹\\房产认证\\mmexport1411274578099.jpg", 200, 200);
		getNewSizePic("D:\\mysystem\\desktop\\新建文件夹\\房产认证\\mmexport1411274583670.jpg", 700, 700);
		getNewSizePic("D:\\mysystem\\desktop\\新建文件夹\\房产认证\\mmexport1411274583670.jpg", 200, 200);
		getNewSizePic("D:\\mysystem\\desktop\\新建文件夹\\房产认证\\mmexport1411274588207.jpg", 700, 700);
		getNewSizePic("D:\\mysystem\\desktop\\新建文件夹\\房产认证\\mmexport1411274588207.jpg", 200, 200);
		getNewSizePic("D:\\mysystem\\desktop\\新建文件夹\\房产认证\\mmexport1411274592096.jpg", 700, 700);
		getNewSizePic("D:\\mysystem\\desktop\\新建文件夹\\房产认证\\mmexport1411274592096.jpg", 200, 200);
	}
}
