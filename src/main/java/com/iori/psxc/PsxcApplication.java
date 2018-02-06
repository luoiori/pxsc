package com.iori.psxc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@MapperScan(value = {"com.iori.psxc.mapper"})
@ComponentScan(basePackages = {"com.iori.psxc.mapper","com.iori.psxc.service",
		"com.iori.psxc","com.iori.constant"})
@SpringBootApplication
public class PsxcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsxcApplication.class, args);
	}
}
