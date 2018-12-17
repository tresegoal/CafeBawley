package cm.rst;

import cm.rst.service.IImageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CafeBawleyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafeBawleyApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(IImageService imageService) {
		return (args) -> {
			//imageService.supprimerImage(long id);
			imageService.init();
		};
	}*/
}
