package com.moa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스케줄러 사용
@SpringBootApplication
public class MoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoaApplication.class, args);
	}

}
