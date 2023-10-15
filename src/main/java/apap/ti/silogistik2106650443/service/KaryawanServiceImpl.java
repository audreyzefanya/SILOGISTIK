package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.Karyawan;
import apap.ti.silogistik2106650443.repository.KaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaryawanServiceImpl implements KaryawanService{

    @Autowired
    KaryawanDb karyawanRepository;

    @Override
    public List<Karyawan> getAllKaryawan() {
        return karyawanRepository.findAll();
    }

    @Override
    public void addKaryawan(Karyawan karyawan) {
        karyawanRepository.save(karyawan);
    }

    @Override
    public Karyawan getKaryawanById(Long id) {
        return karyawanRepository.findById(id).orElse(null);
    }
}