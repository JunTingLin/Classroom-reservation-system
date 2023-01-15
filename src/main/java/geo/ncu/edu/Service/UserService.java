package geo.ncu.edu.Service;

import geo.ncu.edu.dao.UserDAO;
import geo.ncu.edu.enity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> findAll(){
        return  userDAO.findAll();
    }
    public User save(User user){
        return userDAO.save(user);
    }
}
