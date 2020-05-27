package nr.sproductos.models.repository;

import org.springframework.data.repository.CrudRepository;
import nr.commons.models.entity.Producto;

public interface RProducto extends CrudRepository<Producto, Long> {}
