package com.demo;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    public static byte[] bytes = new byte[1024 * 1024 * 2];

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        while (true){
            list.add(bytes);
        }
    }


}
