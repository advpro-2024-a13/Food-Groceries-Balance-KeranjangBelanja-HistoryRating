package heymart.backend.service;

import heymart.backend.model.KeranjangBelanja;
import heymart.backend.repository.KeranjangBelanjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeranjangBelanjaServiceImpl implements KeranjangBelanjaService {

    private final KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Autowired
    public KeranjangBelanjaServiceImpl(KeranjangBelanjaRepository keranjangBelanjaRepository) {
        this.keranjangBelanjaRepository = keranjangBelanjaRepository;
    }

    @Override
    public KeranjangBelanja createKeranjangBelanja(KeranjangBelanja keranjangBelanja) {
        return keranjangBelanjaRepository.save(keranjangBelanja);
    }
}