package com.iori.psxc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(Custom custom){
        try {
            String sql = "insert into custom(name,phone,address,remark,province,city,district,time) values ('%s','%s','%s','%s','%s','%s','%s',now())";
            sql = String.format(sql, custom.getName(), custom.getPhone(), custom.getAddress(), custom.getRemark(), custom.getProvince(), custom.getCity(), custom.getDistrict());
            System.out.println(sql);
            return jdbcTemplate.update(sql);
        }catch(Exception e){
            return 0;
        }
    }
}
