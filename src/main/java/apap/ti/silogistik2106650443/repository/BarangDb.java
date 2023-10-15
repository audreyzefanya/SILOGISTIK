package apap.ti.silogistik2106650443.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ti.silogistik2106650443.model.Barang;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
    Optional<Barang> findBySku(String sku);
    List<Barang> findAllByOrderByMerkAsc();
}