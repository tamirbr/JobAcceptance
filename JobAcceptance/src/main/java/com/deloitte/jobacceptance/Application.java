package com.deloitte.jobacceptance;

import com.deloitte.jobacceptance.model.Customer;
import com.deloitte.jobacceptance.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}
