package satriadhikara.stima.tucil3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import satriadhikara.stima.tucil3.helper.Dictionary;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        Dictionary dictionary = new Dictionary(); // Load words from file
    }
}
