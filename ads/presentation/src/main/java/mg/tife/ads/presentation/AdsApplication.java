package mg.tife.ads.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "mg.tife.ads.presentation",
        "mg.tife.ads.application",
        "mg.tife.ads.infrastructure"
})
@EnableJpaRepositories(basePackages = "mg.tife.ads.infrastructure.repository")
@EntityScan(basePackages = "mg.tife.ads.infrastructure.entity")
public class AdsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdsApplication.class, args);
    }
}