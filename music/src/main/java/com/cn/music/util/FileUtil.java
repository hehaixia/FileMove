package com.cn.music.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.zip.GZIPOutputStream;

public class FileUtil {

    /**
     * 加载本地文件,并转换为byte数组
     */
    public static byte[] loadFile(String filepath) {
        File file = new File(filepath);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        sdf.format(file.lastModified());
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        byte[] data = null ;

        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream((int) file.length());

            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray() ;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    fis = null;
                }

                baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data ;
    }

    /**
     * 对byte[]进行压缩
     */
    public static byte[] compress(byte[] data) {
        System.out.println("before:" + data.length);

        GZIPOutputStream gzip = null ;
        ByteArrayOutputStream baos = null ;
        byte[] newData = null ;

        try {
            baos = new ByteArrayOutputStream() ;
            gzip = new GZIPOutputStream(baos);

            gzip.write(data);
            gzip.finish();
            gzip.flush();

            newData = baos.toByteArray() ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                gzip.close();
                baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("after:" + newData.length);
        return newData ;
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }
}
