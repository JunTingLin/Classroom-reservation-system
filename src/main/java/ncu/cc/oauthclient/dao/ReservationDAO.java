package ncu.cc.oauthclient.dao;

import ncu.cc.oauthclient.bean.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation,Integer> {

    @Query(value = "SELECT * FROM reservation r where r.student_id = :student_id \n" +
            "AND CURDATE() <= r.date\n" +
            "ORDER BY r.date ASC",nativeQuery = true)
    List<Reservation> findAllByStudentIdwOrderByDate(@Param("student_id") String student_id );

    @Query(value = "SELECT * FROM reservation r where r.classroom = :classroom", nativeQuery = true)
    List<Reservation> findAllByClassroom( @Param("classroom") String classroom);
}
