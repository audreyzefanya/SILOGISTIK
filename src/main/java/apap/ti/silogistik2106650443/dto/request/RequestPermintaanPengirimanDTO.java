package apap.ti.silogistik2106650443.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPermintaanPengirimanDTO {
    private Long idKaryawan;
    private String namaPenerima;
    private String alamatPenerima;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPengiriman;
    private Integer jenisLayanan;
    private Integer biayaPengiriman;
    private List<CreateBarangPengirimanDTO> listPermintaanPengirimanBarang;

}
