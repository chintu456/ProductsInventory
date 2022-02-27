package Xero.demo.dao;

import Xero.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeProductDataAccessService implements ProductDao{
    private static List<Product> DB = new ArrayList<>();

    @Override
    public int insertProduct(UUID id, Product product) {
        DB.add(new Product(id, product.getName(), product.getDescription(), product.getPrice(),
                product.getDeliveryPrice()));
        return 1;
    }

    @Override
    public List<Product> selectAllProduct() {
        return DB;
    }

    @Override
    public int insertProduct(Product product) {
        return ProductDao.super.insertProduct(product);
    }

    @Override
    public Optional<Product> selectProductById(UUID id) {
        return DB.stream().filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteProductById(UUID id) {
        Optional<Product> productMaybe = selectProductById(id);
        if(productMaybe.isEmpty()){
            return 0;
        }
        DB.remove(productMaybe.get());
        return 1;
    }

    @Override
    public int updateProductById(UUID id, Product update) {
        return selectProductById(id)
                .map(product -> {
                    int indexOfProductToUpdate = DB.indexOf(product);
                    if(indexOfProductToUpdate >= 0)
                    {
                        DB.set(indexOfProductToUpdate, new Product (id,update.getName(),update.getDescription(),update.getPrice(), update.getDeliveryPrice()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
