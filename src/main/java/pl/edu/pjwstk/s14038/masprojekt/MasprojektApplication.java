package pl.edu.pjwstk.s14038.masprojekt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;


@SpringBootApplication
@ComponentScan(basePackages = "pl.edu.pjwstk.s14038.masprojekt")
public class MasprojektApplication {

    public static void main(String[] args) {

        SpringApplication.run(MasprojektApplication.class, args);
    }

}
