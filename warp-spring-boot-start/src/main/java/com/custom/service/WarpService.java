package com.custom.service;

import lombok.AllArgsConstructor;
/**
 * 自定义starter的核心业务类
 * 需求
 *      给用户的字符串添加前缀和后缀
 */
@AllArgsConstructor
public class WarpService {

    private String befor;

    private String after;

    public String warpString(String word){
        return befor + word + after;
    }


}
