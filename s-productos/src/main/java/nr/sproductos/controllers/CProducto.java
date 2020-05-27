package nr.sproductos.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
// import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import nr.commons.models.entity.Producto;
import nr.sproductos.models.service.SProducto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/productos")
public @RestController class CProducto {

    // private @Autowired Environment env;
    private @Value("${server.port}") Integer port;
    private @Autowired SProducto sv;

    @GetMapping
    public List<Producto> getAll() {
        return sv.findAll().stream().map(p -> {
            // producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            p.setPort(port);
            return p;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Producto get(@PathVariable Long id) {
        Producto producto = sv.findById(id);
        producto.setPort(port);
        // try {
        //     Thread.sleep(2000L);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        return producto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto create(@RequestBody Producto e) {
        return sv.save(e);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto create(@PathVariable Long id, @RequestBody Producto e) {
        Producto productoDb = sv.findById(id);
        productoDb.setNombre(e.getNombre());
        productoDb.setPrecio(e.getPrecio());
        return sv.save(productoDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sv.delete(id);
    }

}
