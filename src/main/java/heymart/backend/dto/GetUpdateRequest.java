package heymart.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class GetUpdateRequest {
    private Long ownerId;
    private Map<UUID, Integer> updatedProducts;
}