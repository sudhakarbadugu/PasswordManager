package com.blmsr.manager.dao;

import com.blmsr.manager.models.User;

import java.util.List;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public interface UserService {
    public long save(User theEntity);
    public long update(User theEntity);
    public long delete(User theEntity);
    public User getPassword(String theName);
    List<User> getAllPasswords();
}
