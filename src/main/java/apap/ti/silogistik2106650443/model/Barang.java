package apap.ti.silogistik2106650443.model;

import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@Entity
@Table(name = "barang")
public class Barang {
    @Id
    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "tipe_barang", nullable = false)
    private Integer tipeBarang;

    @Column(name = "merk", nullable = false, length = 255)
    private String merk;

    @Column(name = "harga_barang", nullable = false)
    private BigInteger hargaBarang;

    @OneToMany(mappedBy="barang", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;

    
}