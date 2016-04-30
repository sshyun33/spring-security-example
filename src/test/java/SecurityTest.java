import com.rohaky.webmvc.service.CustomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        classes = {
                com.rohaky.webmvc.context.RootContext.class,
                com.rohaky.webmvc.context.WebContext.class,
                com.rohaky.webmvc.security.SecurityConfig.class
        }
)
public class SecurityTest {
        @Autowired
        private CustomService service;

        @Autowired
        private WebApplicationContext context;

        private MockMvc mvc;

        @Before
        public void setup() {
                mvc = MockMvcBuilders
                        .webAppContextSetup(context)
/*                        .defaultRequest(get("/").with(user("admin").authorities(() -> "PERM_ACCESS_LIST_PAGE",
                                                                                () -> "PERM_ACCESS_MAIN_PAGE")))*/
                        .apply(springSecurity())
                        .build();
        }

//        @Test(expected = AuthenticationCredentialsNotFoundException.class)
        @Test
        @WithMockUser(username = "jessi", authorities = {"PERM_ACCESS_LIST_PAGE"})
        public void testCustomService() {
                service.listPost();
        }

        @Test
        public void testAdminPage() throws Exception {
                mvc.perform(get("/admin").with(user("admin").authorities(new GrantedAuthority() {
                        @Override
                        public String getAuthority() {
                                return "PERM_ACCESS_ADMIN_PAGE";
                        }
                })))
                                .andExpect(status().isOk());
        }

        @Test
        public void testListPage() throws Exception {
                mvc.perform(get("/list").with(user("admin").authorities(() -> "PERM_ACCESS_LIST_PAGE")))
                        .andExpect(status().isOk());
        }

}
