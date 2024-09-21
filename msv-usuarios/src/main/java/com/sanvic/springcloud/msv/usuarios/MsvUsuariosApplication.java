package com.sanvic.springcloud.msv.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvUsuariosApplication.class, args);
    }

}
