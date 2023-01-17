package geo.ncu.edu.dao;

import geo.ncu.edu.enity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationDAOTest {
    @Autowired
    private ReservationDAO reservationDAO;

    @Test
    void getReservations(){
        System.out.println("輸出"+reservationDAO.findAll());
        System.out.println("型別"+(reservationDAO.findAll()).getClass().getSimpleName());
    }



}