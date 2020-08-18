package com.qjc.spring.demo.biz.service;

public interface LoginService {
    public boolean checkToken(String token);

    public boolean addTokenVisits(String token);

    public Integer getTokenVisits(String token);
}
