package heymart.backend;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class KeranjangBelanja {
    String ownerId;
    List<Product> products;

    public void Order(String ownerId, List<Product> products){

    }
}
