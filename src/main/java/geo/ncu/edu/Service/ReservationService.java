package geo.ncu.edu.Service;

import geo.ncu.edu.dao.ReservationDAO;
import geo.ncu.edu.enity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationDAO reservationDAO;

    public Iterable<Reservation> getReservations(){
        System.out.println(reservationDAO.findAll());
        return  reservationDAO.findAll();
    }
    public Integer createReservation(Reservation reservations){
        return reservationDAO.save(reservations).getId();
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


}
