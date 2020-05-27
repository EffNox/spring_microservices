package nr.commons.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Data
public @Entity class Producto implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    private String nombre;
    private Double precio;
    @Temporal(TemporalType.DATE)
    private Date createAt;
    
    @Transient
    private Integer port;
    
    private static final long serialVersionUID = -3629810027188244551L;
}
