package nr.sitem.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import nr.sitem.clients.ProductoClienteRest;
import nr.sitem.models.Item;
import nr.commons.models.entity.Producto;

@Service
@Primary
public class SItemFeign implements SItem {

    private @Autowired ProductoClienteRest cliFeign;
    
    @Override
    public List<Item> findAll() {
        return cliFeign.getAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(cliFeign.get(id), cantidad);
    }

	@Override
	public Producto save(Producto e) {
		return cliFeign.create(e);
	}

	@Override
	public Producto update(Producto e, Long id) {
		return cliFeign.update(e, id);
	}

	@Override
	public void delete(Long id) {
		cliFeign.delete(id);
	}
    
}
