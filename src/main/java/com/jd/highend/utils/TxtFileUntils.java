package com.jd.highend.utils;


import java.io.*;

public class TxtFileUntils {

    public static void delFile(File file) {
        if (file.exists()) {
            file.delete();
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }

    }
    public static void writeFile(String filePath,String input) throws IOException {
//        File dir = new File(filePath);
//
//        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
//        if (!dir.exists()) {
//            dir.mkdirs();// mkdirs创建多级目录
//        }
        File checkFile = new File(filePath);
        FileWriter fw = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                checkFile.createNewFile();// 创建目标文件
            }
            // 三、向目标文件中写入内容
            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
            fw = new FileWriter(checkFile, true);
            fw.write(input+"\r\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fw)
                fw.close();
        }
    }



    public static void readFile(String filePath)  {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println(str);
        } catch (IOException e) {
        }
    }


}
