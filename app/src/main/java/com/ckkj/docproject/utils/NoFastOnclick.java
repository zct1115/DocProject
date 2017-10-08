package com.ckkj.docproject.utils;

/**
 * Created by zct11 on 2017/8/21.
 */

public class NoFastOnclick {
//    上次点击的时间
    private static long lastClickTime=0;
//    时间间隔
    private static int spaceTime=1000;

    public static boolean isFastClick(){
        long currenTime=System.currentTimeMillis();//当前时间

        boolean isAllow;

        if(currenTime-lastClickTime>spaceTime){
            isAllow=false;
        }else {
            isAllow=true;
        }
        lastClickTime=currenTime;
        return isAllow;
    }
}
