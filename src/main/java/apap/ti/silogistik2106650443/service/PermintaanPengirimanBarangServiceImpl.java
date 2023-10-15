package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106650443.repository.PermintaanPengirimanBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService{
    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangRepository;

    @Override
    public List<PermintaanPengirimanBarang> getPermintaanPengirimanBarangByPermintaanPengiriman(Long id) {
        return permintaanPengirimanBarangRepository.findPermintaanPengirimanBarangByPermintaanPengiriman_IdOrderById(id);
    }
}
