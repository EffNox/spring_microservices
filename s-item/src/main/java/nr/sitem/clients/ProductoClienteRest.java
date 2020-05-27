package nr.sitem.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import nr.commons.models.entity.Producto;

@RequestMapping("/productos")
@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

    @GetMapping
    public List<Producto> getAll();

    @GetMapping("/{id}")
    public Producto get(@PathVariable Long id);

    @PostMapping
    public Producto create(@RequestBody Producto e);

    @PutMapping("/{id}")
    public Producto update(@RequestBody Producto e,@PathVariable Long id);

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id);
}
