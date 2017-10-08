package com.ckkj.docproject.bean.cupboard;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public class ProtectData implements Serializable {

    /**
     * total : 3
     * status : 1
     * msg : 查询成功
     * data : [{"id":"d6891db03fd04053a0478ab1b0423e2a","operUser":"姚","protectTime":"2017","updateTime":"Wed Aug 09 15:48:18 CST 2017","protectType":"防鼠","storeroomName":"添加的库房17","addUser":"admin","storeroomCode":"K001","protectResult":"成功","addTime":"Wed Aug 09 14:03:07 CST 2017","updateUser":"admin"},{"id":"3f300ea9edbb496382ee5cd7e44e74c9","operUser":"姚","protectTime":"姚1","updateTime":"Wed Aug 09 15:53:59 CST 2017","protectType":"姚2","storeroomName":"姚3","addUser":"admin","storeroomCode":"姚4","protectResult":"姚5","addTime":"Fri Aug 04 18:26:09 CST 2017","updateUser":"admin"},{"id":"447433360b1a4133af356a20b638f11f","operUser":"2131","protectTime":"1213","updateTime":"","protectType":"A","storeroomName":"机柜库房","addUser":"admin","storeroomCode":"k001","protectResult":"1231","addTime":"Fri Aug 04 18:25:58 CST 2017","updateUser":""}]
     */

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

    public static class DataBean implements Serializable {
        /**
         * id : d6891db03fd04053a0478ab1b0423e2a
         * operUser : 姚
         * protectTime : 2017
         * updateTime : Wed Aug 09 15:48:18 CST 2017
         * protectType : 防鼠
         * storeroomName : 添加的库房17
         * addUser : admin
         * storeroomCode : K001
         * protectResult : 成功
         * addTime : Wed Aug 09 14:03:07 CST 2017
         * updateUser : admin
         */

        private String id;
        private String operUser;
        private String protectTime;
        private String updateTime;
        private String protectType;
        private String storeroomName;
        private String addUser;
        private String storeroomCode;
        private String protectResult;
        private String addTime;
        private String updateUser;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOperUser() {
            return operUser;
        }

        public void setOperUser(String operUser) {
            this.operUser = operUser;
        }

        public String getProtectTime() {
            return protectTime;
        }

        public void setProtectTime(String protectTime) {
            this.protectTime = protectTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getProtectType() {
            return protectType;
        }

        public void setProtectType(String protectType) {
            this.protectType = protectType;
        }

        public String getStoreroomName() {
            return storeroomName;
        }

        public void setStoreroomName(String storeroomName) {
            this.storeroomName = storeroomName;
        }

        public String getAddUser() {
            return addUser;
        }

        public void setAddUser(String addUser) {
            this.addUser = addUser;
        }

        public String getStoreroomCode() {
            return storeroomCode;
        }

        public void setStoreroomCode(String storeroomCode) {
            this.storeroomCode = storeroomCode;
        }

        public String getProtectResult() {
            return protectResult;
        }

        public void setProtectResult(String protectResult) {
            this.protectResult = protectResult;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }
    }
}
