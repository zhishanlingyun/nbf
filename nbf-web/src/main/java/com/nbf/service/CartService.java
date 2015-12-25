package com.nbf.service;

import com.nbf.dto.Cart;
import com.nbf.support.springfreemarker.FtlService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-25
 * Time: 下午11:50
 * To change this template use File | Settings | File Templates.
 */
@FtlService(name = "CartService")
@Service
public class CartService {
    public Cart getCart(){
        return new Cart(new Date().getTime());
    }
}
