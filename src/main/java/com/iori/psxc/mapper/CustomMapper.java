package com.iori.psxc.mapper;

import com.iori.psxc.Custom;

public interface CustomMapper {
	int insert(Custom custom);

	int maxid();

	Custom getByTradNo(String no);

	void updateByTradNo(Custom custom);
}
