package heymart.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GetProductRequest {
    private Long ownerId;
    private UUID productId;
    private int quantity;
}
