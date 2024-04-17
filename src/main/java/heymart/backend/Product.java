package heymart.backend;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private long supermarketId;
    private long productId;
    private String productName;
    private long productPrice;
    private String productCategory;
    private int productAmount;
}