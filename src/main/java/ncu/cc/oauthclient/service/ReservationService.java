package ncu.cc.oauthclient.service;

import ncu.cc.oauthclient.bean.Reservation;
import ncu.cc.oauthclient.dao.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationDAO reservationDAO;

    public Boolean isDateTimeValid(LocalDateTime checkTime,String classroom){

        List<Reservation> reservationList = reservationDAO.findAllByClassroom(classroom);
        for(Reservation r : reservationList){
            LocalDateTime startDateTime = r.getDate().atTime(r.getStart());
            LocalDateTime endDateTime = r.getDate().atTime(r.getEnd());
            if(!checkTime.isBefore(startDateTime) && !checkTime.isAfter(endDateTime)){
                //包容性比較
                return false;
            }
        }
        return true;
    }
}
