package nr.soauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import nr.usuarioscommons.models.entity.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface FCUsuario {
    @GetMapping("/usuarios/search/buscar-user")
    public Usuario findByUsername(@RequestParam String username);

    @PutMapping("/usuarios/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);
}
