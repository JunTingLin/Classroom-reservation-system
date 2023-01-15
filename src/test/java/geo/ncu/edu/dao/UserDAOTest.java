package geo.ncu.edu.dao;

import geo.ncu.edu.enity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    void getUsers(){
        System.out.println("輸出"+userDAO.findAll());
        System.out.println("型別"+(userDAO.findAll()).getClass().getSimpleName());
    }

    @Test
    void createUser(){
        User user = new User();
        user.setName("測試名");
        user.setNumber(108745874);
        user.setEmail("test@gmail.com");
        user.setAdmin(false);
        userDAO.save(user);
    }
}