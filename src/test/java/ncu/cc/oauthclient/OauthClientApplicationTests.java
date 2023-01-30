package ncu.cc.oauthclient;

import ncu.cc.oauthclient.dao.ReservationDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OauthClientApplicationTests {

	@Autowired
	ReservationDAO reservationDAO;
	@Test
	void testSelectReservation() {
		List list = reservationDAO.findAllByStudentId("109403537");
		list.forEach(System.out::print);
	}

}
