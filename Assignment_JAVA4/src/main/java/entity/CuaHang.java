package entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CuaHang")
public class CuaHang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "ThanhPho")
    private String thanhPho;
    @Column(name = "QuocGia")
    private String quocGia;
    @OneToMany(mappedBy = "listCuaHang", fetch = FetchType.EAGER)
    private List<NhanVien> cuaHangs;

    public CuaHang(String ma, String ten, String diaChi, String thanhPho, String quocGia) {
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.quocGia = quocGia;
        this.thanhPho = thanhPho;
    }

    public CuaHang(UUID id) {
        this.id = id;
    }

    public CuaHang(UUID id, String ma, String ten, String diaChi, String thanhPho, String quocGia, List<NhanVien> cuaHangs) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.quocGia = quocGia;
        this.cuaHangs = cuaHangs;
    }

    public CuaHang(UUID id, String ma, String ten, String diaChi, String thanhPho, String quocGia) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.quocGia = quocGia;
    }

}
