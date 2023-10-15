package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.PermintaanPengirimanBarang;

import java.util.List;

public interface PermintaanPengirimanBarangService {
    List<PermintaanPengirimanBarang> getPermintaanPengirimanBarangByPermintaanPengiriman(Long id);
}
