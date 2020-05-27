package nr.sitem.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import nr.sitem.models.Item;
import nr.commons.models.entity.Producto;

@Service
public class SItemImp implements SItem {

    private @Autowired RestTemplate rst;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays
                .asList(rst.getForObject("http://servicio-productos/productos", Producto[].class));
        return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Producto producto = rst.getForObject("http://servicio-productos/productos/{id}", Producto.class,
                Collections.singletonMap("id", id));
        return new Item(producto, cantidad);
    }

    @Override
    public Producto save(Producto e) {
        HttpEntity<Producto> body = new HttpEntity<>(e);
        ResponseEntity<Producto> rs = rst.exchange("http://servicio-productos/productos", HttpMethod.POST, body,
                Producto.class);
        Producto prRs = rs.getBody();
        return prRs;
    }

    @Override
    public Producto update(Producto e, Long id) {
        HttpEntity<Producto> body = new HttpEntity<>(e);
        ResponseEntity<Producto> rs = rst.exchange("http://servicio-productos/productos/{id}", HttpMethod.PUT, body,
                Producto.class, Collections.singletonMap("id", id));
        return rs.getBody();
    }

    @Override
    public void delete(Long id) {
        rst.delete("http://servicio-productos/productos/{id}", Collections.singletonMap("id", id));
    }

}
