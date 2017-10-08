package com.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.greendao.entity.Protect;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PROTECT".
*/
public class ProtectDao extends AbstractDao<Protect, Long> {

    public static final String TABLENAME = "PROTECT";

    /**
     * Properties of entity Protect.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StoreroomCode = new Property(1, String.class, "StoreroomCode", false, "STOREROOM_CODE");
        public final static Property StoreroomName = new Property(2, String.class, "StoreroomName", false, "STOREROOM_NAME");
        public final static Property ProtectType = new Property(3, String.class, "ProtectType", false, "PROTECT_TYPE");
        public final static Property ProtectTime = new Property(4, String.class, "ProtectTime", false, "PROTECT_TIME");
        public final static Property ProtectResult = new Property(5, String.class, "ProtectResult", false, "PROTECT_RESULT");
        public final static Property OperUser = new Property(6, String.class, "OperUser", false, "OPER_USER");
    }


    public ProtectDao(DaoConfig config) {
        super(config);
    }
    
    public ProtectDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PROTECT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"STOREROOM_CODE\" TEXT," + // 1: StoreroomCode
                "\"STOREROOM_NAME\" TEXT," + // 2: StoreroomName
                "\"PROTECT_TYPE\" TEXT," + // 3: ProtectType
                "\"PROTECT_TIME\" TEXT," + // 4: ProtectTime
                "\"PROTECT_RESULT\" TEXT," + // 5: ProtectResult
                "\"OPER_USER\" TEXT);"); // 6: OperUser
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROTECT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Protect entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String StoreroomCode = entity.getStoreroomCode();
        if (StoreroomCode != null) {
            stmt.bindString(2, StoreroomCode);
        }
 
        String StoreroomName = entity.getStoreroomName();
        if (StoreroomName != null) {
            stmt.bindString(3, StoreroomName);
        }
 
        String ProtectType = entity.getProtectType();
        if (ProtectType != null) {
            stmt.bindString(4, ProtectType);
        }
 
        String ProtectTime = entity.getProtectTime();
        if (ProtectTime != null) {
            stmt.bindString(5, ProtectTime);
        }
 
        String ProtectResult = entity.getProtectResult();
        if (ProtectResult != null) {
            stmt.bindString(6, ProtectResult);
        }
 
        String OperUser = entity.getOperUser();
        if (OperUser != null) {
            stmt.bindString(7, OperUser);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Protect entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String StoreroomCode = entity.getStoreroomCode();
        if (StoreroomCode != null) {
            stmt.bindString(2, StoreroomCode);
        }
 
        String StoreroomName = entity.getStoreroomName();
        if (StoreroomName != null) {
            stmt.bindString(3, StoreroomName);
        }
 
        String ProtectType = entity.getProtectType();
        if (ProtectType != null) {
            stmt.bindString(4, ProtectType);
        }
 
        String ProtectTime = entity.getProtectTime();
        if (ProtectTime != null) {
            stmt.bindString(5, ProtectTime);
        }
 
        String ProtectResult = entity.getProtectResult();
        if (ProtectResult != null) {
            stmt.bindString(6, ProtectResult);
        }
 
        String OperUser = entity.getOperUser();
        if (OperUser != null) {
            stmt.bindString(7, OperUser);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Protect readEntity(Cursor cursor, int offset) {
        Protect entity = new Protect( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // StoreroomCode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // StoreroomName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // ProtectType
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // ProtectTime
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // ProtectResult
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // OperUser
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Protect entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStoreroomCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStoreroomName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProtectType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProtectTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProtectResult(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setOperUser(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Protect entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Protect entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Protect entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
