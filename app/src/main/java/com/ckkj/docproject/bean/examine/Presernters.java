package com.ckkj.docproject.bean.examine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ting on 2017/9/14.
 */

public class Presernters implements Serializable {

    /**
     * total : 2
     * pageNum : 1
     * status : 1
     * msg : 数据获取成功
     * data : [{"examine_id":"212dc61014624d64831b22378b91209c","rejectReason":"","addUser":"用户","examine_processId":"1325088","dataCount":"1","preTaskName":"","taskName":"任务1","currentTaskId":"1325092","updateUser":"","addTime":"2017-09-12 04:45:25","classifyName":"归档文件目录"},{"examine_id":"d320713e8c6a4daf8960747a69d7f467","rejectReason":"","addUser":"用户","examine_processId":"1322570","dataCount":"1","preTaskName":"","taskName":"2","currentTaskId":"1322574","updateUser":"","addTime":"2017-09-11 09:55:17","classifyName":"归档文件目录"}]
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

    public static class DataBean  implements Serializable  {
        /**
         * examine_id : 212dc61014624d64831b22378b91209c
         * rejectReason :
         * addUser : 用户
         * examine_processId : 1325088
         * dataCount : 1
         * preTaskName :
         * taskName : 任务1
         * currentTaskId : 1325092
         * updateUser :
         * addTime : 2017-09-12 04:45:25
         * classifyName : 归档文件目录
         */

        private String examine_id;
        private String rejectReason;
        private String addUser;
        private String examine_processId;
        private String dataCount;
        private String preTaskName;
        private String taskName;
        private String currentTaskId;
        private String updateUser;
        private String addTime;
        private String classifyName;

        public String getExamine_id() {
            return examine_id;
        }

        public void setExamine_id(String examine_id) {
            this.examine_id = examine_id;
        }

        public String getRejectReason() {
            return rejectReason;
        }

        public void setRejectReason(String rejectReason) {
            this.rejectReason = rejectReason;
        }

        public String getAddUser() {
            return addUser;
        }

        public void setAddUser(String addUser) {
            this.addUser = addUser;
        }

        public String getExamine_processId() {
            return examine_processId;
        }

        public void setExamine_processId(String examine_processId) {
            this.examine_processId = examine_processId;
        }

        public String getDataCount() {
            return dataCount;
        }

        public void setDataCount(String dataCount) {
            this.dataCount = dataCount;
        }

        public String getPreTaskName() {
            return preTaskName;
        }

        public void setPreTaskName(String preTaskName) {
            this.preTaskName = preTaskName;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getCurrentTaskId() {
            return currentTaskId;
        }

        public void setCurrentTaskId(String currentTaskId) {
            this.currentTaskId = currentTaskId;
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

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }
    }
}
