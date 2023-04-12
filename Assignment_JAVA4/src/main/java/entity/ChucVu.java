package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ChucVu")
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;

    @OneToMany(mappedBy = "listChucVu", fetch = FetchType.EAGER)
    private List<NhanVien> nhanVien;


    public ChucVu(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public ChucVu(UUID id) {
        this.id = id;
    }

    public ChucVu(UUID id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }

}
