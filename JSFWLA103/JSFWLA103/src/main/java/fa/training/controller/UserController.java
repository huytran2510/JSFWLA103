package fa.training.controller;

import fa.training.dto.MemberForRegister;
import fa.training.service.templates.ISecurityService;
import fa.training.service.templates.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Column;
import javax.validation.Valid;
@Controller
public class UserController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ISecurityService securityService;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("member", new MemberForRegister());
        return "register-form";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("member") @Valid MemberForRegister cUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register-form";
        }
        memberService.save(cUser);

        securityService.autoLogin(cUser.getUsername(), cUser.getPassword());
        return "redirect:/member/home";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login-form";
    }
}
