package apap.ti.silogistik2106650443.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBarangDTO {
    private String merk;
    private Integer tipeBarang;
    private BigInteger hargaBarang;
}
