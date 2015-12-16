package com.nbf.service;

import com.nbf.dto.Sku;
import com.nbf.support.springfreemarker.FtlService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-25
 * Time: 下午11:39
 * To change this template use File | Settings | File Templates.
 */
@FtlService(name = "SkuService")
@Service
public class SkuService {

    public Sku getSku(){
        return new Sku(100000L,"CD");
    }

}
