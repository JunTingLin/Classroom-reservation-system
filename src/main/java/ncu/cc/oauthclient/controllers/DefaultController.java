package ncu.cc.oauthclient.controllers;

import ncu.cc.oauthclient.bean.Reservation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/form")
    public String form(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken) {
            final var token = (OAuth2AuthenticationToken) authentication;

            token.getPrincipal().getAttributes().forEach((k, v) -> {
                System.out.printf("%s : %s\n", k, v);
            });
            modelMap.addAttribute("p",token.getPrincipal().getAttributes());

        }

        return "form";
    }

    @RequestMapping("/calendar")
    public String calendar() {
        return "calendar";
    }

    @PostMapping("/addReservation")
    public String addReservation(@ModelAttribute Reservation reservation) {
        System.out.println(reservation);

        return "redirect:/form";
    }
}
