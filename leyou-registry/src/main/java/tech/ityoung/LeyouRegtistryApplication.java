package tech.ityoung;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LeyouRegtistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeyouRegtistryApplication.class);
    }
}
