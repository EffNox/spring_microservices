package nr.soauth.config.auth.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import brave.Tracer;
import feign.FeignException;
import nr.soauth.services.SUsuario;
import nr.usuarioscommons.models.entity.Usuario;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private Logger lg = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
    private @Autowired SUsuario sv;
    private @Autowired Tracer tracer;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails u = (UserDetails) authentication.getPrincipal();
        String msj = "Success Login: ".concat(u.getUsername());
        System.out.println(msj);
        lg.info(msj);

        Usuario user = sv.findByUsername(authentication.getName());
        if (user.getIntentos() != null && user.getIntentos() > 0) {
            user.setIntentos(0);
            sv.update(user, user.getId());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        String msj = "Error en el Login: " + exception.getMessage();
        lg.error(msj);
        System.out.println(msj);
        try {
            StringBuilder errors = new StringBuilder();
            errors.append(msj);
            Usuario u = sv.findByUsername(authentication.getName());
            if (u.getIntentos() == null) {
                u.setIntentos(0);
            }
            lg.info("Intentos actual es de: " + u.getIntentos());
            u.setIntentos(u.getIntentos() + 1);
            lg.info("Intentos despues es de: " + u.getIntentos());
            errors.append(" - Intentos del login: " + u.getIntentos());
            if (u.getIntentos() >= 3) {
                String er = String.format("El usuario %s des-habilitado por máximo de intentos.", u.getUsername());
                lg.error(er);
                errors.append(" - "+er);
                u.setEnabled(false);
            }
            sv.update(u, u.getId());
            tracer.currentSpan().tag("error.msj", errors.toString());
        } catch (FeignException e) {
            lg.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
        }
    }

}
