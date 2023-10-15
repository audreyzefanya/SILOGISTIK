package apap.ti.silogistik2106650443.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 20)
    private Long id;

    @Column(name = "kuantitas_pesanan", nullable = false)
    private Integer kuantitasPesanan;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_permintaan_pengiriman", nullable = false)
    private PermintaanPengiriman permintaanPengiriman;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="sku_barang", nullable = false)
    private Barang barang;
}