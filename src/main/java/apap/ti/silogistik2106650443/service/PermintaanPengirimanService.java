package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.dto.request.CreateBarangPengirimanDTO;
import apap.ti.silogistik2106650443.model.PermintaanPengiriman;

import java.time.LocalDate;
import java.util.List;


public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> getAllPermintaanPengiriman();
    void addPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman, Long idKaryawan, LocalDate tanggalPengiriman, String namaPenerima, Integer jenisPelayanan, String alamatPenerima, Integer biayaPengiriman, List<CreateBarangPengirimanDTO> listBarang);
    PermintaanPengiriman getPermintaanPengirimanById(Long id);
    void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    Boolean isPengirimanLessThanOneDay(PermintaanPengiriman permintaanPengiriman);
    void updatePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    Long getJumlahPermintaanPengiriman();
    List<PermintaanPengiriman> getAllPermintaanPengirimanFilterByBarangAndTanggal(String sku, LocalDate startDate, LocalDate endDate);
}