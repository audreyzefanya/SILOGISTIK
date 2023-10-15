package apap.ti.silogistik2106650443.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ti.silogistik2106650443.model.PermintaanPengirimanBarang;
import java.util.List;

@Repository
public interface PermintaanPengirimanBarangDb extends JpaRepository<PermintaanPengirimanBarang, Long> {
    List<PermintaanPengirimanBarang> findPermintaanPengirimanBarangByPermintaanPengiriman_IdOrderById(Long id);
}