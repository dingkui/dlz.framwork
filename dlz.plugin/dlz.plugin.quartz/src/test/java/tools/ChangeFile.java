package tools;

import java.io.*;
import java.util.Arrays;

public class ChangeFile {
    public static void main(String[] args) throws IOException {
        String root="D:\\mysystem\\desktop\\新建文件夹 (4)\\5.运维资料";
        change(new File(root));
    }
    private final static void  change(File f) throws IOException {
        if(f.isFile()){
//            String absolutePath = f.getAbsolutePath().replaceAll("\\.txt$",".md");
//            f.renameTo(new File(absolutePath));
            charsetEnc(f);
        }else{
            Arrays.stream(f.listFiles()).forEach(fi -> {
                try {
                    change(fi);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 功能：更改单文件字符编码
     * @param file 文件
     * @throws IOException
     */
    public static void charsetEnc(File file) throws IOException{
        FileInputStream is = new FileInputStream(file);
        // 创建目标输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // 取流中的数据
        int len = 0;
        byte[] buf = new byte[256];
        while ((len = is.read(buf, 0, 256)) > -1) {
            bos.write(buf, 0, len);
        }
        // 目标流转为字节数组返回到前台
        byte[] bytes = bos.toByteArray();
        bos.close();
        is.close();

        String absolutePath = file.getAbsolutePath().replaceAll("\\.txt$",".md");
        FileOutputStream out = new FileOutputStream(absolutePath);
        out.write("```\n".getBytes());
        out.write(convEncoding(bytes,"gbk","UTF-8"));
        out.close();

        file.delete();
    }


    /**
     * 字符串的编码格式转换
     *
     * @param value
     *            -- 要转换的字符串
     * @param oldCharset
     *            -- 原编码格式
     * @param newCharset
     *            -- 新编码格式
     * @return
     */
    public static byte[] convEncoding(byte[] value, String oldCharset,
                                      String newCharset) {
        OutputStreamWriter outWriter = null;
        ByteArrayInputStream byteIns = null;
        ByteArrayOutputStream byteOuts = new ByteArrayOutputStream();
        InputStreamReader inReader = null;

        char cbuf[] = new char[1024];
        int retVal = 0;
        try {
            byteIns = new ByteArrayInputStream(value);
            inReader = new InputStreamReader(byteIns, oldCharset);
            outWriter = new OutputStreamWriter(byteOuts, newCharset);
            while ((retVal = inReader.read(cbuf)) != -1) {
                outWriter.write(cbuf, 0, retVal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inReader != null)
                    inReader.close();
                if (outWriter != null)
                    outWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        // System.out.println("temp" + temp);
        return byteOuts.toByteArray();
    }

}
