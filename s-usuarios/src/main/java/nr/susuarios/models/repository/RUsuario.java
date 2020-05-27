package nr.susuarios.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import nr.usuarioscommons.models.entity.Usuario;

@RepositoryRestResource(path = "usuarios")
public interface RUsuario extends PagingAndSortingRepository<Usuario, Long> {

    @RestResource(path = "buscar-user")
    public Usuario findByUsername(String username);
    // public Usuario findByUsername(@Param("user") String username);
    
}
