package apap.ti.silogistik2106650443.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ti.silogistik2106650443.model.Karyawan;

@Repository
public interface KaryawanDb extends JpaRepository<Karyawan, Long> {
}