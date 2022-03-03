package Xero.demo.service;
import Xero.demo.dao.ProductDao;
import Xero.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(@Qualifier("postgres") ProductDao productDao) {
        this.productDao = productDao;
    }

    public int addProduct(Product product){
        return productDao.insertProduct(product);
    }

    public List<Product> getAllProduct(){
        return productDao.selectAllProduct();
    }
    public Optional<Product> getProductById(UUID id) {
        return productDao.selectProductById(id);
    }

    public int deleteProduct(UUID id){
        return productDao.deleteProductById(id);
    }
    public int updateProduct(UUID id, Product newProduct){
        return productDao.updateProductById(id,newProduct);
    }

    public Optional<List<Product>> getOptionsByID(UUID id) {
        return productDao.getOptionsByID(id);
    }
}