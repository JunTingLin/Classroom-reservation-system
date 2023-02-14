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
//		List list = reservationDAO.findAllByStudentIdwOrderByDate("109403537");
//		list.forEach(System.out::println);
//
//		List list2 = reservationDAO.findAllByClassroom("S130");
//		list2.forEach(System.out::println);

		List list3 = reservationDAO.findDateByBatchGroupByInfoDESC(true);
		list3.forEach(System.out::println);
	}



}
