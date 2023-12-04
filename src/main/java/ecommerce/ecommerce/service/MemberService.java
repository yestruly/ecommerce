package ecommerce.ecommerce.service;

import ecommerce.ecommerce.dto.MemberDto;
import ecommerce.ecommerce.entity.Member;
import ecommerce.ecommerce.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("해당 고객 없음"));

    }

    //고객 회원등록
    public Member customerRegister(MemberDto.SignUp member) {
        boolean exist = this.memberRepository.existsByUsername(member.getUsername());

        if(exist){
            throw new RuntimeException("이미 존재하는 고객");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return this.memberRepository.save(member.toCustomerEntity());
    }

    //판매자 회원등록
    public Member sellerRegister(MemberDto.SignUp member) {
        boolean exist = this.memberRepository.existsByUsername(member.getUsername());

        if(exist){
            throw new RuntimeException("이미 존재하는 판매자");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return this.memberRepository.save(member.toSellerEntity());
    }

    //로그인
    public Member authenticate(MemberDto.LogIn login) {
        var member= memberRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new RuntimeException("해당 회원 없음"));

        if (!passwordEncoder.matches(login.getPassword(), member.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        return member;
    }

    //회원 정보 수정
    @Transactional
    public void updateMember(String username, MemberDto.UpdateMember updateMember, Principal principal){
        String memberName = principal.getName();
        if(!memberName.equals(username)){
            throw new RuntimeException("일치하지 않는 회원");
        }

        Member member = memberRepository.findByUsername(memberName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        member.setPassword(passwordEncoder.encode(updateMember.getPassword()));
        member.setPhone(updateMember.getPhone());
        member.setEmail(updateMember.getEmail());
        member.setAddress(updateMember.getAddress());
        member.setUpdate_date(LocalDateTime.now());
        memberRepository.save(member);
    }
}
