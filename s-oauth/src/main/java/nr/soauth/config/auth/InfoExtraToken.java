package nr.soauth.config.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import nr.soauth.services.SUsuario;
import nr.usuarioscommons.models.entity.Usuario;

@Component
public class InfoExtraToken implements TokenEnhancer {

    private @Autowired SUsuario sv;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Usuario u = sv.findByUsername(authentication.getName());
        Map<String, Object> infEx = new HashMap<>();
        infEx.put("extra_info", u.getNombre() + "/" + u.getApellido() + "/" + u.getCorreo());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infEx);
        return accessToken;
    }

}
