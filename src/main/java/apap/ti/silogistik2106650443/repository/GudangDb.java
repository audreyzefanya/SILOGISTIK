package apap.ti.silogistik2106650443.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ti.silogistik2106650443.model.Gudang;
import java.util.List;
import java.util.Optional;

@Repository
public interface GudangDb extends JpaRepository<Gudang, Long> {
    List<Gudang> findAllGudangByListGudangBarang_Barang_skuOrderById(String sku);
    Optional<Gudang> findById(Long id);
}