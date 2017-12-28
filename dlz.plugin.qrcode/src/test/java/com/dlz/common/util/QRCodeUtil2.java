package com.dlz.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 生成二维码QRCode
 * 
 * @date 2017-09-05
 * @author dk
 * 
 */
public class QRCodeUtil2 {
	public static boolean createQRCode(String content, String imgPath, String logoFile) {
		return createQRCode(0,content, imgPath, logoFile);
	}
	public static boolean createQRCode(int w,String content, String imgPath) {
		return createQRCode(w,content, imgPath, null);
	}
	public static boolean createQRCode(String content, String imgPath) {
		return createQRCode(0,content, imgPath,null);
	}
	public static boolean createQRCode(int w, String content, String imgPath, String logoFile) {
		try {
			w=w==0?256:w;
			int m = 2;// 边框宽度
			int mw = 2;// 边框距离
			BufferedImage image = new BufferedImage(w + (m + mw) * 2, w + (m + mw) * 2, BufferedImage.TYPE_INT_RGB);
			int logw = w / 4;

			// 获取图形上下文
			Graphics gs = image.getGraphics();
			// 设定字体
			gs.setColor(Color.WHITE);
			gs.setFont(new Font("宋体", Font.CENTER_BASELINE, 44));
			gs.fillRect(m, m, w + mw * 2, w + mw * 2);

			BufferedImage bi = getQrCode(content.getBytes("GBK"));
			if (bi != null) {
				gs.drawImage(bi, m + mw, m + mw, w, w, null);
			}

			BufferedImage logo = makeRoundedCorner(logoFile, logw);
			if (logo != null) {
				int c = (w - logw) / 2 + m + mw;
				// 中央背景处理
				gs.fillRect(c - 2, c - 2, logw + 4, logw + 4);
				gs.drawImage(logo, c, c, logw, logw, null);
			}

			gs.dispose();
			image.flush();
			// 生成二维码QRCode图片
			ImageIO.write(image, "png", new File(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static BufferedImage getQrCode(byte[] contentBytes) {
		Qrcode qrcodeHandler = new Qrcode();
		qrcodeHandler.setQrcodeErrorCorrect('M');
		qrcodeHandler.setQrcodeEncodeMode('B');
		qrcodeHandler.setQrcodeVersion(7);

		if (contentBytes.length > 0 && contentBytes.length < 120) {
			boolean[][] s = qrcodeHandler.calQrcode(contentBytes);
			BufferedImage bi = new BufferedImage(s.length, s[0].length, BufferedImage.TYPE_BYTE_BINARY);
			Graphics2D g = bi.createGraphics();
			g.setBackground(Color.WHITE);
			int mulriple = 1;
			g.clearRect(0, 0, s.length, s[0].length);
			g.setColor(Color.BLACK);
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

	/*
	 * 圆角处理
	 * 
	 * @param BufferedImage
	 * 
	 * @param cornerRadius
	 */
	public static BufferedImage makeRoundedCorner(String logoFile, int w) {
		try {
			if (logoFile==null) {
				return null;
			}
			File logo = new File(logoFile);
			if (!logo.exists()) {
				return null;
			}
			int cornerRadius = w / 4;
			BufferedImage image = ImageIO.read(logo);
			BufferedImage output = new BufferedImage(w, w, BufferedImage.BITMASK);
			Graphics2D g2 = output.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.fillRoundRect(0, 0, w, w, cornerRadius, cornerRadius);
			g2.setComposite(AlphaComposite.SrcIn);
			g2.drawImage(image, 0, 0, w, w, null);
			g2.dispose();
			output.flush();
			return output;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
