package geo.ncu.edu.dao;

import geo.ncu.edu.enity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDAO extends JpaRepository<Reservation,Integer> {


}
