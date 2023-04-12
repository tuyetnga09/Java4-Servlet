package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    private UUID idProduct;
    private String tenSP;
    private Integer soLuong;
    private BigDecimal giaBan;
private String hinhAnh;


}
