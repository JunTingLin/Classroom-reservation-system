package geo.ncu.edu.Service;

import geo.ncu.edu.dao.ReservationDAO;
import geo.ncu.edu.enity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationDAO reservationDAO;

    public Iterable<Reservation> getReservations(){
        return  reservationDAO.findAll();
    }
    public Integer createReservation(Reservation reservation){
        if(!reservation.getStartTime().before(reservation.getEndTime())){
            throw new IllegalArgumentException("結束時間不可早於開始時間");
        }
        if(!isTimeAvailabel(reservation.getStartTime())){
            throw new IllegalArgumentException("起始時間包含在別人預約的區間當中");
        }
        if(!isTimeAvailabel(reservation.getEndTime())){
            throw new IllegalArgumentException("結束時間包含在別人預約的區間當中");
        }
        return reservationDAO.save(reservation).getId();
    }
    public Boolean updateReservation(Integer id, Reservation newReservation){
        Optional<Reservation> isExistUser = findById(id);
        if (! isExistUser.isPresent()) {
            return false;
        }
        Reservation reservation = isExistUser.get();
        newReservation.setId(reservation.getId());
        reservationDAO.save(newReservation);
        return true;
    }
    public Optional<Reservation> findById(Integer id){
        return reservationDAO.findById(id);
    }
    public Boolean deleteReservation(Integer id) {
        Optional<Reservation> isExistUser = findById(id);
        if (!isExistUser.isPresent()) {
            return false;
        }
        reservationDAO.deleteById(id);
        return true;
    }

    public Boolean isTimeAvailabel(Date checkTime){
        List<Reservation> reservationList = reservationDAO.findAll();
        for(Reservation r : reservationList){
            if(!checkTime.before(r.getStartTime()) && !checkTime.after(r.getEndTime())){
                //包容性比較
                return false;
            }
        }
        return true;
    }


}
