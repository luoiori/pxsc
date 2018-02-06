package com.iori.psxc.service;

import com.iori.psxc.Custom;

public interface ICustomService {
    int insert(Custom custom);

    int maxid();

    Custom getByTradNo(String no);

    void updateByTradNo(Custom custom);
}
