package Xero.demo.api;

import Xero.demo.model.Product;
import Xero.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import Xero.demo.model.Product;
import Xero.demo.service.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("api/products")
@RestController
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProduct(@Valid @NotNull @RequestBody Product product)
    {
        productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping(path = "{id}")
    public Product getProductByID(@PathVariable("id")  UUID id) {
        return productService.getProductById(id)
                .orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deleteProductById(@PathVariable("id") UUID id){
        productService.deleteProduct(id);
    }
    @PutMapping(path = "{id}")
    public void updateProduct(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Product productToUpdate) {
        productService.updateProduct(id,productToUpdate);
    }

    }


