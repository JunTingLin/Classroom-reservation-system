package geo.ncu.edu.controller;

import geo.ncu.edu.Service.ReservationService;
import geo.ncu.edu.enity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity getReservations() {
        Iterable<Reservation> reservationList = reservationService.getReservations();
        return ResponseEntity.status(HttpStatus.OK).body(reservationList);
    }

    @GetMapping("/reservations/{id}")
    public Optional<Reservation> getReservation(@PathVariable Integer id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        return reservation;
    }

    @PostMapping("/reservations")
    public ResponseEntity createReservation(@RequestBody Reservation reservation) {
        Integer result = reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/reservations/{id}")
    public ResponseEntity upadteReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {
        if(!reservation.getStartTime().before(reservation.getEndTime())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("結束時間不可早於開始時間");
        }
        Boolean result = reservationService.updateReservation(id ,reservation);
        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.OK).body("成功更新");
    }
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity deleteReservation(@PathVariable Integer id) {
        Boolean result = reservationService.deleteReservation(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.OK).body("成功刪除");
    }

}
