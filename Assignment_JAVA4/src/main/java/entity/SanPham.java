package entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "SanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;

    @OneToMany(mappedBy = "listSanPham", fetch = FetchType.EAGER)
    private List<ChiTietSP> sanPhams;

    public SanPham(UUID id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }

    public SanPham(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

}
