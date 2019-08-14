package com.stackroute.MuzixAppMysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.stackroute")
@EnableCaching
public class MuzixAppMysqlApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(MuzixAppMysqlApplication.class, args);
	}

}
