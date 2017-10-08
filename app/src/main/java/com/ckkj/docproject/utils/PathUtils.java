package com.ckkj.docproject.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by XISEVEN on 2017/6/12.
 */

public class PathUtils {
    //  /DocManage/{tableName}
    public static String getPath(String tableName) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                "DocManage" + File.separator + tableName;
    }

    //  /DocManage/{tableName}/{fileName}
    public static String getFilePath(String tableName,String fileName) {
        return getPath(tableName) + File.separator + fileName;
    }
}
