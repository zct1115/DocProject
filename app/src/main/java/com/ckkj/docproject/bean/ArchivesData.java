package com.ckkj.docproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XISEVEN on 2017/5/30.
 */

public class ArchivesData implements Serializable {

    /**
     * total : 3
     * status : 1
     * msg : 查询成功
     * data : [{"id":"d125cba9010b409f877f4d8852994569","title":"关于提供中小企业融资需求信息的通知","identificationCn":"档号","identification":"无/A/10年/1992/0001","tableName":"t_guidangwenjianmulu","titleCn":"文件题名"},{"id":"3f4782cd1e6a405883e1a40d443eea22","title":"关于《贯彻落实〈以扩大开放推动汕头科学发展工作方案〉实施意见（征求意见稿）》意见的复函","identificationCn":"档号","identification":"无/A/10年/1992/0002","tableName":"t_guidangwenjianmulu","titleCn":"文件题名"},{"id":"1dd4037784b14ca29470ecc9ed0cd02f","title":"通知","identificationCn":"档号","identification":"无/A/10年/1992/0003","tableName":"t_guidangwenjianmulu","titleCn":"文件题名"}]
    **/
    private int total;
    private String status;
    private String msg;
    private int pageNum;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : d125cba9010b409f877f4d8852994569
         * title : 关于提供中小企业融资需求信息的通知
         * identificationCn : 档号
         * identification : 无/A/10年/1992/0001
         * tableName : t_guidangwenjianmulu
         * titleCn : 文件题名
         */

        private String id;
        private String title;
        private String identificationCn;
        private String identification;
        private String tableName;
        private String titleCn;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIdentificationCn() {
            return identificationCn;
        }

        public void setIdentificationCn(String identificationCn) {
            this.identificationCn = identificationCn;
        }

        public String getIdentification() {
            return identification;
        }

        public void setIdentification(String identification) {
            this.identification = identification;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }
    }
}
