package ncu.cc.oauthclient.controllers;

import ncu.cc.oauthclient.bean.Reservation;
import ncu.cc.oauthclient.dao.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class DefaultController {
    @Autowired
    ReservationDAO reservationDAO;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/form")
    public String form(ModelMap modelMap,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken) {
            final var token = (OAuth2AuthenticationToken) authentication;

            token.getPrincipal().getAttributes().forEach((k, v) -> {
                System.out.printf("%s : %s\n", k, v);
            });
            modelMap.addAttribute("p",token.getPrincipal().getAttributes());

            List<Reservation> yourReservations = reservationDAO.findAllByStudentId((String)token.getPrincipal().getAttributes().get("identifier"));
            model.addAttribute("yourReservations",yourReservations);

        }


        return "form";
    }

    @RequestMapping("/calendar")
    public String calendar() {
        return "calendar";
    }

    @PostMapping("/addReservation")
    public String addReservation(String classroom,String date,String start,String end) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var token = (OAuth2AuthenticationToken) authentication;
        Reservation reservation = new Reservation();
        reservation.setStudentId((String)token.getPrincipal().getAttributes().get("identifier"));
        reservation.setChineseName((String) token.getPrincipal().getAttributes().get("chineseName"));
        reservation.setEmail((String) token.getPrincipal().getAttributes().get("email"));
        reservation.setClassroom(classroom);
        reservation.setDate(LocalDate.parse(date));
        reservation.setStart(LocalTime.parse(start));
        reservation.setEnd(LocalTime.parse(end));

        System.out.println(reservation);
        reservationDAO.save(reservation);


        return "redirect:/form";
    }
}
