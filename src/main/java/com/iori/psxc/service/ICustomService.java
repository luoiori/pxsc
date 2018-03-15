package com.iori.psxc.service;

import com.iori.psxc.Custom;
import com.iori.psxc.Price;

public interface ICustomService {
    int insert(Custom custom);

    int maxid();

    Custom getByTradNo(String no);

    void updateByTradNo(Custom custom);

    Price getTcPrice(int tc);
}
