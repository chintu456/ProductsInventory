package Xero.demo.dao;

import Xero.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class ProductDataAccessService implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertProduct(UUID id, String productId, Product newProduct) {
        final String insertQuery = "INSERT INTO product (id, name, description, price, deliveryPrice, productid)" + " VALUES (?,?,?,?,?,?); ";
        int rowUpdate = jdbcTemplate.update(insertQuery, id, newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getDeliveryPrice(), productId);
        return rowUpdate;
    }

    @Override
    public List<Product> selectAllProduct() {
        final String sql = "SELECT id, name, description, price, deliveryPrice, productid FROM product";
        List<Product> product = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Float price = Float.parseFloat(resultSet.getString("price"));
            Float deliveryPrice = Float.parseFloat(resultSet.getString("deliveryPrice"));
            String productId = resultSet.getString("productid");
            return new Product(id, name, description, price, deliveryPrice, productId);
        });
        return product;
    }

    @Override
    public Optional<Product> selectProductById(UUID id) {
        final String sql = "SELECT id, name, description, price, deliveryPrice, productid FROM product WHERE id = ?";
        Product product = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            UUID Id2 = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Float price = Float.parseFloat(resultSet.getString("price"));
            Float deliveryPrice = Float.parseFloat(resultSet.getString("deliveryPrice"));
            String productId = resultSet.getString("productid");
            return new Product(Id2, name, description, price, deliveryPrice, productId);
        }, new Object[]{id});
        return Optional.ofNullable(product);
    }

    @Override
    public int deleteProductById(UUID id) {
        final String deleteQuery = "DELETE FROM product WHERE id = ?";
        int rowDeleted = jdbcTemplate.update(deleteQuery, id);
        if (rowDeleted >= 1) return 1;
        else return 0;
    }

    @Override
    public int updateProductById(UUID id, Product newProduct) {
        final String updateQuery = "UPDATE product SET name = ?, description = ?," + " price = ?, deliveryPrice = ? WHERE id = ?";
        int rowUpdate = jdbcTemplate.update(updateQuery, newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getDeliveryPrice(), id);
        return rowUpdate;
    }

    @Override
    public Optional<List<Product>> getOptionsByID(UUID id) {
        Optional<Product> productToSearch = selectProductById(id);
        final String sql = "SELECT id, name, description, price, deliveryPrice, productid FROM product WHERE productid = ?";
        //+ productToSearch.get().getProductId();
        System.out.println("producToSearch" + productToSearch.get().getProductId());
        List<Product> product = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id2 = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Float price = Float.parseFloat(resultSet.getString("price"));
            Float deliveryPrice = Float.parseFloat(resultSet.getString("deliveryPrice"));
            String productId = resultSet.getString("productid");
            return new Product(id2, name, description, price, deliveryPrice, productId);
        }, new Object[]{productToSearch.get().getProductId()});
        return Optional.ofNullable(product);
    }
}
