package ncu.cc.oauthclient.dao;

import ncu.cc.oauthclient.bean.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation,Integer> {

    @Query(value = "SELECT * FROM reservation r where r.student_id = :student_id \n" +
            "AND CURDATE() <= r.date\n" +
            "ORDER BY r.date ASC",nativeQuery = true)
    List<Reservation> findAllByStudentIdwOrderByDate(@Param("student_id") String student_id );

    @Query(value = "SELECT * FROM reservation r where r.student_id = :student_id \n" +
            "AND CURDATE() <= r.date\n" +
            "AND r.is_batch = :is_batch\n" +
            "ORDER BY r.date ASC",nativeQuery = true)
    List<Reservation> findAllByStudentIdAAndBatchOrderByDate(@Param("student_id") String student_id ,@Param("is_batch") boolean is_batch);


    @Query(value = "SELECT * FROM reservation r WHERE r.is_batch = :is_batch GROUP BY r.info" ,nativeQuery = true)
    List<Reservation> findAllByBatchGroupByInfoASC(@Param("is_batch") boolean is_batch);

    @Query(value = "SELECT  Max(date) as date FROM reservation \n" +
            "WHERE is_batch = :is_batch\n" +
            "GROUP BY info" ,nativeQuery = true)
    List<Date> findDateByBatchGroupByInfoDESC(@Param("is_batch") boolean is_batch);

    @Query(value = "SELECT * FROM reservation r where r.classroom = :classroom", nativeQuery = true)
    List<Reservation> findAllByClassroom( @Param("classroom") String classroom);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM reservation WHERE info=:info AND is_batch = :is_batch", nativeQuery = true)
    void deleteByInfoAndBatch(@Param("info") String info ,@Param("is_batch") boolean is_batch);
}
