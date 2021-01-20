package netex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import java.io.Serializable;

@SpringBootApplication
@PropertySource({"/templates/application.properties"})
public class Main implements Serializable {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
