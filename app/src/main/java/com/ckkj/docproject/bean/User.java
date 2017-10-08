package com.ckkj.docproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XISEVEN on 2017/5/26.
 */

public class User implements Serializable{


    /**
     * result : success
     * status : 1
     * data : [{"id":"2","username":"admin","showName":"用户","sessionid":"7B5133D00E8477A4837E84CAADE79C1A"}]
     * msg : 验证成功
     */

    private String result;
    private String status;
    private String msg;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
         * id : 2
         * username : admin
         * showName : 用户
         * sessionid : 7B5133D00E8477A4837E84CAADE79C1A
         */

        private String id;
        private String username;
        private String showName;
        private String sessionid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getShowName() {
            return showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }

        public String getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
            this.sessionid = sessionid;
        }
    }
}
