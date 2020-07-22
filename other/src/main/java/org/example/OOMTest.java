package org.example;


import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    private static byte[] bytes = new byte[1024 * 1024 * 200];

    public static void main( String[] args ){
        List<Object> list = new ArrayList<>();
        while (true){
            list.add(bytes);
        }
    }


}
