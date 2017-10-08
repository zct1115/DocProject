package com.ckkj.docproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XISEVEN on 2017/5/30.
 */

public class ArchivesCatalogue implements Serializable {


    /**
     * total : 24
     * data : [{"id":"7e6ff171ba2442379cb4ba0532c6bf0f","level":1,"pid_sort":0,"sort":5,"tableNameCH":"文书档案管理","tableName":"t_wenshudanganguanli","pid":"8c45d0cda20e439f93ed4b73a76d0622"},{"id":"28053836c495484fbaf5deb7c3ddea45","level":2,"pid_sort":0,"sort":8,"tableNameCH":"归档文件目录","tableName":"t_guidangwenjianmulu","pid":"45d52e4012a34814bd6a27f3b6e03923"},{"id":"45d52e4012a34814bd6a27f3b6e03923","level":2,"pid_sort":0,"sort":9,"tableNameCH":"归档文件管理","tableName":"t_guidangwenjianguanli","pid":"8c45d0cda20e439f93ed4b73a76d0622"},{"id":"e40cec731bc347be8307655a80cfc38c","level":2,"pid_sort":0,"sort":11,"tableNameCH":"文书案卷目录","tableName":"t_wenshuanjuanmulu","pid":"7e6ff171ba2442379cb4ba0532c6bf0f"},{"id":"8c45d0cda20e439f93ed4b73a76d0622","level":1,"pid_sort":0,"sort":16,"tableNameCH":"档案管理","tableName":"t_danganguanli","pid":"0"},{"id":"4617ea742d4f4ce19602e9021221dfcb","level":2,"pid_sort":0,"sort":19,"tableNameCH":"文书卷内目录","tableName":"t_wenshujuanneimulu","pid":"7e6ff171ba2442379cb4ba0532c6bf0f"},{"id":"2a64f8216001459492cf8e8c38975079","level":1,"pid_sort":0,"sort":42,"tableNameCH":"编研管理","tableName":"t_bianyanguanli","pid":"0"},{"id":"a2e1facd135844118cba9583c531d68a","level":2,"pid_sort":0,"sort":44,"tableNameCH":"大事记目录","tableName":"t_dashijimulu","pid":"2a64f8216001459492cf8e8c38975079"},{"id":"4f2d6c4336ed477ca0580370d88ebacb","level":2,"pid_sort":0,"sort":45,"tableNameCH":"组织沿革目录","tableName":"t_zuzhiyangemulu","pid":"2a64f8216001459492cf8e8c38975079"},{"id":"c62f4330b60d442cb3c937a13d52b407","level":2,"pid_sort":0,"sort":46,"tableNameCH":"专题目录","tableName":"t_zhuantimulu","pid":"2a64f8216001459492cf8e8c38975079"},{"id":"eca468fabdc14166bde4a6c31b4a95dd","level":2,"pid_sort":0,"sort":50,"tableNameCH":"会计档案","tableName":"t_kuaijidangan","pid":"8c45d0cda20e439f93ed4b73a76d0622"},{"id":"3747e07987d9479692715b5f2135c072","level":3,"pid_sort":0,"sort":51,"tableNameCH":"会计档案目录","tableName":"t_kuaijidanganmulu","pid":"eca468fabdc14166bde4a6c31b4a95dd"},{"id":"b08825d09efe4d3088ac49fcc1db85b6","level":3,"pid_sort":0,"sort":52,"tableNameCH":"会计报表卷内目录","tableName":"t_kuaijibaobiaojuanneimulu","pid":"eca468fabdc14166bde4a6c31b4a95dd"},{"id":"206963329902450f841f0402bc86d95b","level":2,"pid_sort":0,"sort":53,"tableNameCH":"声像档案","tableName":"t_shengxiangdangan","pid":"8c45d0cda20e439f93ed4b73a76d0622"},{"id":"97b1290befbe424b84e33a9b744e93c5","level":3,"pid_sort":0,"sort":54,"tableNameCH":"声像案卷目录","tableName":"t_shengxianganjuanmulu","pid":"206963329902450f841f0402bc86d95b"},{"id":"ee34d9e3c59b41eca5d7da280584435d","level":3,"pid_sort":0,"sort":55,"tableNameCH":"声像卷内目录","tableName":"t_shengxiangjuanneimulu","pid":"206963329902450f841f0402bc86d95b"},{"id":"0c53cb3bd15e4a498383d683b9ff49d8","level":2,"pid_sort":0,"sort":56,"tableNameCH":"实物档案","tableName":"t_shiwudangan","pid":"8c45d0cda20e439f93ed4b73a76d0622"},{"id":"e229c5c5a43343e99f4ebbe71d81dc2d","level":3,"pid_sort":0,"sort":57,"tableNameCH":"实物档案目录","tableName":"t_shiwudanganmulu","pid":"0c53cb3bd15e4a498383d683b9ff49d8"},{"id":"1839317953e04863a55378ef2a905384","level":2,"pid_sort":0,"sort":58,"tableNameCH":"婚姻档案","tableName":"t_hunyindangan","pid":"8c45d0cda20e439f93ed4b73a76d0622"},{"id":"0e39e85bb87b4977ae8ed023f9c75d66","level":3,"pid_sort":0,"sort":59,"tableNameCH":"婚姻档案目录","tableName":"t_hunyindanganmulu","pid":"1839317953e04863a55378ef2a905384"},{"id":"e558c6784b8a4f4199ef4627123329af","level":3,"pid_sort":0,"sort":60,"tableNameCH":"婚姻卷内目录","tableName":"t_hunyinjuanneimulu","pid":"1839317953e04863a55378ef2a905384"},{"id":"ac407dde59d54b51bc7544e6e25e0389","level":2,"pid_sort":0,"sort":61,"tableNameCH":"基建档案","tableName":"t_jijiandangan","pid":"8c45d0cda20e439f93ed4b73a76d0622"},{"id":"9aa071a705b54b57a5f559bfbbb4f72e","level":3,"pid_sort":0,"sort":62,"tableNameCH":"基建档案目录","tableName":"t_jijiandanganmulu","pid":"ac407dde59d54b51bc7544e6e25e0389"},{"id":"1c21ba64286745fbb4b9f5164df7e0dd","level":3,"pid_sort":0,"sort":63,"tableNameCH":"基建卷内目录","tableName":"t_jijianjuanneimulu","pid":"ac407dde59d54b51bc7544e6e25e0389"}]
     * status : 1
     * msg : 查询成功
     */

    private int total;
    private int status;
    private String msg;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
         * id : 7e6ff171ba2442379cb4ba0532c6bf0f
         * level : 1
         * pid_sort : 0
         * sort : 5
         * tableNameCH : 文书档案管理
         * tableName : t_wenshudanganguanli
         * pid : 8c45d0cda20e439f93ed4b73a76d0622
         */

        private String id;
        private int level;
        private int pid_sort;
        private int sort;
        private String tableNameCH;
        private String tableName;
        private String pid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getPid_sort() {
            return pid_sort;
        }

        public void setPid_sort(int pid_sort) {
            this.pid_sort = pid_sort;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTableNameCH() {
            return tableNameCH;
        }

        public void setTableNameCH(String tableNameCH) {
            this.tableNameCH = tableNameCH;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }
    }
}
