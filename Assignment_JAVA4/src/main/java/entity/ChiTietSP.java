package entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ChiTietSP")
public class ChiTietSP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "NamBH")
    private Integer namBH;
    @Column(name = "MoTa")
    private String moTa;
    @Column(name = "SoLuongTon")
    private Integer soLuongTon;
    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;
    @Column(name = "GiaBan")
    private BigDecimal giaBan;
    @Column(name = "images")
    private String images;

    @ManyToOne
    @JoinColumn(name = "IdSP")
    private SanPham listSanPham;
    @ManyToOne
    @JoinColumn(name = "IdMauSac")
    private MauSac listMauSac;

    @ManyToOne
    @JoinColumn(name = "IdDongSP")
    private DongSP listDongSP;

    @ManyToOne
    @JoinColumn(name = "IdNsx")
    private NhaSanXuat listNhaSanXuat;


    public void setListMauSac(MauSac listMauSac) {
        this.listMauSac = listMauSac;
    }

    public DongSP getListDongSP() {
        return listDongSP;
    }

    public void setListDongSP(DongSP listDongSP) {
        this.listDongSP = listDongSP;
    }

    public NhaSanXuat getListNhaSanXuat() {
        return listNhaSanXuat;
    }

    public void setListNhaSanXuat(NhaSanXuat listNhaSanXuat) {
        this.listNhaSanXuat = listNhaSanXuat;
    }
}
