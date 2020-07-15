package org.clickandcollect.webservice;

//import com.vividsolutions.jts.geom.GeometryFactory;
//import com.vividsolutions.jts.util.GeometricShapeFactory;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.util.GeometricShapeFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"org.clickandcollect"})
@EntityScan(basePackages = {"org.clickandcollect"})
@EnableJpaRepositories(basePackages = {"org.clickandcollect"})
public class ClickAndCollectApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickAndCollectApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET","HEAD","PUT","POST","DELETE")
                        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                                "Access-Control-Request-Headers", "Authorization")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
            }
        };
    }

    @Bean
    public GeometryFactory geometryFactoryBean() {
        return new GeometryFactory();
    }

    @Bean
    public GeometricShapeFactory geometricShapeFactoryBean() {
        return new GeometricShapeFactory();
    }

}
