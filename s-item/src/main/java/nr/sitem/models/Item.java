package nr.sitem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nr.commons.models.entity.Producto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Producto producto;
    private Integer cantidad;

    public Double getTotal() {
        return producto.getPrecio() * cantidad.doubleValue();
    }

}
