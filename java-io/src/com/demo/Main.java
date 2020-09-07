package com.demo;




import com.demo.model.Student;

import java.io.*;
import java.util.Scanner;

public class Main {

    private final static String TEST_PATH = "D:\\io\\test\\a.txt";

    private final static String SER_PATH = "D:\\io\\test\\object";

    private final static Scanner scanner = new Scanner(System.in);

    private static void appIO() throws Exception{
        String next = scanner.next();
        switch (next){
            case "1":
                fileInputStreamApply();
                appIO();
                break;
            case "2":
                bufferedInputStreamApply();
                appIO();
                break;
            case "3":
                fileOutputStreamApply();
                appIO();
                break;
            case "4":
                bufferedOutputStreamApply();
                appIO();
                break;
            case "5":
                objectOutputStreamApply();
                appIO();
                break;
            case "6":
                objectInputStreamApply();
                appIO();
                break;
            case "-1":
                break;
        }
    }

    private static void objectInputStreamApply() throws Exception{
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(SER_PATH));

        Object o = inputStream.readObject();
        System.out.println(o);
        if (inputStream != null){
            inputStream.close();
        }
    }

    private static void objectOutputStreamApply() throws Exception{
        Student student = new Student();
        student.setId(1);
        student.setName("jack");
        student.setAge(23);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SER_PATH));
        out.writeObject(student);
        if(out != null){
            out.flush();
            out.close();
        }
    }

    private static void bufferedOutputStreamApply() throws Exception{
        String word = "\r\nbufferedOutputStreamApply追加內容";
        byte[] bytes = word.getBytes("UTF-8");
        OutputStream out = new BufferedOutputStream(new FileOutputStream(TEST_PATH, true));
        for (byte b :bytes) {
            out.write(b);
        }
        if(out != null){
            out.flush();
            out.close();
            System.out.println("操作完成");
        }
    }

    private static void fileOutputStreamApply() throws Exception{
        String word = "\r\nfileOutputStreamApply追加內容";
        byte[] bytes = word.getBytes("utf-8");
        OutputStream fos = new FileOutputStream(TEST_PATH, true);
        for (byte b :bytes) {
            fos.write(b);
        }
        if(fos != null){
            fos.flush();
            fos.close();
            System.out.println("操作完成");
        }
    }


    private static void fileInputStreamApply() throws Exception {
        InputStream fis = new FileInputStream(TEST_PATH);
        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        System.out.println(new String(buf,0, buf.length));
        if(fis != null){
            fis.close();
        }
    }

    private static void bufferedInputStreamApply() throws Exception{
        InputStream inputStream = new BufferedInputStream(new FileInputStream(TEST_PATH));
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        System.out.println(new String(buffer, 0, buffer.length));
        if(inputStream != null){
            inputStream.close();
        }
    }


    public static void main(String[] args) throws Exception {
        appIO();
    }


}
