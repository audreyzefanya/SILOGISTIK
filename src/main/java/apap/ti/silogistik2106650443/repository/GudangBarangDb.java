package apap.ti.silogistik2106650443.repository;

import apap.ti.silogistik2106650443.model.GudangBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    List<GudangBarang> findGudangBarangByBarang_SkuOrderById(String sku);
}
