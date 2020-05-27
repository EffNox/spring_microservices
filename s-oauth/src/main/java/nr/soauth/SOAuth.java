package nr.soauth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SOAuth implements CommandLineRunner {

	// private @Autowired BCryptPasswordEncoder pwEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SOAuth.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// String pass = pwEncoder.encode("123456");
		// System.out.println(pass);
	}

}
