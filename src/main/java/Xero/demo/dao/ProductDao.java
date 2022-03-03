package Xero.demo.dao;
import Xero.demo.model.Product;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface ProductDao {
    int insertProduct(UUID id, String productId, Product product);


    default int insertProduct(Product product) {
        UUID id = UUID.randomUUID();
        String productId = (UUID.randomUUID()).toString();
        return insertProduct(id, productId, product);
    }
    List<Product> selectAllProduct();
    Optional<Product> selectProductById(UUID id);

    int deleteProductById(UUID id);
    int updateProductById(UUID id, Product product);
    Optional<List<Product>> getOptionsByID(UUID id);
}
