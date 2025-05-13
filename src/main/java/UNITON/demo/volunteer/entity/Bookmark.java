package UNITON.demo.volunteer.entity;

import UNITON.demo.login.entity.UserEntity;
import jakarta.persistence.*;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue
    private Long id;

    private String progrmRegistNo;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    public static Bookmark of(String progrmRegistNo, UserEntity user) {
        Bookmark b = new Bookmark();
        b.progrmRegistNo = progrmRegistNo;
        b.user = user;
        return b;
    }
}
