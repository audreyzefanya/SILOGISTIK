package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.model.Gudang;

import java.util.List;
import java.util.Optional;

public interface GudangService {
    List<Gudang> getAllGudang();
    List<Gudang> getAllGudangByBarang(String sku);
    Gudang getGudangById(Long id);
    void addGudang(Gudang gudang);
}