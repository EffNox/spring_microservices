package nr.sproductos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nr.commons.models.entity.Producto;
import nr.sproductos.models.repository.RProducto;

@Service
public class SProductoImp implements SProducto {

    private @Autowired RProducto rp;
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) rp.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return rp.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public Producto save(Producto e) {
        return rp.save(e);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
         rp.deleteById(id);
    }
    
}
