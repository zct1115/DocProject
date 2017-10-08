package com.ckkj.docproject.event;

/**
 * Created by XISEVEN on 2017/6/3.
 */

public class MessageEvent {
    private String tableName;
    private String tableNameCH;
    private String catalogueId;

   public MessageEvent(){

   }

    public MessageEvent(String tableName, String tableNameCH,String catalogueId) {
        this.tableName = tableName;
        this.tableNameCH = tableNameCH;
        this.catalogueId=catalogueId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getTableNameCH() {
        return tableNameCH;
    }

    public void setTableNameCH(String tableNameCH) {
        this.tableNameCH = tableNameCH;
    }

    public String getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(String catalogueId) {
        this.catalogueId = catalogueId;
    }
}
