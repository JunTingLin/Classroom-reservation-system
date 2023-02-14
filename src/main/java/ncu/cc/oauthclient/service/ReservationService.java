package ncu.cc.oauthclient.service;

import ncu.cc.oauthclient.bean.Reservation;
import ncu.cc.oauthclient.dao.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationDAO reservationDAO;

    public Boolean isDateTimeValid(LocalDateTime checkTime, String classroom) {

        List<Reservation> reservationList = reservationDAO.findAllByClassroom(classroom);
        for (Reservation r : reservationList) {
            LocalDateTime startDateTime = r.getDate().atTime(r.getStart());
            LocalDateTime endDateTime = r.getDate().atTime(r.getEnd());
            if (!checkTime.isBefore(startDateTime) && !checkTime.isAfter(endDateTime)) {
                //包容性比較
                return false;
            }
        }
        return true;
    }

    @Transactional
    public void insertReservation(Reservation reservation) throws Exception {
//        String msg="";
        LocalDateTime startDateTime = reservation.getDate().atTime(reservation.getStart());
        LocalDateTime endDateTime = reservation.getDate().atTime(reservation.getEnd());
        if (startDateTime.isAfter(endDateTime)) {
//            msg = "新增失敗，開始時間不可晚於結束時間";
            throw new RuntimeException("新增失敗，開始時間不可晚於結束時間");
        } else if (startDateTime.isBefore(LocalDateTime.now()) || endDateTime.isBefore(LocalDateTime.now())) {
//            msg = "新增失敗，只能預約未來";
            throw new RuntimeException("新增失敗，只能預約未來");
        } else if (!isDateTimeValid(startDateTime, reservation.getClassroom())) {
//            msg = "新增失敗，開始時間包含在別人的區間當中";
            throw new RuntimeException("新增失敗，開始時間包含在別人的區間當中");
        } else if (!isDateTimeValid(endDateTime, reservation.getClassroom())) {
//            msg = "新增失敗，結束時間包含在別人的區間當中";
            throw new RuntimeException("新增失敗，結束時間包含在別人的區間當中");
        } else {
            reservationDAO.save(reservation);
//            msg = "新增成功";
        }

    }
}
