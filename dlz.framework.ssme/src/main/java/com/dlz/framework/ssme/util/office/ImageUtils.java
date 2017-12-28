package com.dlz.framework.ssme.util.office;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.dlz.framework.logger.MyLogger;
/**
 * 图片工具类， 图片水印，文字水印，缩放，补白等
 * 
 * @author Carl He
 */
public final class ImageUtils {
	private static MyLogger logger = MyLogger.getLogger(ImageUtils.class);
	/**
	 * 
	 * @param logoText 水印内容
	 * @param srcImgPath 源图片路径 
     * @param targerPath 目标图片路径 
     * @param degree 水印图片旋转角度 
	 * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	 */
	public static void markByText(String logoText, String srcImgPath, String targerPath, Integer degree, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
		// 主图片的路径
		InputStream is = null;
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);

			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}
			
			String path = ImageUtils.class.getClassLoader().getResource("simsun.ttc").getPath();
			Font f = Font.createFont(Font.TRUETYPE_FONT, new File(path));
			f = f.deriveFont(fontStyle, fontSize);
			g.setFont(f);
			
	        g.setColor(color);
	        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
	        int width = srcImg.getWidth(null);
            int height = srcImg.getHeight(null);
			int width_1 = fontSize * getLength(logoText);
	        int height_1 = fontSize;
	        int widthDiff = width - width_1;
	        int heightDiff = height - height_1;
	        if(x < 0){
	            x = widthDiff / 2;
	        }else if(x > widthDiff){
	            x = widthDiff;
	        }
	        if(y < 0){
	            y = heightDiff / 2;
	        }else if(y > heightDiff){
	            y = heightDiff;
	        }
	            
	        g.drawString(logoText, x, y + height_1);
			//g.drawString(logoText, 150, 300);
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			logger.debug("图片完成添加文字印章。。。。。。");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
	}
	
	/**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
*/
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }
	public static void main(String[] args) {
		String srcImgPath = "C:/Users/Administrator/Desktop/1.jpg";  
        String logoText = "[ 测试文字水印 http://sjsky.iteye.com ]";  
        String targerPath = "C:/Users/Administrator/Desktop/2.jpg"; 
        markByText(logoText, srcImgPath, targerPath, null, "宋体", Font.BOLD, 80, Color.BLACK, -1, -1, 1f); 
	}
}