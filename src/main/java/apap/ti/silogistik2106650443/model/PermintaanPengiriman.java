package apap.ti.silogistik2106650443.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "nomor_pengiriman", nullable = false, length = 16)
    private String nomorPengiriman;

    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled;

    @Column(name = "nama_penerima", nullable = false, length = 255)
    private String namaPenerima;

    @Column(name = "alamat_penerima", nullable = false, length = 255)
    private String alamatPenerima;

    @Column(name = "tanggal_pengiriman", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPengiriman;

    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biayaPengiriman;

    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenisLayanan;

    @Column(name = "waktu_permintaan", nullable = false)
    private LocalDateTime waktuPermintaan;

    @ManyToOne
    @JoinColumn(name = "id_karyawan", nullable = false)
    private Karyawan karyawan;

    @OneToMany(mappedBy="permintaanPengiriman", fetch = FetchType.EAGER)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;

}