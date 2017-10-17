package com.nong.nongo2o.greenDaoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nong.nongo2o.entity.bean.EaseUserInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EASE_USER_INFO".
*/
public class EaseUserInfoDao extends AbstractDao<EaseUserInfo, String> {

    public static final String TABLENAME = "EASE_USER_INFO";

    /**
     * Properties of entity EaseUserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UserId = new Property(0, String.class, "userId", true, "USER_ID");
        public final static Property UserNick = new Property(1, String.class, "userNick", false, "USER_NICK");
        public final static Property Avatar = new Property(2, String.class, "avatar", false, "AVATAR");
    }


    public EaseUserInfoDao(DaoConfig config) {
        super(config);
    }
    
    public EaseUserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EASE_USER_INFO\" (" + //
                "\"USER_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: userId
                "\"USER_NICK\" TEXT," + // 1: userNick
                "\"AVATAR\" TEXT);"); // 2: avatar
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EASE_USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EaseUserInfo entity) {
        stmt.clearBindings();
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(1, userId);
        }
 
        String userNick = entity.getUserNick();
        if (userNick != null) {
            stmt.bindString(2, userNick);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(3, avatar);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EaseUserInfo entity) {
        stmt.clearBindings();
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(1, userId);
        }
 
        String userNick = entity.getUserNick();
        if (userNick != null) {
            stmt.bindString(2, userNick);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(3, avatar);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public EaseUserInfo readEntity(Cursor cursor, int offset) {
        EaseUserInfo entity = new EaseUserInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // userId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userNick
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // avatar
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EaseUserInfo entity, int offset) {
        entity.setUserId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUserNick(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAvatar(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final String updateKeyAfterInsert(EaseUserInfo entity, long rowId) {
        return entity.getUserId();
    }
    
    @Override
    public String getKey(EaseUserInfo entity) {
        if(entity != null) {
            return entity.getUserId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(EaseUserInfo entity) {
        return entity.getUserId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}