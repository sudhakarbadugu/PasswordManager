package com.blmsr.manager.service;

import android.content.Context;

import com.blmsr.manager.dao.CategoryEntryService;
import com.blmsr.manager.dao.CategoryService;
import com.blmsr.manager.dao.SqliteController;
import com.blmsr.manager.dao.UserService;
import com.blmsr.manager.dao.impl.CategoryServiceImpl;
import com.blmsr.manager.dao.impl.CategoryEntryServiceImpl;
import com.blmsr.manager.dao.impl.UserServiceImpl;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public class DatabaseService {
    private  Context itsContext;
    private CategoryService categoryService;
    private CategoryEntryService categoryEntryService;
    private UserService userService;
    private DatabaseService(Context theContext)
    {
        itsContext = theContext;
        SqliteController aSqliteController = new SqliteController(theContext);
        categoryService = new CategoryServiceImpl(aSqliteController);
        categoryEntryService = new CategoryEntryServiceImpl(aSqliteController);
        userService = new UserServiceImpl(aSqliteController);
    }
    private static DatabaseService itsDatabaseService;

    public static final DatabaseService getInstance(Context theContext)
    {
        if(theContext == null){
            throw new IllegalArgumentException("Context can't be null");
        }

        if(itsDatabaseService == null)
        {
            itsDatabaseService = new DatabaseService(theContext);
        }

        return itsDatabaseService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public CategoryEntryService getCategoryEntryService() {
        return categoryEntryService;
    }

    public UserService getUserService() {
        return userService;
    }
}
