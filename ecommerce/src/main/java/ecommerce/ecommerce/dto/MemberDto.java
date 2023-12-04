package ecommerce.ecommerce.dto;

import ecommerce.ecommerce.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

import static ecommerce.ecommerce.entity.constants.Role.*;

@Data
public class MemberDto extends Member {
    @Data
    public static class LogIn {
        private String username;
        private String password;
    }

    @Data
    public static class SignUp{
        private String password;
        private String username;
        private String email;
        private String phone;
        private String address;
        private String role;

        public Member toCustomerEntity(){
            return Member.builder()
                    .username(this.username)
                    .password(this.password)
                    .email(this.email)
                    .phone(this.phone)
                    .address(this.address)
                    .role(String.valueOf((ROLE_CUSTOMER)))
                    .create_date(LocalDateTime.now())
                    .build();

        }

        public Member toSellerEntity(){
            return Member.builder()
                    .username(this.username)
                    .password(this.password)
                    .email(this.email)
                    .phone(this.phone)
                    .address(this.address)
                    .role(String.valueOf(ROLE_SELLER))
                    .create_date(LocalDateTime.now())
                    .build();

        }
    }

    @Data
    public class UpdateMember{
        private String password;
        private String email;
        private String phone;
        private String address;

    }
}
