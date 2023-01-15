package geo.ncu.edu.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    void findAll(){
        System.out.println(userDAO.findAll());
    }
}