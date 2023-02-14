package ncu.cc.oauthclient.controllers;

import ncu.cc.oauthclient.bean.Reservation;
import ncu.cc.oauthclient.dao.ReservationDAO;
import ncu.cc.oauthclient.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
            modelMap.addAttribute("p",token.getPrincipal().getAttributes());

            List<Reservation> yourReservations = reservationDAO.findAllByStudentIdAAndBatchOrderByDate((String)token.getPrincipal().getAttributes().get("identifier"),false);
            model.addAttribute("yourReservations",yourReservations);
            model.addAttribute("msg",msg);

        }


        return "reservationForm";
    }

    @RequestMapping("/calendar")
    public String calendar(Model model){

        String[] classList = new String[]{"S130", "S231", "S253"};
        Map<Object, Object> re = new HashMap<>();

        for (String classroom : classList){
            List<Reservation> rList = reservationDAO.findAllByClassroom(classroom);
            int i = 0;
            for (Reservation l : rList){
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
//        System.out.print(re);
        return "calendar";
    }

//    public Map<Object, Object> getDetail(){
//        String[] classList = new String[]{"S130", "S231", "S253"};
//        Map<Object, Object> re = new HashMap<>();
//
//        for (String classroom : classList){
//            List<Reservation> rList = reservationDAO.findAllByClassroom(classroom);
//            int i = 0;
//            for (Reservation l : rList){
//                Map<String, Object> data = new HashMap<>();
//                data.put("classroom", classroom);
//                data.put("chinese_name", l.getChineseName());
//                data.put("date", l.getDate());
//                data.put("start", l.getStart());
//                data.put("end", l.getEnd());
//                re.put(i, data);
//                i++;
//            }
//        }
//        System.out.print(re);
//        return re;
//    }

    @PostMapping("/addReservation")
    public String addReservation(String classroom,String date,String start,String end,String info) {

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
        reservation.setInfo(info);
        reservation.setBatch(false);

        System.out.println(reservation);
        LocalDateTime startDateTime = reservation.getDate().atTime(reservation.getStart());
        LocalDateTime endDateTime = reservation.getDate().atTime(reservation.getEnd());
        if(startDateTime.isAfter(endDateTime)){
            msg="新增失敗，開始時間不可晚於結束時間";
        } else if (startDateTime.isBefore(LocalDateTime.now()) || endDateTime.isBefore(LocalDateTime.now())) {
            msg="新增失敗，只能預約未來";
        } else if(!reservationService.isDateTimeValid(startDateTime,reservation.getClassroom())) {
            msg="新增失敗，開始時間包含在別人的區間當中";
        } else if (!reservationService.isDateTimeValid(endDateTime,reservation.getClassroom())) {
            msg="新增失敗，結束時間包含在別人的區間當中";
        } else {
            reservationDAO.save(reservation);
            msg="新增成功";
        }

        return "redirect:/reservationForm";
    }

    @PostMapping("/deleteReservation")
    public String deleteReservation(Integer id) {

        System.out.println(id);
        reservationDAO.deleteById(id);

        return "redirect:/reservationForm";
    }

    @PostMapping("/addBatch")
    public String addBatch(String classroom,String date,String start,String end) {

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
        LocalDateTime startDateTime = reservation.getDate().atTime(reservation.getStart());
        LocalDateTime endDateTime = reservation.getDate().atTime(reservation.getEnd());
        if(startDateTime.isAfter(endDateTime)){
            msg="新增失敗，開始時間不可晚於結束時間";
        } else if (startDateTime.isBefore(LocalDateTime.now()) || endDateTime.isBefore(LocalDateTime.now())) {
            msg="新增失敗，只能預約未來";
        } else if(!reservationService.isDateTimeValid(startDateTime,reservation.getClassroom())) {
            msg="新增失敗，開始時間包含在別人的區間當中";
        } else if (!reservationService.isDateTimeValid(endDateTime,reservation.getClassroom())) {
            msg="新增失敗，結束時間包含在別人的區間當中";
        } else {
            reservationDAO.save(reservation);
            msg="新增成功";
        }

        return "redirect:/reservationForm";
    }


}
