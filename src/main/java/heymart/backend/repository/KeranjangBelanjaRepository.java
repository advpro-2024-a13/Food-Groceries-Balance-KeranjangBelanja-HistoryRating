package heymart.backend.repository;

import heymart.backend.models.KeranjangBelanja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface KeranjangBelanjaRepository extends JpaRepository<KeranjangBelanja, Long>{
}