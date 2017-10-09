package com.ckkj.docproject.http;


import  com.ckkj.docproject.app.App;
import  com.ckkj.docproject.utils.SPUtils;

/**
 * Created by XISEVEN on 2017/5/26.
 */

public class Urls {
    public static String sBase_url;


    static {
        sBase_url = (String) SPUtils.getParam(App.getContext(), "BASE_URL", "http://192.168.103.66:8080/");
    }

    private String BASE_URL = sBase_url;
    //登录
    public final String URL_LOGIN = BASE_URL + "app_login";
    //退出登录
    public final String URL_LOGOUT = BASE_URL + "system_logout";

    /*----------------------------------检索------------------------------------------*/

    //档案门类查询
    public final String URL_FINDCATALOGUE = BASE_URL +"app/appController/getCatalogueTree";
    //门类数据查询
    public final String URL_FINDDATABYCATALOGUE = BASE_URL + "app/appController/findDatabyCatalogue";
    //查看数据详情
    public final String URL_FINDDATABYID = BASE_URL + "app/appController/findDataByIdAndTableEnName";

    //查看或下载电子文件
    public final String URL_DOWNLOAD = BASE_URL + "app/appController/download";
    //数据检索接口
    public final String URL_SEARCH = BASE_URL + "app/appController/search";



    /*----------------------------------库房管理------------------------------------------*/

    /*柜架管理*/

    //柜架信息
    public final String URL_CUPBOARD = BASE_URL +"app/appGuijiaguanliController/findData";

    //增加柜架
    public final String URL_ADDCUPBOARD = BASE_URL +"app/appGuijiaguanliController/add";

    //修改柜架
    public final String URL_UPDATECUPBOARD = BASE_URL +"app/appGuijiaguanliController/edit";

    //删除柜架
    public final String URL_DELETECUPBOARD = BASE_URL +"app/appGuijiaguanliController/delete";

    //搜索柜架
    public final String URL_SEARCHCUPBOARD = BASE_URL +"app/appGuijiaguanliController/search";

    //提交离线柜架信息
    public final String URL_TAKEN = BASE_URL + "app/appGuijiaguanliController/addList";

    /*温湿度管理*/

    //温湿度信息
    public final String URL_HUMITURE = BASE_URL +"app/appWenshiduController/findData";

    //增加温湿度信息
    public final String URL_ADDHUMITURE = BASE_URL +"app/appWenshiduController/add";

    //修改温湿度信息
    public final String URL_UPDHUMITURE = BASE_URL +"app/appWenshiduController/edit";

    //删除温湿度信息
    public final String URL_DELETEHUMITURE = BASE_URL +"app/appWenshiduController/delete";

    //搜索温湿度信息
    public final String URL_SEARCHHUMITURE = BASE_URL +"app/appWenshiduController/search";



    /*保护登记*/

    //保护登记信息
    public final String URL_PROTECT = BASE_URL +"app/appBaohudengjiController/findData";

    //增加保护登记信息
    public final String URL_ADDPROTECT = BASE_URL +"app/appBaohudengjiController/add";

    //修改保护登记信息
    public final String URL_UPDATEPROTECT = BASE_URL +"app/appBaohudengjiController/edit";

    //删除保护登记信息
    public final String URL_DELETEPROTECT = BASE_URL +"app/appBaohudengjiController/delete";

    //搜索保护登记信息
    public final String URL_SEARCHPROTECT = BASE_URL +"app/appBaohudengjiController/search";

   /*=====================================电子文档审核==========================================================*/

   //实体档案服务
    public final String URL_ENTITIVE=BASE_URL+"app/entityFileAuditingAppController/getAudit";

    //审核接口
    public final String URL_CHECKED=BASE_URL+"app/entityFileAuditingAppController/checkExamineUser";

    //逐级审核
    public final String URL_NEXTCHECKED=BASE_URL+"app/entityFileAuditingAppController/auditTask";

    //明细
    public final String URL_DETILS=BASE_URL+"app/entityFileAuditingAppController/findClassifySpellAndNameAndIds";


    /*---------------------------------------------------------------------------------------------------------*/
    //现行文件开放审核
    public final String URL_PRESENTERS=BASE_URL+"app/currentFileOpenReviewAppController/getAudit";

    //审核权限接口
    public final String URL_PRESENTER_CHECKED="app/currentFileOpenReviewAppController/checkExamineUser";

    //逐级审核
    public final String URL_PRESENTER_NEXTCHECKED="app/currentFileOpenReviewAppController/auditTask";

    //明细
    public final String URL_PRESENTER_DETILS=BASE_URL+"app/currentFileOpenReviewAppController/findDataList";


    /*---------------------------------------------------------------------------------------------------------*/
    //预归审核
    public final String URL_PREPROCESS=BASE_URL+"app/prepareAuditAppController/getAudit";

    //审核权限接口
    public final String URL_PREPROCESS_CHECKED="app/prepareAuditAppController/checkExamineUser";

    //逐级审核
    public final String URL_PREPROCESS_NEXTCHECKED="app/prepareAuditAppController/auditTask";

    //明细
    public final String URL_PREPROCESS_DETILS=BASE_URL+"";

    /*---------------------------------------------------------------------------------------------------------*/
    //预归库审核
    public final String URL_PREPROCESS_STOREROOM=BASE_URL+"app/prepareLibraryAppController/getAudit";

    //审核权限接口
    public final String URL_PREPROCESS_STOREROOM_CHECKED="app/prepareLibraryAppController/checkExamineUser";

    //逐级审核
    public final String URL_PREPROCESS_STOREROOM_NEXTCHECKED="app/prepareLibraryAppController/auditTask";

    /*---------------------------------------------------------------------------------------------------------*/
    //介质审核
    public final String URL_MEDIUM=BASE_URL+"app/mediumAppController/getAudit";

    //审核权限接口
    public final String URL_MEDIUM_CHECKED="app/prepareLibraryAppController/checkExamineUser";

    //逐级审核
    public final String URL_MEDIUM_NEXTCHECKED="app/prepareLibraryAppController/auditTask";



}
