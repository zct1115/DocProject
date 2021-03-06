package com.greendao.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.greendao.entity.Protect;
import com.greendao.entity.Cupboard;
import com.greendao.entity.Humiture;

import com.greendao.dao.ProtectDao;
import com.greendao.dao.CupboardDao;
import com.greendao.dao.HumitureDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig protectDaoConfig;
    private final DaoConfig cupboardDaoConfig;
    private final DaoConfig humitureDaoConfig;

    private final ProtectDao protectDao;
    private final CupboardDao cupboardDao;
    private final HumitureDao humitureDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        protectDaoConfig = daoConfigMap.get(ProtectDao.class).clone();
        protectDaoConfig.initIdentityScope(type);

        cupboardDaoConfig = daoConfigMap.get(CupboardDao.class).clone();
        cupboardDaoConfig.initIdentityScope(type);

        humitureDaoConfig = daoConfigMap.get(HumitureDao.class).clone();
        humitureDaoConfig.initIdentityScope(type);

        protectDao = new ProtectDao(protectDaoConfig, this);
        cupboardDao = new CupboardDao(cupboardDaoConfig, this);
        humitureDao = new HumitureDao(humitureDaoConfig, this);

        registerDao(Protect.class, protectDao);
        registerDao(Cupboard.class, cupboardDao);
        registerDao(Humiture.class, humitureDao);
    }
    
    public void clear() {
        protectDaoConfig.clearIdentityScope();
        cupboardDaoConfig.clearIdentityScope();
        humitureDaoConfig.clearIdentityScope();
    }

    public ProtectDao getProtectDao() {
        return protectDao;
    }

    public CupboardDao getCupboardDao() {
        return cupboardDao;
    }

    public HumitureDao getHumitureDao() {
        return humitureDao;
    }

}
