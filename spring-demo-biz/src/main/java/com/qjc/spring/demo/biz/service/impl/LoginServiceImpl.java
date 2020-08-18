package com.qjc.spring.demo.biz.service.impl;

import com.qjc.spring.demo.biz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean checkToken(String token) {
        return stringRedisTemplate.hasKey(token);
    }

    @Override
    public boolean addTokenVisits(String token) {
        if(!this.checkToken(token)) {
            stringRedisTemplate.opsForValue().set(token, "1");
            return true;
        } else {
            stringRedisTemplate.boundValueOps(token).increment(1);
            return true;
        }
    }

    @Override
    public Integer getTokenVisits(String token) {
        if(!this.checkToken(token)) {
            return 0;
        } else {
            String value = stringRedisTemplate.opsForValue().get(token);
            return Integer.parseInt(value);
        }
    }
}
