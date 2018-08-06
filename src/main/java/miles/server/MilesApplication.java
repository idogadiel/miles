package miles.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MilesApplication {
    public static void main(String[] args) {

        SpringApplication.run(MilesApplication.class, args);
    }
}
