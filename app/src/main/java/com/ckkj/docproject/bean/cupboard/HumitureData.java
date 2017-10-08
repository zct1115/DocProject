package com.ckkj.docproject.bean.cupboard;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zct11 on 2017/8/9.
 */

public class HumitureData implements Serializable {


    /**
     * total : 2
     * pageNum : 1
     * status : 1
     * msg : 查询成功
     * data : [{"outHumidity":"","takeSteps":"231312","updateTime":"","remark":"","addUser":"admin","checkTime":"","id":"86e2f9a372fb46ab8ab2621fd15111c2","custodian":"","pmHumidty":"31","pmTemp":"21312","inTemp":"","inHumidity":"","outTemp":"","amHumidty":"31231313","checkData":"2017.08.17","amTemp":"312312","updateUser":"","addTime":"Thu Aug 17 15:09:43 CST 2017"},{"outHumidity":"","takeSteps":"13123","updateTime":"","remark":"","addUser":"admin","checkTime":"","id":"d9275b2435084d70ac0e8bbd665aebf6","custodian":"","pmHumidty":"313213","pmTemp":"1312","inTemp":"","inHumidity":"","outTemp":"","amHumidty":"213","checkData":"2017.08.17","amTemp":"213","updateUser":"","addTime":"Thu Aug 17 14:54:34 CST 2017"}]
     */

    private int total;
    private int pageNum;
    private String status;
    private String msg;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
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
         * outHumidity :
         * takeSteps : 231312
         * updateTime :
         * remark :
         * addUser : admin
         * checkTime :
         * id : 86e2f9a372fb46ab8ab2621fd15111c2
         * custodian :
         * pmHumidty : 31
         * pmTemp : 21312
         * inTemp :
         * inHumidity :
         * outTemp :
         * amHumidty : 31231313
         * checkData : 2017.08.17
         * amTemp : 312312
         * updateUser :
         * addTime : Thu Aug 17 15:09:43 CST 2017
         */

        private String outHumidity;
        private String takeSteps;
        private String updateTime;
        private String remark;
        private String addUser;
        private String checkTime;
        private String id;
        private String custodian;
        private String pmHumidty;
        private String pmTemp;
        private String inTemp;
        private String inHumidity;
        private String outTemp;
        private String amHumidty;
        private String checkData;
        private String amTemp;
        private String updateUser;
        private String addTime;

        public String getOutHumidity() {
            return outHumidity;
        }

        public void setOutHumidity(String outHumidity) {
            this.outHumidity = outHumidity;
        }

        public String getTakeSteps() {
            return takeSteps;
        }

        public void setTakeSteps(String takeSteps) {
            this.takeSteps = takeSteps;
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

        public String getAddUser() {
            return addUser;
        }

        public void setAddUser(String addUser) {
            this.addUser = addUser;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustodian() {
            return custodian;
        }

        public void setCustodian(String custodian) {
            this.custodian = custodian;
        }

        public String getPmHumidty() {
            return pmHumidty;
        }

        public void setPmHumidty(String pmHumidty) {
            this.pmHumidty = pmHumidty;
        }

        public String getPmTemp() {
            return pmTemp;
        }

        public void setPmTemp(String pmTemp) {
            this.pmTemp = pmTemp;
        }

        public String getInTemp() {
            return inTemp;
        }

        public void setInTemp(String inTemp) {
            this.inTemp = inTemp;
        }

        public String getInHumidity() {
            return inHumidity;
        }

        public void setInHumidity(String inHumidity) {
            this.inHumidity = inHumidity;
        }

        public String getOutTemp() {
            return outTemp;
        }

        public void setOutTemp(String outTemp) {
            this.outTemp = outTemp;
        }

        public String getAmHumidty() {
            return amHumidty;
        }

        public void setAmHumidty(String amHumidty) {
            this.amHumidty = amHumidty;
        }

        public String getCheckData() {
            return checkData;
        }

        public void setCheckData(String checkData) {
            this.checkData = checkData;
        }

        public String getAmTemp() {
            return amTemp;
        }

        public void setAmTemp(String amTemp) {
            this.amTemp = amTemp;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }
    }
}
