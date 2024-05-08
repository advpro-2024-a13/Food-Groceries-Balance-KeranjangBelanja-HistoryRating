package heymart.backend.service;

import heymart.backend.models.Cart;
import heymart.backend.repository.KeranjangBelanjaRepository;
import org.springframework.stereotype.Service;

@Service
public class KeranjangBelanjaServiceImpl implements KeranjangBelanjaService {

    private final KeranjangBelanjaRepository keranjangBelanjaRepository;

    public KeranjangBelanjaServiceImpl(KeranjangBelanjaRepository keranjangBelanjaRepository) {
        this.keranjangBelanjaRepository = keranjangBelanjaRepository;
    }

    @Override
    public Cart createKeranjangBelanja(Cart cart) {
        return keranjangBelanjaRepository.save(cart);
    }
}