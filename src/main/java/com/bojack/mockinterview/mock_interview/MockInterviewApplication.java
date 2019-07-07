package com.bojack.mockinterview.mock_interview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bojack.mockinterview.mock_interview.dao")
public class MockInterviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockInterviewApplication.class, args);
    }

}
