package com.greendao.entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "HUMITURE".
 */
@Entity
public class Humiture {

    @Id
    private Long id;
    private String checkData;
    private String AmTemp;
    private String AmHumidity;
    private String PmTemp;
    private String PmHumidity;
    private String inTemp;
    private String inHumidity;
    private String outTemp;
    private String outHumidity;
    private String Taken;
    private String custodian;
    private String remark;

    @Generated
    public Humiture() {
    }

    public Humiture(Long id) {
        this.id = id;
    }

    @Generated
    public Humiture(Long id, String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark) {
        this.id = id;
        this.checkData = checkData;
        this.AmTemp = AmTemp;
        this.AmHumidity = AmHumidity;
        this.PmTemp = PmTemp;
        this.PmHumidity = PmHumidity;
        this.inTemp = inTemp;
        this.inHumidity = inHumidity;
        this.outTemp = outTemp;
        this.outHumidity = outHumidity;
        this.Taken = Taken;
        this.custodian = custodian;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckData() {
        return checkData;
    }

    public void setCheckData(String checkData) {
        this.checkData = checkData;
    }

    public String getAmTemp() {
        return AmTemp;
    }

    public void setAmTemp(String AmTemp) {
        this.AmTemp = AmTemp;
    }

    public String getAmHumidity() {
        return AmHumidity;
    }

    public void setAmHumidity(String AmHumidity) {
        this.AmHumidity = AmHumidity;
    }

    public String getPmTemp() {
        return PmTemp;
    }

    public void setPmTemp(String PmTemp) {
        this.PmTemp = PmTemp;
    }

    public String getPmHumidity() {
        return PmHumidity;
    }

    public void setPmHumidity(String PmHumidity) {
        this.PmHumidity = PmHumidity;
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

    public String getOutHumidity() {
        return outHumidity;
    }

    public void setOutHumidity(String outHumidity) {
        this.outHumidity = outHumidity;
    }

    public String getTaken() {
        return Taken;
    }

    public void setTaken(String Taken) {
        this.Taken = Taken;
    }

    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
