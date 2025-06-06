package UNITON.demo.volunteer.entity;

import UNITON.demo.login.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "volunteer_bookmark",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "progrmRegistNo"})}
)
public class VolunteerBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String progrmRegistNo;
    private String progrmSj;
    private String actPlace;
    private String noticeEndde;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolunteerBookmark that = (VolunteerBookmark) o;
        return progrmRegistNo.equals(that.progrmRegistNo);
    }

    @Override
    public int hashCode() {
        return progrmRegistNo.hashCode();
    }
}
