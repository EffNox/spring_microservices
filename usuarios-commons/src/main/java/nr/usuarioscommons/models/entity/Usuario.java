package nr.usuarioscommons.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
public @Entity class Usuario implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(unique = true, length = 20)
    private String username;
    @Column(length = 60)
    private String password;
    private Boolean enabled;
    private String nombre;
    private String apellido;
    @Column(unique = true, length = 100)
    private String correo;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Perfil> perfiles;

    private Integer intentos;

    private static final long serialVersionUID = 1L;

}
