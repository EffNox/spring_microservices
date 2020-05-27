package nr.susuarios.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import nr.usuarioscommons.models.entity.Perfil;
import nr.usuarioscommons.models.entity.Usuario;

@Configuration
public class Repository implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Usuario.class,Perfil.class);
    }
    
}
