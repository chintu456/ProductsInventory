package Xero.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


public class Product {
    private final UUID id;
    @NotBlank
    private final String name;
    private final String description;
    private final float price;
    private final float deliveryPrice;
    private final String productId;


    public Product(@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("price") float price,
                   @JsonProperty("deliveryPrice") float deliveryPrice,
                   @JsonProperty("productId") String productId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.productId = productId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public float getDeliveryPrice() {
        return deliveryPrice;
    }

    public String getProductId() {
        return productId;
    }
}


