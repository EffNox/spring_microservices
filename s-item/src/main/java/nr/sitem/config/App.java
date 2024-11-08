package nr.sitem.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class App {

    @Bean
    @LoadBalanced
    public RestTemplate rst(){
        return new RestTemplate();    
    }

}
