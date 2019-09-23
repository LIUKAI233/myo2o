package com.lk.o2o.service;

import com.lk.o2o.entity.LocalAuth;

public interface LocalAuthService {

    /**
     * 通过账号密码，获取平台账号信息
     * @param username 用户名
     * @param password 密码
     * @return 平台账号信息
     */
    LocalAuth getLocalAuthByUsernameAndPassword(String username,String password);

    /**
     * 通过用户id，获取平台账号信息
     * @param userId 用户id
     * @return 平台账号信息
     */
    LocalAuth getLocalAuthByUserId(Long userId);

    /**
     *
     * @param localAuth
     * @return
     */
    int addLocalAuth(LocalAuth localAuth);
}
