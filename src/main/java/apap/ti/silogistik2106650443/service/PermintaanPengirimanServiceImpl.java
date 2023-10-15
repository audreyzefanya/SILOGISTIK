package apap.ti.silogistik2106650443.service;

import apap.ti.silogistik2106650443.dto.request.CreateBarangPengirimanDTO;
import apap.ti.silogistik2106650443.model.PermintaanPengiriman;
import apap.ti.silogistik2106650443.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106650443.repository.BarangDb;
import apap.ti.silogistik2106650443.repository.KaryawanDb;
import apap.ti.silogistik2106650443.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106650443.repository.PermintaanPengirimanBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService{
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Autowired
    BarangDb barangDb;

    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDb.findAll();
    }

    @Override
    public void addPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman, Long idKaryawan, LocalDate tanggalPengiriman, String namaPenerima, Integer jenisPelayanan, String alamatPenerima, Integer biayaPengiriman, List<CreateBarangPengirimanDTO> listBarang) {
        LocalDateTime waktuPermintaan = LocalDateTime.now();
        String nomorPengiriman = generateNomorPengiriman(listBarang, jenisPelayanan, waktuPermintaan);
        System.out.println(nomorPengiriman);
        permintaanPengiriman.setWaktuPermintaan(waktuPermintaan);
        permintaanPengiriman.setNomorPengiriman(nomorPengiriman);
        permintaanPengiriman.setIsCancelled(false);

        permintaanPengirimanDb.save(permintaanPengiriman);

        for (CreateBarangPengirimanDTO barang : listBarang) {
            PermintaanPengirimanBarang newPermintaanPengirimanBarang = new PermintaanPengirimanBarang();
            newPermintaanPengirimanBarang.setBarang(barangDb.findBySku(barang.getSku()).orElse(null));
            newPermintaanPengirimanBarang.setKuantitasPesanan(barang.getKuantitasPesanan());
            newPermintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
            permintaanPengirimanBarangDb.save(newPermintaanPengirimanBarang);
        }
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        return permintaanPengirimanDb.findById(id).orElse(null);
    }

    @Override
    public void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        if (ChronoUnit.HOURS.between(permintaanPengiriman.getWaktuPermintaan(), LocalDateTime.now()) < 1) {
            permintaanPengiriman.setIsCancelled(true);
            permintaanPengirimanDb.save(permintaanPengiriman);
        }
    }

    @Override
    public Boolean isPengirimanLessThanOneDay(PermintaanPengiriman permintaanPengiriman) {
        LocalDateTime waktuPermintaan = permintaanPengiriman.getWaktuPermintaan();
        LocalDateTime currentTime = LocalDateTime.now();
        return ChronoUnit.HOURS.between(waktuPermintaan, currentTime) < 24;
    }

    @Override
    public void updatePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public Long getJumlahPermintaanPengiriman() {
        return null;
    }

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengirimanFilterByBarangAndTanggal(String sku, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    private String generateNomorPengiriman(List<CreateBarangPengirimanDTO> listBarang, Integer jenisLayanan, LocalDateTime waktuPermintaan) {
        int totalBarang = listBarang.stream().mapToInt(CreateBarangPengirimanDTO::getKuantitasPesanan).sum();
        String totalBarangString = String.format("%02d", Math.min(totalBarang, 99));
        String kodeJenisLayanan = switch (jenisLayanan) {
            case 1 -> "SAM";
            case 2 -> "KIL";
            case 3 -> "REG";
            case 4 -> "HEM";
            default -> throw new IllegalArgumentException("Invalid jenis layanan: " + jenisLayanan);
        };
    
        String time = String.format("%02d:%02d:%02d", waktuPermintaan.getHour(), waktuPermintaan.getMinute(), waktuPermintaan.getSecond());
        return "REQ" + totalBarangString + kodeJenisLayanan + time;
    }
    
}