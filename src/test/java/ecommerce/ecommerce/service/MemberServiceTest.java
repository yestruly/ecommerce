package ecommerce.ecommerce.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecommerce.ecommerce.dto.MemberDto;
import ecommerce.ecommerce.dto.MemberDto.LogIn;
import ecommerce.ecommerce.dto.MemberDto.SignUp;
import ecommerce.ecommerce.entity.Member;
import ecommerce.ecommerce.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static ecommerce.ecommerce.entity.constants.Role.ROLE_CUSTOMER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;



    @Test
    void customerRegister() {
        MemberDto.SignUp member = new MemberDto.SignUp();
        member.setUsername("yestruly123");
        member.setPassword("aaa!!!123");
        member.setEmail("asdf123@gmail.com");
        member.setPhone("010-1111-1111");

        memberService.customerRegister(member);

        Optional<Member> findCustomer = memberRepository.findByUsername("yestruly123");
        assertThat(findCustomer).isPresent();

        Member savedMember = findCustomer.get();

        assertThat(savedMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(savedMember.getRole()).isEqualTo("ROLE_CUSTOMER");
        System.out.println("고객 회원가입 성공");

    }

    @Test
    void sellerRegister() {
        MemberDto.SignUp member = new MemberDto.SignUp();
        member.setUsername("yestruly1234");
        member.setPassword("aaa!!!1234");
        member.setEmail("asdf1234@gmail.com");
        member.setPhone("010-1111-1111");

        memberService.sellerRegister(member);

        Optional<Member> findCustomer = memberRepository.findByUsername("yestruly1234");
        assertThat(findCustomer).isPresent();

        Member savedMember = findCustomer.get();

        assertThat(savedMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(savedMember.getRole()).isEqualTo("ROLE_SELLER");
        System.out.println("판매자 회원가입 테스트 성공");
    }

    @Test
    void authenticateSuccess() {
        MemberDto.SignUp signUp = new MemberDto.SignUp();
        signUp.setUsername("yestruly1234");
        signUp.setPassword("aaa!!!1234");
        signUp.setEmail("asdf1234@gmail.com");
        signUp.setPhone("010-1111-1111");
        memberService.sellerRegister(signUp);

        MemberDto.LogIn logIn = new MemberDto.LogIn();
        logIn.setUsername("yestruly1234");
        logIn.setPassword("aaa!!!1234");
        Member authenticationMember = memberService.authenticate(logIn);

        assertThat(authenticationMember).isNotNull();
        assertThat(authenticationMember.getUsername()).isEqualTo(logIn.getUsername());
        System.out.println("로그인 성공");
    }

    @Test
    void updateMember() {
        System.out.println("안녕");
    }
}