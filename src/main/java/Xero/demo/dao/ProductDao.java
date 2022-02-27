package Xero.demo.dao;
import Xero.demo.model.Product;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface ProductDao {
    int insertProduct(UUID id,Product product);


    default int insertProduct(Product product) {
        UUID id = UUID.randomUUID();
        return insertProduct(id,product);
    }
    List<Product> selectAllProduct();
    Optional<Product> selectProductById(UUID id);
    int deleteProductById(UUID id);
    int updateProductById(UUID id, Product product);
}
