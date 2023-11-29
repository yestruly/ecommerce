package ecommerce.ecommerce.member.contorller;


import ecommerce.ecommerce.config.security.TokenProvider;
import ecommerce.ecommerce.member.dto.MemberDto;
import ecommerce.ecommerce.member.entity.Member;
import ecommerce.ecommerce.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup/customer")
    public ResponseEntity<?> customerSignup(@RequestBody MemberDto.SignUp member) {
        var result = this.memberService.customerRegister(member);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/signup/seller")
    public ResponseEntity<?> sellerSignup(@RequestBody MemberDto.SignUp member) {
        var result = this.memberService.sellerRegister(member);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto.LogIn login) {
        var member = this.memberService.authenticate(login);
        var token = this.tokenProvider.generateToken(member.getUsername(), member.getRole());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("sessionId");
        return "logout";
    }
}
