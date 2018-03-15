package com.iori.psxc.mapper;

import com.iori.psxc.Custom;
import com.iori.psxc.Price;

public interface CustomMapper {
	int insert(Custom custom);

	int maxid();

	Custom getByTradNo(String no);

	void updateByTradNo(Custom custom);

	Price getTcPrice(int tc);
}
