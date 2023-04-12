package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DongSP")
public class DongSP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;

    public DongSP(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }
    @OneToMany(mappedBy = "listDongSP", fetch = FetchType.EAGER)
    private List<ChiTietSP> dongSP;

    public DongSP(UUID id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }
}
