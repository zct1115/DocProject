package com.ckkj.docproject.bean;

import java.io.Serializable;

/**
 * Created by zct11 on 2017/8/13.
 */

public class DownLoadData implements Serializable {

    /**
     * url : http://192.168.1.116:8080//static/temp/download/4423d13407a74f60baa13d9973b37645.jpg
     * status : 1
     * msg : 查询成功
     */

    private String url;
    private String status;
    private String msg;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
