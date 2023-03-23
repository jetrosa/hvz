package noroff.project.hvz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HvzApplication {

	public static void main(String[] args) {
		SpringApplication.run(HvzApplication.class, args);
	}

}
