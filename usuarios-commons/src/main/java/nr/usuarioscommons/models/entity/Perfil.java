package nr.usuarioscommons.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
public @Entity class Perfil implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    @Column(unique = true, length = 30)
    private String nombre;
    private static final long serialVersionUID = 1L;
}
