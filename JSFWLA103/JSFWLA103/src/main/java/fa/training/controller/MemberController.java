package fa.training.controller;



import fa.training.dto.ContentForCreate;
import fa.training.dto.MemberForUpdate;
import fa.training.entities.Content;
import fa.training.entities.Member;
import fa.training.service.templates.ContentService;
import fa.training.service.templates.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ContentService contentService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("member",new MemberForUpdate());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Member member = memberService.findByUsername(username);
        model.addAttribute("email", member.getEmail());

        List<Content> contentList = contentService.getAll();
        model.addAttribute("contents", contentList);

        ContentForCreate contentForCreate = new ContentForCreate();
        model.addAttribute("content",contentForCreate);

        return "menu-form";
    }

    @GetMapping("/update")
    private String update(Model model) {
        MemberForUpdate memberForUpdate = new MemberForUpdate();
        model.addAttribute("member", memberForUpdate);
        return "update-profile";
    }

    @PostMapping("/update")
    private String update(@ModelAttribute("member") @Valid MemberForUpdate memberForUpdate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update-profile";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println(username);
        Member updatedMember = memberService.update(memberForUpdate, username);
        if (updatedMember != null) {

            return "redirect:/member/home";
        } else {
            return "update-profile";
        }
    }

    @GetMapping("/list")
    public String getAll(Model model){
        List<Content> contentList = contentService.getAll();
        model.addAttribute("contents", contentList);
        return "view-content";
    }

    @GetMapping("/add")
    public String add(Model model){
        ContentForCreate contentForCreate = new ContentForCreate();
        model.addAttribute("content",contentForCreate);
        return "add-content";
    }

    @PostMapping("/add")
    public String addContent(@ModelAttribute("content") @Valid ContentForCreate contentForCreate, BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()) {
            return "menu-form";
        }
        Member member = memberService.findByUsername(principal.getName());
        Content content = new Content(contentForCreate);
        content.setCreateDate(LocalDateTime.now());
        content.setMember(member);
        contentService.save(content);
        return "redirect:/member/home";
    }
}
