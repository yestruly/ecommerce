package ecommerce.ecommerce.controller;

import static ecommerce.ecommerce.entity.constants.Role.ROLE_CUSTOMER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecommerce.ecommerce.config.security.TokenProvider;
import ecommerce.ecommerce.dto.MemberDto;
import ecommerce.ecommerce.dto.MemberDto.LogIn;
import ecommerce.ecommerce.dto.MemberDto.SignUp;
import ecommerce.ecommerce.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private MemberService memberService;

  @MockBean
  private TokenProvider tokenProvider;

  @Test
  public void testCustomerSignup() throws Exception {
    MemberDto.SignUp customer = new MemberDto.SignUp();
    customer.setUsername("abc123");
    customer.setPassword("abcde1234!");
    customer.setAddress("서울시");
    customer.setPhone("010-1111-1111");
    customer.setEmail("abc123@gamil.com");


    mockMvc.perform(post("/member/signup/customer")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(customer)))
        .andExpect(status().isOk())
        .andDo(print());

  }

  @Test
  public void testSellerSignup() throws Exception {
    MemberDto.SignUp customer = new MemberDto.SignUp();
    customer.setUsername("def123");
    customer.setPassword("abcde1234!");
    customer.setAddress("서울시");
    customer.setPhone("010-1234-4567");
    customer.setEmail("abc123444@gamil.com");


    mockMvc.perform(post("/member/signup/seller")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(customer)))
        .andExpect(status().isOk())
        .andDo(print());

  }

  @Test
  public void loginTest() throws Exception{
    MemberDto.LogIn logIn = new LogIn();
    logIn.setUsername("abc123");
    logIn.setPassword("abcde1234!");

    mockMvc.perform(post("/member/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(logIn)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").exists()) // Assuming the response contains a token
        .andDo(print());
  }

}