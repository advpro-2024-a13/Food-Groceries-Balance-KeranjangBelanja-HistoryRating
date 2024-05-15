package heymart.backend.dto;

import lombok.Builder;

import java.util.HashMap;
import java.util.UUID;

@Builder
public class GetAllProductsFromUser {
    public HashMap<UUID, Integer> productMap;
}