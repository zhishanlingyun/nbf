package com.nbf.service;

import com.nbf.support.springfreemarker.FtlService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-26
 * Time: 上午9:34
 * To change this template use File | Settings | File Templates.
 */
@FtlService(name = "UserQueryService")
public class UserQueryService extends QueryService {

    @Override
    public void addParam(Map<String, String> map) {
        System.out.println("UserQueryService addParam");
        map.put("mary","jjjjjjjj");
    }

}
