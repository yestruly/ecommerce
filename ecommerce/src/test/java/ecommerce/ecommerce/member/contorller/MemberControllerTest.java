package ecommerce.ecommerce.member.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecommerce.ecommerce.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberDetailService;

    @Autowired
    ObjectMapper mapper;

    @Test
    @WithMockUser
    void signupSuccess() throws Exception{

    }

}