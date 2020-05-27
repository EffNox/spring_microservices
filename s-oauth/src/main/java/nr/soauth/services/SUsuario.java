package nr.soauth.services;

import nr.usuarioscommons.models.entity.Usuario;

public interface SUsuario {
    public Usuario findByUsername(String username);
    public Usuario update(Usuario usuario, Long id);
}
