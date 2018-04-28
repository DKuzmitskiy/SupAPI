package sup;

import sup.config.AppConfig;
import sup.model.User;
import sup.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

//@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

    @Autowired
    private UserService userService;
    private User user;

//    @BeforeClass
//    public static void setUser(){
//        user = new User();
//        user.setName("test name");
//    }

    @Before
    public void addUserToDB(){
        user = new User();
        user.setName("test name");
        user.setPassword("password");
    }

    @Test
    // Should Get User
    public void test00(){
        user = userService.add(user);
        Assert.assertNotNull(userService.getAllUsers());
    }

    @Test
    // Should Get User By Id
    public void test01(){
        user = userService.add(user);
        Assert.assertNotNull(userService.getById(user.getId()));
    }

    @Test
    // Should Create User
    public void test02(){
        user = userService.add(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(user.getName(), "test name");
    }

    @Test
    // Should Update User
    public void test04(){
        user = userService.add(user);
        user.setName("new name");
        user = userService.update(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(user.getName(), "new name");
    }

    @Test
    // Should Delete User
    public void test05(){
        user = userService.add(user);
        user = userService.delete(user.getId());
        Assert.assertNull(userService.getById(user.getId()));
    }


}
