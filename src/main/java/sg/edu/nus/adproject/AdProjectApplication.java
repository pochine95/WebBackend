package sg.edu.nus.adproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "sg.edu.nus.adproject") //
public class AdProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdProjectApplication.class, args);
    }

}
