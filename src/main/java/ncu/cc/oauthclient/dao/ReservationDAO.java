package ncu.cc.oauthclient.dao;

import ncu.cc.oauthclient.bean.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation,Integer> {

    @Query(value = "SELECT * FROM reservation r where r.student_id = :student_id",nativeQuery = true)
    List<Reservation> findAllByStudentId(@Param("student_id") String student_id );
}
