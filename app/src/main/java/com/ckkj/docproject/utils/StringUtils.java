package com.ckkj.docproject.utils;

/**
 * Created by zct11 on 2017/8/6.
 */

public class StringUtils {

    /*字符串去除逗号分隔符
    * */

    public static String[] changesize(String doc){
        String[] strArray=null;
        strArray = doc.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }








}
