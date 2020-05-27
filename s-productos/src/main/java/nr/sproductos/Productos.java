package nr.sproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"nr.commons.models.entity"})
public class Productos {

	public static void main(String[] args) {
		SpringApplication.run(Productos.class, args);
	}

}
