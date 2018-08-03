package com.kodgemisi.course.ecommerce.product;

import com.kodgemisi.course.ecommerce.user.Role;
import com.kodgemisi.course.ecommerce.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductIntegrationTests {

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void getDashboard() throws Exception {
        mockMvc.perform(get("http://localhost:8080/"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAdminDashboardAndExpectRedirection() throws Exception {
        mockMvc.perform(get("http://localhost:8080/admin"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void getAdminDashboardWithUserRoleAndExpectForbidden() throws Exception {

        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.USER);
        user.setRoles(roleSet);

        mockMvc.perform(
                get("http://localhost:8080/admin")
                        .with(user(user))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void getAdminDashboardWithAdminRoleAndExpectSuccess() throws Exception {

        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.ADMIN);
        user.setRoles(roleSet);

        mockMvc.perform(
                get("http://localhost:8080/admin")
                        .with(user(user))
                )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void getGistSuccessfully() throws Exception {
        mockMvc.perform(
                    get("http://localhost:8080/api/products/get-gist")
                            .param("gistId", "5c8708d042db8b9a71bea40b823652b6")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").exists());
    }

}
