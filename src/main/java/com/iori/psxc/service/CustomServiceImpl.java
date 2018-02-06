package com.iori.psxc.service;

import com.iori.psxc.Custom;
import com.iori.psxc.mapper.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomServiceImpl implements ICustomService {

    @Autowired
    private CustomMapper customMapper;

    @Override
    public int insert(Custom custom){
        try {
            custom.setPaid(0);
            return customMapper.insert(custom);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int maxid() {
        return customMapper.maxid();
    }

    @Override
    public Custom getByTradNo(String no) {
        return customMapper.getByTradNo(no);
    }

    @Override
    public void updateByTradNo(Custom custom) {
        customMapper.updateByTradNo(custom);
    }
}
