package nr.sproductos.models.service;

import java.util.List;
import nr.commons.models.entity.Producto;

public interface SProducto {
    public List<Producto> findAll();

    public Producto findById(Long id);

    public Producto save(Producto e);

    public void delete(Long id);
}
