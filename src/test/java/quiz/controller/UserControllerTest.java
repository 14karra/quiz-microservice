package quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import quiz.controllerAdvice.UserExceptionHandler;
import quiz.entity.User;
import quiz.model.Response;
import quiz.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    private User expectedUser;
    private List<User> expectedUsers;
    private final String username = "JohnDoe";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new UserExceptionHandler())
                .setUseSuffixPatternMatch(false)
                .build();

        mapper = new ObjectMapper();

        expectedUser = new User();
        expectedUsers = new ArrayList<User>() {{
            add(expectedUser);
        }};

        when(userService.getAllUsers()).thenReturn(expectedUsers);
        when(userService.getUser(any(String.class))).thenReturn(expectedUser);
        when(userService.updateUser(any(User.class), any(String.class))).thenReturn(expectedUser);
        when(userService.deleteUser(any(String.class)))
                .thenReturn(new Response(HttpStatus.OK.value(), "User with username=" + username + " has been deleted"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(UserController.PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedUsers), result.getResponse().getContentAsString());
    }

    @Test
    public void testGetUser() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(UserController.PATH + "/{username}", username))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedUser), result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateUser() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(UserController.PATH + "/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(expectedUser), result.getResponse().getContentAsString());
    }

    @Test
    public void testDeleteUser() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(UserController.PATH + "/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(mapper.writeValueAsString(
                new Response(HttpStatus.OK.value(), "User with username=" + username + " has been deleted")),
                result.getResponse().getContentAsString());
    }
}
