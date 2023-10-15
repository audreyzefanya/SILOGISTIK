package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.Karyawan;

import java.util.List;

public interface KaryawanService {
    List<Karyawan> getAllKaryawan();
    void addKaryawan(Karyawan karyawan);
    Karyawan getKaryawanById(Long id);
}