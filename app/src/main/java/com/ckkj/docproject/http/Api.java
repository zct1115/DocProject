package com.ckkj.docproject.http;


import com.ckkj.docproject.bean.DownLoadData;
import com.ckkj.docproject.bean.cupboard.CupBoardData;
import com.ckkj.docproject.bean.cupboard.HumitureData;
import com.ckkj.docproject.bean.cupboard.ProtectData;
import com.ckkj.docproject.bean.examine.Entitive;
import com.ckkj.docproject.bean.examine.EntitiveDetils;
import com.ckkj.docproject.bean.examine.Preprocess;
import com.ckkj.docproject.bean.examine.Presernters;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.AbsCallback;

import  com.ckkj.docproject.bean.ArchivesCatalogue;
import  com.ckkj.docproject.bean.ArchivesData;
import  com.ckkj.docproject.bean.ArchivesDetail;
import  com.ckkj.docproject.bean.User;
import  com.ckkj.docproject.callback.DialogCallBack;


/**
 * Created by XISEVEN on 2017/5/26.
 */

public class Api {
    private static Urls sUrls;

    static{
        sUrls = new Urls();
    }

    //登录
    public static void login(String username, String password, DialogCallBack<User> callback) {
        OkGo.<User>post(sUrls.URL_LOGIN)
                .tag(sUrls.URL_LOGIN)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("username", username)
                .params("password", password)
                .execute(callback);
    }


    //登出
    public static void logout(AbsCallback<User> callback) {
        OkGo.<User>post(sUrls.URL_LOGOUT)
                .tag(sUrls.URL_LOGOUT)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //档案门类查询
    public static void findCatalogue(DialogCallBack<ArchivesCatalogue> callback) {
        OkGo.<ArchivesCatalogue>post(sUrls.URL_FINDCATALOGUE)
                .tag(sUrls.URL_FINDCATALOGUE)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);

    }

    //门类数据查询
    public static void findDatabyCatalogue(String tableName, String catalogueId,String pageNum, String limits, DialogCallBack<ArchivesData> callback) {
        OkGo.<ArchivesData>post(sUrls.URL_FINDDATABYCATALOGUE)
                .tag(sUrls.URL_FINDDATABYCATALOGUE)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("tableName", tableName)
                .params("catalogueId", catalogueId)
                .params("pageNum",pageNum)
                .params("limits", limits)
                .execute(callback);
    }

    //查看数据详情
    public static void findDatabyID(String id, String tableName, DialogCallBack<ArchivesDetail> callback) {
        OkGo.<ArchivesDetail>post(sUrls.URL_FINDDATABYID)
                .tag(sUrls.URL_FINDDATABYID)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", id)
                .params("tableName", tableName)
                .execute(callback);
    }

    //获取下载文件
    public static void downloadfile(String path, AbsCallback<DownLoadData> callback) {
        OkGo.<DownLoadData>post(path)
                .tag(path)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(callback);
    }

    //数据检索
    public static void search(String tableName, int pageNum, int limits, String searchCondition, DialogCallBack<ArchivesData> callback) {
        OkGo.<ArchivesData>post(sUrls.URL_SEARCH)
                .tag(sUrls.URL_SEARCH)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("tableName", tableName)
                .params("pageNum", pageNum)
                .params("limits", limits)
                .params("searchCondition", searchCondition)
                .execute(callback);
    }



     /*----------------------------------库房管理------------------------------------------*/

    /*柜架管理*/

    //柜架信息
    public static void cupboardinfo(String pageNum,String limits,DialogCallBack<CupBoardData> callback){
        OkGo.<CupBoardData>post(sUrls.URL_CUPBOARD)
                .tag(sUrls.URL_CUPBOARD)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("limits",limits)
                .execute(callback);
    }

    //增加柜架
    public static  void  addcupboardinfo(String name,String code,String location,String storeroomName,String storeroomCode,String remark,DialogCallBack<CupBoardData> callback){
        OkGo.<CupBoardData>post(sUrls.URL_ADDCUPBOARD)
                .tag(sUrls.URL_ADDCUPBOARD)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("name",name)
                .params("code",code)
                .params("location",location)
                .params("storeroomName",storeroomName)
                .params("storeroomCode",storeroomCode)
                .params("remark",remark)
                .execute(callback);
    }

    //修改柜架
    public  static  void  updatecupboardinfo(String id,String name,String code,String location,String storeroomName,String storeroomCode,String remark,DialogCallBack<CupBoardData> callback){
        OkGo.<CupBoardData>post(sUrls.URL_UPDATECUPBOARD)
                .tag(sUrls.URL_UPDATECUPBOARD)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id",id)
                .params("name",name)
                .params("code",code)
                .params("location",location)
                .params("storeroomName",storeroomName)
                .params("storeroomCode",storeroomCode)
                .params("remark",remark)
                .execute(callback);
    }

    //删除柜架
    public  static  void  deletecupboardinfo(String id,DialogCallBack<CupBoardData> callback){
        OkGo.<CupBoardData>post(sUrls.URL_DELETECUPBOARD)
                .tag(sUrls.URL_DELETECUPBOARD)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id",id)
                .execute(callback);
    }

    //搜索柜架
    public static void searchData(String pageNum,String limits,String Dataname,String Datavalues,DialogCallBack<CupBoardData> callback){
        OkGo.<CupBoardData>post(sUrls.URL_SEARCHCUPBOARD)
                .tag(sUrls.URL_SEARCHCUPBOARD)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("limits",limits)
                .params(Dataname,Datavalues)
                .execute(callback);

    }

    //提交离线柜架
    public static void putTaken(String cupBoards, DialogCallBack<CupBoardData> dialogCallBack) {
        OkGo.<CupBoardData>post(sUrls.URL_TAKEN)
                .tag(sUrls.URL_TAKEN)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("cupBoards", cupBoards)
                .execute(dialogCallBack);
    }


    /*温湿度管理*/


    //温湿度信息
    public static  void  humitureinfo(String pageNum,String limits,DialogCallBack<HumitureData> callback){
        OkGo.<HumitureData>post(sUrls.URL_HUMITURE)
                .tag(sUrls.URL_HUMITURE)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("limits",limits)
                .execute(callback);
    }

    //修改温湿度信息
    public static void updatehumitureinfo(String id, String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark, DialogCallBack<HumitureData> callback) {
        OkGo.<HumitureData>post(sUrls.URL_UPDHUMITURE)
                .tag(sUrls.URL_UPDHUMITURE)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id",id)
                .params("checkData", checkData)
                .params("amTemp", AmTemp)
                .params("amHumidty", AmHumidity)
                .params("pmTemp", PmTemp)
                .params("pmHumidty", PmHumidity)
                .params("inTemp", inTemp)
                .params("inHumidity", inHumidity)
                .params("outTemp", outTemp)
                .params("outHumidity", outHumidity)
                .params("takeSteps", Taken)
                .params("custodian", custodian)
                .params("remark", remark)
                .execute(callback);

    }
    //登记温湿度信息
    public static void addhumitureinfo(String checkData, String AmTemp, String AmHumidity, String PmTemp, String PmHumidity, String inTemp, String inHumidity, String outTemp, String outHumidity, String Taken, String custodian, String remark, DialogCallBack<HumitureData> callback) {
        OkGo.<HumitureData>post(sUrls.URL_ADDHUMITURE )
                .tag(sUrls.URL_ADDHUMITURE )
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("checkData",checkData)
                .params("amTemp", AmTemp)
                .params("amHumidty", AmHumidity)
                .params("pmTemp", PmTemp)
                .params("pmHumidty", PmHumidity)
                .params("inTemp", inTemp)
                .params("inHumidity", inHumidity)
                .params("outTemp", outTemp)
                .params("outHumidity", outHumidity)
                .params("takeSteps", Taken)
                .params("custodian", custodian)
                .params("remark", remark)
                .execute(callback);
    }

    //搜索温湿度信息
    public static void searchhumitureinfo(String checkData, String AmTemp, String AmHumidity,String PmTemp,String PmHumidity, String inTemp,String inHumidity, String outTemp, String outHumidity,String Taken, String custodian, String remark, DialogCallBack<HumitureData> callback) {
        OkGo.<HumitureData>post(sUrls.URL_SEARCHHUMITURE )
                .tag(sUrls.URL_SEARCHHUMITURE )
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("checkData",checkData)
                .params("amTemp", AmTemp)
                .params("amHumidty", AmHumidity)
                .params("pmTemp", PmTemp)
                .params("pmHumidty", PmHumidity)
                .params("inTemp", inTemp)
                .params("inHumidity", inHumidity)
                .params("outTemp", outTemp)
                .params("outHumidity", outHumidity)
                .params("takeSteps", Taken)
                .params("custodian", custodian)
                .params("remark", remark)
                .execute(callback);
    }

    /*保护登记*/

    //保护登记信息
    public static  void  protectinfo(String pageNum,String limits,DialogCallBack<ProtectData> callback){
        OkGo.<ProtectData>post(sUrls.URL_PROTECT)
                .tag(sUrls.URL_PROTECT)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("limits",limits)
                .execute(callback);
    }

    //修改保护登记信息
    public static void updateprotectinfo(String id, String storeroomCode, String storeroomName, String protectType, String protectTime, String protectResult, String operUser, DialogCallBack<ProtectData> callback) {
        OkGo.<ProtectData>post(sUrls.URL_UPDATEPROTECT)
                .tag(sUrls.URL_UPDATEPROTECT)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id",id)
                .params("storeroomCode",storeroomCode)
                .params("storeroomName",storeroomName)
                .params("protectType",protectType)
                .params("protectTime",protectTime)
                .params("protectResult",protectResult)
                .params("operUser",operUser)
                .execute(callback);
    }

    //增加保护登记信息
    public static void addprotectinfo(String storeroomCode, String storeroomName, String protectType, String protectTime, String protectResult, String operUser, DialogCallBack<ProtectData> callback) {
        OkGo.<ProtectData>post(sUrls.URL_ADDPROTECT)
                .tag(sUrls.URL_ADDPROTECT)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("storeroomCode",storeroomCode)
                .params("storeroomName",storeroomName)
                .params("protectType",protectType)
                .params("protectTime",protectTime)
                .params("protectResult",protectResult)
                .params("operUser",operUser)
                .execute(callback);
    }

    //查询保护登记

    public static void searchprotectinfo(String pageNum, String mLimits,String storeroomCode, String storeroomName, String protectType, String protectTime, String protectResult, String operUser, DialogCallBack<ProtectData> callback) {
        OkGo.<ProtectData>post(sUrls.URL_SEARCHPROTECT)
                .tag(sUrls.URL_SEARCHPROTECT)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("mLimits",mLimits)
                .params("storeroomCode",storeroomCode)
                .params("storeroomName",storeroomName)
                .params("protectType",protectType)
                .params("protectTime",protectTime)
                .params("protectResult",protectResult)
                .params("operUser",operUser)
                .execute(callback);
    }

    /*=====================================================电子文档审核============================================================*/

    //实体档案服务
    public static void entitiveinfo(String pageNum, String mLimits, DialogCallBack<Entitive> callback){
        OkGo.<Entitive>post(sUrls.URL_ENTITIVE)
                .tag(sUrls.URL_ENTITIVE)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("mLimits",mLimits)
                .execute(callback);
    }

    //审核权限接口
    public static void checked(String examine_processId,DialogCallBack<Entitive> callback){
        OkGo.<Entitive>post(sUrls.URL_CHECKED)
                .tag(sUrls.URL_CHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_processId",examine_processId)
                .execute(callback);
    }

    //逐级审核
    public static void nextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,String replicable,String accessTo,DialogCallBack<Entitive> callback){
        OkGo.<Entitive>post(sUrls.URL_NEXTCHECKED)
                .tag(sUrls.URL_NEXTCHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_id",examine_id)
                .params("examine_processId",examine_processId)
                .params("currentTaskId",currentTaskId)
                .params("auditState",auditState)
                .params("rejectReason",rejectReason)
                .params("replicable",replicable)
                .params("accessTo",accessTo)
                .execute(callback);
    }

    //明细
    public static void entitive_detils(String examine_processId,DialogCallBack<EntitiveDetils> callback){
        OkGo.<EntitiveDetils>post(sUrls.URL_DETILS)
                .tag(sUrls.URL_DETILS)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_processId",examine_processId)
                .execute(callback);
    }

    /*--------------------------------------------------------------------------------------------------*/

    //现行文件开放审核
    public static void presenterinfo(String pageNum, String mLimits, DialogCallBack<Presernters> callback){
        OkGo.<Presernters>post(sUrls.URL_PRESENTERS)
                .tag(sUrls.URL_PRESENTERS)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("mLimits",mLimits)
                .execute(callback);
    }

    //审核权限接口
    public static void presenterchecked(String examine_processId,DialogCallBack<Presernters> callback){
        OkGo.<Presernters>post(sUrls.URL_PRESENTER_CHECKED)
                .tag(sUrls.URL_PRESENTER_CHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_processId",examine_processId)
                .execute(callback);
    }

    //逐级审核
    public static void presenternextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,String allowDownload,DialogCallBack<Presernters> callback){
        OkGo.<Presernters>post(sUrls.URL_PRESENTER_NEXTCHECKED)
                .tag(sUrls.URL_PRESENTER_NEXTCHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_id",examine_id)
                .params("examine_processId",examine_processId)
                .params("currentTaskId",currentTaskId)
                .params("auditState",auditState)
                .params("rejectReason",rejectReason)
                .params("allowDownload",allowDownload)
                .execute(callback);
    }

    //明细
    public static void presenterdetils(String examine_processId,DialogCallBack<Presernters> callback){
        OkGo.<Presernters>post(sUrls.URL_PRESENTER_DETILS)
                .tag(sUrls.URL_PRESENTER_DETILS)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_processId",examine_processId)
                .execute(callback);
    }

    /*--------------------------------------------------------------------------------------------------*/

    //预归审核
    public static void preprocessinfo(String pageNum, String mLimits, DialogCallBack<Preprocess> callback){
        OkGo.<Preprocess>post(sUrls.URL_PREPROCESS)
                .tag(sUrls.URL_PREPROCESS)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("mLimits",mLimits)
                .execute(callback);
    }

    //审核权限接口
    public static void preprocesschecked(String examine_processId,DialogCallBack<Preprocess> callback){
        OkGo.<Preprocess>post(sUrls.URL_PREPROCESS_CHECKED)
                .tag(sUrls.URL_PREPROCESS_CHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_processId",examine_processId)
                .execute(callback);
    }

    //逐级审核
    public static void preprocessnextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,String allowDownload,DialogCallBack<Preprocess> callback){
        OkGo.<Preprocess>post(sUrls.URL_PREPROCESS_NEXTCHECKED)
                .tag(sUrls.URL_PREPROCESS_NEXTCHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_id",examine_id)
                .params("examine_processId",examine_processId)
                .params("currentTaskId",currentTaskId)
                .params("auditState",auditState)
                .params("rejectReason",rejectReason)
                .params("allowDownload",allowDownload)
                .execute(callback);
    }

    //明细
    public static void preprocessdetils(String examine_processId,DialogCallBack<Preprocess> callback){
        OkGo.<Preprocess>post(sUrls.URL_PREPROCESS_DETILS)
                .tag(sUrls.URL_PREPROCESS_DETILS)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_processId",examine_processId)
                .execute(callback);
    }

     /*--------------------------------------------------------------------------------------------------*/

    //预归库审核
    public static void preprocessstoreroominfo(String pageNum, String mLimits, DialogCallBack<Preprocess> callback){
        OkGo.<Preprocess>post(sUrls.URL_PREPROCESS)
                .tag(sUrls.URL_PREPROCESS)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("mLimits",mLimits)
                .execute(callback);
    }

    //审核权限接口
    public static void preprocessstoreroomchecked(String examine_processId,DialogCallBack<Preprocess> callback){
        OkGo.<Preprocess>post(sUrls.URL_PREPROCESS_CHECKED)
                .tag(sUrls.URL_PREPROCESS_CHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_processId",examine_processId)
                .execute(callback);
    }

    //逐级审核
    public static void preprocessstoreroomnextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,DialogCallBack<Preprocess> callback){
        OkGo.<Preprocess>post(sUrls.URL_PREPROCESS_NEXTCHECKED)
                .tag(sUrls.URL_PREPROCESS_NEXTCHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_id",examine_id)
                .params("examine_processId",examine_processId)
                .params("currentTaskId",currentTaskId)
                .params("auditState",auditState)
                .params("rejectReason",rejectReason)
                .execute(callback);
    }

     /*--------------------------------------------------------------------------------------------------*/

    //介质审核
    public static void mediuminfo(String pageNum, String mLimits, DialogCallBack<Medium> callback){
        OkGo.<Medium>post(sUrls.URL_PREPROCESS)
                .tag(sUrls.URL_PREPROCESS)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("pageNum",pageNum)
                .params("mLimits",mLimits)
                .execute(callback);
    }

    //审核权限接口
    public static void mediumchecked(String examine_processId,DialogCallBack<Medium> callback){
        OkGo.<Medium>post(sUrls.URL_PREPROCESS_CHECKED)
                .tag(sUrls.URL_PREPROCESS_CHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_processId",examine_processId)
                .execute(callback);
    }

    //逐级审核
    public static void mediumnextchecked(String examine_id,String examine_processId,String currentTaskId,String auditState,String rejectReason,DialogCallBack<Medium> callback){
        OkGo.<Medium>post(sUrls.URL_PREPROCESS_NEXTCHECKED)
                .tag(sUrls.URL_PREPROCESS_NEXTCHECKED)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("examine_id",examine_id)
                .params("examine_processId",examine_processId)
                .params("currentTaskId",currentTaskId)
                .params("auditState",auditState)
                .params("rejectReason",rejectReason)
                .execute(callback);
    }

}
