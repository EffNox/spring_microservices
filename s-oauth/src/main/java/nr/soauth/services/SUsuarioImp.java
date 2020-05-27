package nr.soauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import brave.Tracer;
import feign.FeignException;
import nr.soauth.clients.FCUsuario;
import nr.usuarioscommons.models.entity.Usuario;

@Service
public class SUsuarioImp implements UserDetailsService, SUsuario {

    private Logger lg = LoggerFactory.getLogger(SUsuarioImp.class);
    private @Autowired FCUsuario clt;
    private @Autowired Tracer tracer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario u = findByUsername(username);

            List<GrantedAuthority> authorities = u.getPerfiles().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getNombre()))
                    .peek(authority -> lg.info("Perfil:" + authority.getAuthority())).collect(Collectors.toList());
            lg.info("Usuario autenticado: " + u.getUsername());
            return new User(u.getUsername(), u.getPassword(), u.getEnabled(), true, true, true, authorities);
        } catch (FeignException e) {
            String msj = "Error en el login, no existe el usuario '" + username + "' en el sistema";
            lg.error(msj);
            tracer.currentSpan().tag("error.msj", msj + " -:- " + e.getMessage());
            throw new UsernameNotFoundException(msj);
        }
    }

    @Override
    public Usuario findByUsername(String username) {
        return clt.findByUsername(username);
    }

    @Override
    public Usuario update(Usuario usuario, Long id) {
        return clt.update(usuario, id);
    }

}
