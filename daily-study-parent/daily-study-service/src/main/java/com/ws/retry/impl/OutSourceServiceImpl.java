package com.ws.retry.impl;

import com.ws.retry.IOutSourceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @Author: wangshuo
 * @Date: 2023/8/25 11:50
 */
@Service
public class OutSourceServiceImpl  implements IOutSourceService {

    static Random random = new Random();

    @Override
    public List<Integer> getResult()  throws RuntimeException{
       throw new RuntimeException();
    }



}
