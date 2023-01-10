package web.karrot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CloneKarrotMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloneKarrotMarketApplication.class, args);
	}

}
