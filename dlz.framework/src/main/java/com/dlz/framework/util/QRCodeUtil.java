package com.dlz.framework.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 生成二维码QRCode
 * 
 * @date 2017-09-05
 * @author dk
 * 
 */
public class QRCodeUtil {
	/**
	 * 
	 * @param content
	 *            二維碼內容
	 * @param imgPath
	 *            生成圖片地址
	 * @param ccbPath
	 *            中間圖片路徑
	 * @return
	 */
	public static int createQRCode(String content, String imgPath, String ccbPath) {
		try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);

			// System.out.println(content);
			byte[] contentBytes = content.getBytes("gb2312");
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
				return -1;
			}
			// Image img = ImageIO.read(new File(ccbPath));//实例化一个Image对象。
			// gs.drawImage(img, 55, 55, 35,35,null);
			// gs.dispose();
			bufImg.flush();

			// 生成二维码QRCode图片
			File imgFile = new File(imgPath);
			ImageIO.write(bufImg, "png", imgFile);

		} catch (Exception e) {
			e.printStackTrace();
			return -100;
		}

		return 0;
	}
}
