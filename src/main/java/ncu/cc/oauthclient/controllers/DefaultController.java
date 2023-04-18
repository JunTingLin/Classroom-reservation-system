package ncu.cc.oauthclient.controllers;

import ncu.cc.oauthclient.bean.Reservation;
import ncu.cc.oauthclient.dao.ReservationDAO;
import ncu.cc.oauthclient.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DefaultController {
    String msg;
    @Autowired
    ReservationDAO reservationDAO;
    @Autowired
    ReservationService reservationService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/reservationForm")
    public String reservationForm(ModelMap modelMap, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken) {
            final var token = (OAuth2AuthenticationToken) authentication;

            token.getPrincipal().getAttributes().forEach((k, v) -> {
                System.out.printf("%s : %s\n", k, v);
            });
            modelMap.addAttribute("p", token.getPrincipal().getAttributes());

            List<Reservation> yourReservations = reservationDAO.findAllByStudentIdAAndBatchOrderByDate((String) token.getPrincipal().getAttributes().get("identifier"), false);
            model.addAttribute("yourReservations", yourReservations);
            model.addAttribute("msg", msg);

        }


        return "reservationForm";
    }

    @RequestMapping("/calendar")
    public String calendar(Model model) {

        String[] classList = new String[]{"S130", "S231", "S253"};
        Map<Object, Object> re = new HashMap<>();

        for (String classroom : classList) {
            List<Reservation> rList = reservationDAO.findAllByClassroom(classroom);
            int i = 0;
            for (Reservation l : rList) {
                Map<String, Object> data = new HashMap<>();
                data.put('"' + "classroom" + '"', '"' + classroom + '"');
                data.put('"' + "chinese_name" + '"', '"' + l.getChineseName() + '"');
                data.put('"' + "date" + '"', '"' + l.getDate().toString() + '"');
                data.put('"' + "start" + '"', '"' + l.getStart().toString() + '"');
                data.put('"' + "end" + '"', '"' + l.getEnd().toString() + '"');
                re.put('"' + Integer.toString(i) + '"', data);
                i++;
            }
        }

        model.addAttribute("detail", re);
        return "calendar";
    }

    @PostMapping("/addReservation")
    public String addReservation(String classroom, String date, String start, String end, String info) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var token = (OAuth2AuthenticationToken) authentication;
        Reservation reservation = new Reservation();
        reservation.setStudentId((String) token.getPrincipal().getAttributes().get("identifier"));
        reservation.setChineseName((String) token.getPrincipal().getAttributes().get("chineseName"));
        reservation.setEmail((String) token.getPrincipal().getAttributes().get("email"));
        reservation.setClassroom(classroom);
        reservation.setDate(LocalDate.parse(date));
        reservation.setStart(LocalTime.parse(start));
        reservation.setEnd(LocalTime.parse(end));
        reservation.setInfo(info);
        reservation.setBatch(false);

        System.out.println(reservation);
        try {
            reservationService.insertReservation(reservation);
            msg = "新增成功";
        } catch (Exception e) {
            msg = e.getMessage();
        }

        return "redirect:/reservationForm";
    }

    @PostMapping("/deleteReservation")
    public String deleteReservation(Integer id) {

        System.out.println(id);
        reservationDAO.deleteById(id);

        return "redirect:/reservationForm";
    }

    @RequestMapping("/batchForm")
    public String batchForm(ModelMap modelMap, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken) {
            final var token = (OAuth2AuthenticationToken) authentication;

            token.getPrincipal().getAttributes().forEach((k, v) -> {
                System.out.printf("%s : %s\n", k, v);
            });
            modelMap.addAttribute("p", token.getPrincipal().getAttributes());

            List<Reservation> yourBatchs = reservationDAO.findAllByBatchGroupByInfoASC( true);
            List<Date> yourLastDateList = reservationDAO.findDateByBatchGroupByInfoDESC( true);
            for(int i=0;i< yourBatchs.size();i++){
                yourBatchs.get(i).setEndDate(yourLastDateList.get(i));
                //把groupby好的最後時間加入
            }
            model.addAttribute("yourBatchs", yourBatchs);
            model.addAttribute("msg", msg);

        }
        return "batchForm";
    }

    @PostMapping("/addBatch")
    @Transactional
    public String addBatch(String classroom, String startDate, int weeks, String start, String end,String info) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final var token = (OAuth2AuthenticationToken) authentication;

        LocalDate date = LocalDate.parse(startDate);

        System.out.println(weeks);
        for (int i = 1; i <= weeks; i++) {
            Reservation reservation = new Reservation();
            reservation.setStudentId((String) token.getPrincipal().getAttributes().get("identifier"));
            reservation.setChineseName((String) token.getPrincipal().getAttributes().get("chineseName"));
            reservation.setEmail((String) token.getPrincipal().getAttributes().get("email"));
            reservation.setClassroom(classroom);
            reservation.setDate(date);
            reservation.setStart(LocalTime.parse(start));
            reservation.setEnd(LocalTime.parse(end));
            reservation.setInfo(info);
            reservation.setBatch(true);

            System.out.println(reservation);
            date = date.plusDays(7);

            msg = "新增成功";

            try {
                reservationService.insertReservation(reservation);
            } catch (Exception e) {
                msg = e.getMessage();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                break;
            }

        }

        return "redirect:/batchForm";
    }

    @PostMapping("/deleteBatch")
    public String deleteBatch( String info, String isBatch) {

        System.out.println(info);
        reservationDAO.deleteByInfoAndBatch(info,Boolean.parseBoolean(isBatch));

        return "redirect:/batchForm";
    }


}
