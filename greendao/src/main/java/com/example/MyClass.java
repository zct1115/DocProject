package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.greendao.entity");
        Entity entity = schema.addEntity("Protect");
        entity.addIdProperty();
        entity.addStringProperty("StoreroomCode");
        entity.addStringProperty("StoreroomName");
        entity.addStringProperty("ProtectType");
        entity.addStringProperty("ProtectTime");
        entity.addStringProperty("ProtectResult");
        entity.addStringProperty("OperUser");


        Entity entity1 = schema.addEntity("Cupboard");
        entity1.addIdProperty();
        entity1.addStringProperty("code");
        entity1.addStringProperty("name");
        entity1.addStringProperty("location");
        entity1.addStringProperty("storeroomName");
        entity1.addStringProperty("storeroomCode");
        entity1.addStringProperty("remark");

        Entity entity2 = schema.addEntity("Humiture");
        entity2.addIdProperty();
        entity2.addStringProperty("checkData");
        entity2.addStringProperty("AmTemp");
        entity2.addStringProperty("AmHumidity");
        entity2.addStringProperty("PmTemp");
        entity2.addStringProperty("PmHumidity");
        entity2.addStringProperty("inTemp");
        entity2.addStringProperty("inHumidity");
        entity2.addStringProperty("outTemp");
        entity2.addStringProperty("outHumidity");
        entity2.addStringProperty("Taken");
        entity2.addStringProperty("custodian");
        entity2.addStringProperty("remark");


        schema.setDefaultJavaPackageDao("com.greendao.dao");
        try {
            new DaoGenerator().generateAll(schema, "C:\\Users\\zct11\\Desktop\\newwork\\DocProject\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
