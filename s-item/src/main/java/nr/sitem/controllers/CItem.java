package nr.sitem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import nr.sitem.models.Item;
import nr.commons.models.entity.Producto;
import nr.sitem.models.services.SItem;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/items")
@RefreshScope
public @RestController class CItem {

    private Logger lg = LoggerFactory.getLogger(CItem.class);

    private @Autowired Environment env;

    private @Autowired SItem sv;
    private @Value("${configuracion.texto}") String texto;

    @GetMapping
    public List<Item> getAll() {
        return sv.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/{id}/cantidad/{cantidad}")
    public Item get(@PathVariable Long id, @PathVariable Integer cantidad) {
        return sv.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, Integer cantidad) {
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Somy");
        producto.setPrecio(500.00);
        item.setProducto(producto);
        return item;
    }

    @GetMapping("/obetener-config")
    public ResponseEntity<?> obetenerConfig(@Value("${server.port}") String port) {
        lg.info(texto);
        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", port);

        if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
            json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
            json.put("autor.correo", env.getProperty("configuracion.autor.correo"));
        }

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto create(@RequestBody Producto e) {
        return sv.save(e);
    }

    @PutMapping("/{id}")
    public Producto update(@RequestBody Producto e, @PathVariable Long id) {
        return sv.update(e, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        sv.delete(id);
    }

}
