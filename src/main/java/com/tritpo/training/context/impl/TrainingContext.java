package com.tritpo.training.context.impl;


import com.tritpo.training.context.ApplicationContext;
import com.tritpo.training.dao.impl.UserDaoImpl;
import com.tritpo.training.domain.BaseEntity;
import com.tritpo.training.domain.User;
import com.tritpo.training.exception.ContextException;
import com.tritpo.training.pool.ConnectionPool;
import com.tritpo.training.specification.impl.FindAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

public class   TrainingContext implements ApplicationContext {

    private static TrainingContext context;

    public static TrainingContext getInstance() {
        if (context == null) {
            context = new TrainingContext();
        }

        return context;
    }

    private static final Logger logger = LogManager.getLogger(TrainingContext.class);
    private TrainingContext() {
        ConnectionPool.getInstance();
        init();
    }


    private Collection<User> users = new ArrayList<>();

    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.getName().equals(User.class.getName()))
            return (Collection<T>) users;
        return null;
    }

    /**
     * fill in cache when application starts
     */
    @Override
    public void init() {
        try {
            fillInCollections();
            logger.info("Cache initialised successfully");
        } catch (ContextException e) {
            e.printStackTrace();
            logger.fatal("Cant fill in cache");
            System.exit(1);
        }

    }

    private void fillInCollections() throws ContextException {
        users = UserDaoImpl.getInstance().queryAll(new FindAll());
        if ( users.isEmpty()) {
            throw new ContextException("Failed");
        }
    }


}
