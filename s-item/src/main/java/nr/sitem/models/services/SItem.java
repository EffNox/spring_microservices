package nr.sitem.models.services;

import java.util.List;
import nr.sitem.models.Item;
import nr.commons.models.entity.Producto;

public interface SItem {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
    public Producto save(Producto e);
    public Producto update (Producto e, Long id);
    public void delete(Long id);
}
