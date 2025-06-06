package UNITON.demo.volunteer.repository;

import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.volunteer.entity.VolunteerBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerBookmarkRepository extends JpaRepository<VolunteerBookmark, Long> {
    @Query("SELECT v.progrmRegistNo FROM VolunteerBookmark v WHERE v.user = :user")
    List<String> findProgrmRegistNoByUser(@Param("user") UserEntity user);

    Optional<VolunteerBookmark> findByUserAndProgrmRegistNo(UserEntity user, String progrmRegistNo);
    void deleteByUserAndProgrmRegistNo(UserEntity user, String progrmRegistNo);
    boolean existsByUserAndProgrmRegistNo(UserEntity user, String progrmRegistNo);

}
