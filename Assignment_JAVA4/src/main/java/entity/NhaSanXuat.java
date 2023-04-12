package entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "NSX")
public class NhaSanXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;
    @Column(name = "images")
    private String image;
    @OneToMany(mappedBy = "listNhaSanXuat", fetch = FetchType.EAGER)
    private List<ChiTietSP> nhaSanXuats;


    public NhaSanXuat() {
    }

    public NhaSanXuat(UUID id, String ma, String ten, String image) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.image = image;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public NhaSanXuat(UUID id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }
}
