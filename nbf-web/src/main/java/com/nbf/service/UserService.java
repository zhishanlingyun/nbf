package com.nbf.service;

import com.nbf.dto.User;
import com.nbf.support.springfreemarker.FtlService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-25
 * Time: 下午10:13
 * To change this template use File | Settings | File Templates.
 */
@FtlService(name = "UserService")
@Service
public class UserService {

    public User getUser(){
        return new User("张三","123",11);
    }

}
