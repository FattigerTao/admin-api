package com.lccf.service.user;

import org.springframework.data.domain.Page;

import com.lccf.base.service.IBaseService;
import com.lccf.domain.User;

/**
 * @author lichangchao
 * @Time 2017 -03-29 09:21:10
 */
public interface IUserService extends IBaseService<User,UserParam,UserVo> {
    /**
     * 注册用户
     *
     * @param userParam
     * @return
     */
    User register(UserParam userParam);

    /**
     * 获取用户数据
     * @param userParam
     * @return
     */
    Page<User> page(UserParam userParam);

    User  getByUserName(String userName);
}
