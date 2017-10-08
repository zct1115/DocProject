package com.ckkj.docproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 档案详情
 * Created by XISEVEN on 2017/5/30.
 */

public class ArchivesDetail implements Serializable{


    /**
     * electronLocal : 12
     * electron_wenjian : 谁/我去的期望的.png
     * status :
     * msg : 查询成功
     * data : ["档号 : 无/A/10年/1992/0001","全宗号 : 无","全宗单位 : 无","年度 : 1992","保管期限 : 10年","类别 : A","件号 : 0001","文件编号 : 汕金[2009]16号","文件题名 : 关于提供中小企业融资需求信息的通知","文件页数 : 7","文件日期 : 2009.03.20","密级 : 无密","电子文件 : 谁/我去的期望的.png","备注 : ","时间及输入员号 : ","机构 : ","主题词 : ","附注 : ","摘要 : ","文件类型 : ","人名 : ","盒号 : ","责任者 : 市金融服务办/市经贸局","分类号 : ","是否开放 : 是","页号 : "]
     */

    private int electronLocal;
    private String electron_wenjian;
    private String status;
    private String msg;
    private List<String> data;

    public int getElectronLocal() {
        return electronLocal;
    }

    public void setElectronLocal(int electronLocal) {
        this.electronLocal = electronLocal;
    }

    public String getElectron_wenjian() {
        return electron_wenjian;
    }

    public void setElectron_wenjian(String electron_wenjian) {
        this.electron_wenjian = electron_wenjian;
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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
