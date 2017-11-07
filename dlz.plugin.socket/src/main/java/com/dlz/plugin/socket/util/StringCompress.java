package com.dlz.plugin.socket.util;
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;  
import java.util.zip.ZipInputStream;  
import java.util.zip.ZipOutputStream;  
  
public class StringCompress {  
	private static String cataName="UTF-8";
    public static final byte[] compress(String paramString) {  
        if (paramString == null)  
            return null;  
        ByteArrayOutputStream byteArrayOutputStream = null;  
        ZipOutputStream zipOutputStream = null;  
        byte[] arrayOfByte;  
        try {  
            byteArrayOutputStream = new ByteArrayOutputStream();  
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);  
            zipOutputStream.putNextEntry(new ZipEntry("0"));  
            zipOutputStream.write(paramString.getBytes(cataName));
            zipOutputStream.closeEntry();  
            arrayOfByte = byteArrayOutputStream.toByteArray();  
        } catch (IOException localIOException5) {  
            arrayOfByte = null;  
        } finally {  
            if (zipOutputStream != null)  
                try {  
                    zipOutputStream.close();  
                } catch (IOException localIOException6) {  
            }  
            if (byteArrayOutputStream != null)  
                try {  
                    byteArrayOutputStream.close();  
                } catch (IOException localIOException7) {  
            }  
        }  
        return arrayOfByte;  
    }  
  
    public static final String decompress(byte[] paramArrayOfByte) {  
        if (paramArrayOfByte == null)  
            return null;  
        ByteArrayOutputStream byteArrayOutputStream = null;  
        ByteArrayInputStream byteArrayInputStream = null;  
        ZipInputStream zipInputStream = null;  
        String str;  
        try {  
            byteArrayOutputStream = new ByteArrayOutputStream();  
            byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);  
            zipInputStream = new ZipInputStream(byteArrayInputStream);  
            zipInputStream.getNextEntry();  
            byte[] arrayOfByte = new byte[1024];  
            int i = -1;  
            while ((i = zipInputStream.read(arrayOfByte)) != -1)  
                byteArrayOutputStream.write(arrayOfByte, 0, i);  
            str = byteArrayOutputStream.toString(cataName);  
        } catch (IOException localIOException7) {  
            str = null;  
        } finally {  
            if (zipInputStream != null)  
                try {  
                    zipInputStream.close();  
                } catch (IOException localIOException8) {  
                }  
            if (byteArrayInputStream != null)  
                try {  
                    byteArrayInputStream.close();  
                } catch (IOException localIOException9) {  
                }  
            if (byteArrayOutputStream != null)  
                try {  
                    byteArrayOutputStream.close();  
                } catch (IOException localIOException10) {  
            }  
        }  
        return str;  
    }
    
    public static final String decompress(String paramArrayOfByte) throws UnsupportedEncodingException {  
			return decompress(paramArrayOfByte.getBytes(cataName));
    }
    
    public static final String compressStr(String paramString) throws UnsupportedEncodingException {  
        return new String(compress(paramString),cataName);  
    }  
    
    public static void main(String[] args) throws UnsupportedEncodingException {
    	String aa="aaaaaaabbbsdsdfrwerw1234123asdf啊沙发上大师傅aaaaaaaaaaabbbsdsdfrwerw1234123asdf啊沙发上大师傅aaaaaaaaaaabbbsdsdfrwerw1234123asdf啊沙发上大师傅aaaaaaaaaaabbbsdsdfrwerw1234123asdf啊沙发上大师傅aaaaaaaaaaabbbsdsdfrwerw1234123asdf啊沙发上大师傅aaaaaaaaaaarw1234123asdf啊沙发上大师傅aaaaaaarw1234123asdf啊沙发上大师傅aaaaaaaarw1234123asdf啊沙发上大师傅aaaaaarw1234123asdf啊沙发上大师傅aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
    	
    	byte[] a=compress(aa);
    	System.out.println(a.length);
    	String s=new String(a,"ISO-8859-1");
    	byte[] a1=s.getBytes("ISO-8859-1");
    	System.out.println(s.length());
		System.out.println(aa.length());
		System.out.println(s);
		System.out.println(decompress(a1));
		System.out.println(decompress(a));
	}
}