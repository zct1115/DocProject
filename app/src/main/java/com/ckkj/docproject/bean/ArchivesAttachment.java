package com.ckkj.docproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XISEVEN on 2017/5/30.
 */

public class ArchivesAttachment implements Serializable{




    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * name : 0009_1.jpg
         * path : http://192.168.88.89:7272/other/t_wenshuanjuanmulu/0009_1.jpg
         */

        //电子文件的名称
        private String name;
        //文件的路径
        private String path;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

    }
}
