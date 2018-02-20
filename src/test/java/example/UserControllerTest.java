package example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import example.config.AppConfig;
import example.controller.UserController;
import example.model.User;
import example.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void test_get_all_success() throws Exception {
        List<User> users = Arrays.asList(
                new User(1,"Daenerys Targaryen"),
                new User(2,"John Doe"));
        when(userService.getAllUsers()).thenReturn(users);
        mockMvc.perform(get("/api/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Daenerys Targaryen")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("John Doe")));
        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_add_success() throws Exception {
        User user = new User(1,"test");
        when(userService.add(user)).thenReturn(user);
//        doNothing().when(userService).add(user);    !!! void only !!!
//        MvcResult result = mockMvc.perform(post("/api/add")).andReturn();

        mockMvc.perform(post("/api/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(user))
        )
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("test")));
//        .andDo(print());

        verify(userService, times(1)).add(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_get_by_id_success() throws Exception {
        User user = new User(1,"test");
        when(userService.getById(1)).thenReturn(user);
        mockMvc.perform(get("/api/getById?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")));
        verify(userService, times(1)).getById(1);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_update_success() throws Exception {
        User user = new User(1,"test");
        when(userService.update(user)).thenReturn(user);
        mockMvc.perform(post("/api/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")));
        verify(userService, times(1)).update(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_delete_success() throws Exception {
        User user = new User(1, "test");
        when(userService.delete(1)).thenReturn(user);
        mockMvc.perform(delete("/api/delete/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")));
        verify(userService, times(1)).delete(1);
        verifyNoMoreInteractions(userService);
    }

    public static String asJsonString(final Object obj) {
        try {
//            System.out.println(new ObjectMapper().writeValueAsString(obj));
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
