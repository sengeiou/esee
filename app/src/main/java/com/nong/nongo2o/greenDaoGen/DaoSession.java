package com.nong.nongo2o.greenDaoGen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.nong.nongo2o.entity.bean.EaseUserInfo;
import com.nong.nongo2o.entity.domain.City;

import com.nong.nongo2o.greenDaoGen.EaseUserInfoDao;
import com.nong.nongo2o.greenDaoGen.CityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig easeUserInfoDaoConfig;
    private final DaoConfig cityDaoConfig;

    private final EaseUserInfoDao easeUserInfoDao;
    private final CityDao cityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        easeUserInfoDaoConfig = daoConfigMap.get(EaseUserInfoDao.class).clone();
        easeUserInfoDaoConfig.initIdentityScope(type);

        cityDaoConfig = daoConfigMap.get(CityDao.class).clone();
        cityDaoConfig.initIdentityScope(type);

        easeUserInfoDao = new EaseUserInfoDao(easeUserInfoDaoConfig, this);
        cityDao = new CityDao(cityDaoConfig, this);

        registerDao(EaseUserInfo.class, easeUserInfoDao);
        registerDao(City.class, cityDao);
    }
    
    public void clear() {
        easeUserInfoDaoConfig.clearIdentityScope();
        cityDaoConfig.clearIdentityScope();
    }

    public EaseUserInfoDao getEaseUserInfoDao() {
        return easeUserInfoDao;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

}
