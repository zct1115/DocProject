package com.ckkj.docproject.bean.cupboard;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public class CupBoardData implements Serializable {

    /**
     * total : 4
     * status : 1
     * msg : 查询成功
     * data : [{"id":"19295f477b3a402d83596983b0c1d265","updateTime":"","remark":"","location":"四楼","name":"测试柜架1","storeroomName":"406房","addUser":"admin","storeroomCode":"07K","code":"k004","addTime":"Mon Aug 07 17:31:03 CST 2017","updateUser":""},{"id":"bd20d5bcdab8423d9834d157ec6e24d9","updateTime":"","remark":"","location":"iiqw","name":"23","storeroomName":"idfsiao","addUser":"admin","storeroomCode":"090sa","code":"k003","addTime":"Mon Aug 07 17:12:22 CST 2017","updateUser":""},{"id":"6a2142706adc4d94b0f33c09b51d2f4f","updateTime":"","remark":"","location":"三楼第二排","name":"技术2","storeroomName":"机柜库房","addUser":"admin","storeroomCode":"j001","code":"k002","addTime":"Fri Aug 04 17:55:12 CST 2017","updateUser":""},{"id":"6d7e5bc8cbb44b27976bd1dda5b6cebc","updateTime":"Tue Aug 08 10:32:20 CST 2017","remark":"\"\"","location":"三第二排","name":"术","storeroomName":"机房","addUser":"admin","storeroomCode":"j0021","code":"k0012","addTime":"Fri Aug 04 17:54:02 CST 2017","updateUser":"admin"}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 19295f477b3a402d83596983b0c1d265
         * updateTime :
         * remark :
         * location : 四楼
         * name : 测试柜架1
         * storeroomName : 406房
         * addUser : admin
         * storeroomCode : 07K
         * code : k004
         * addTime : Mon Aug 07 17:31:03 CST 2017
         * updateUser :
         */

        private String id;
        private String updateTime;
        private String remark;
        private String location;
        private String name;
        private String storeroomName;
        private String addUser;
        private String storeroomCode;
        private String code;
        private String addTime;
        private String updateUser;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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
