package nr.susuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"nr.usuarioscommons.models.entity"})
@SpringBootApplication
public class SUsuario {

	public static void main(String[] args) {
		SpringApplication.run(SUsuario.class, args);
	}

}
