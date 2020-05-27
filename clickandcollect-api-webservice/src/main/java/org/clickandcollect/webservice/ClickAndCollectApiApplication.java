package org.clickandcollect.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"org.clickandcollect"})
@EntityScan(basePackages = {"org.clickandcollect"})
@EnableJpaRepositories(basePackages = {"org.clickandcollect"})
public class ClickAndCollectApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickAndCollectApiApplication.class, args);
    }

}
