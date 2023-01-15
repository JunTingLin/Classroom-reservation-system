package geo.ncu.edu.Service;

import geo.ncu.edu.dao.UserDAO;
import geo.ncu.edu.enity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public Iterable<User> getUsers(){
        return  userDAO.findAll();
    }
    public Integer createUser(User user){
        return userDAO.save(user).getId();
    }
    public Boolean updateUser(Integer id, User newUser){
        Optional<User> isExistUser = findById(id);
        if (! isExistUser.isPresent()) {
            return false;
        }
        User user = isExistUser.get();
        newUser.setId(user.getId());
        userDAO.save(newUser);
        return true;
    }
    public Optional<User> findById(Integer id){
        return userDAO.findById(id);
    }
    public Boolean deleteUser(Integer id) {
        Optional<User> isExistUser = findById(id);
        if (!isExistUser.isPresent()) {
            return false;
        }
        userDAO.deleteById(id);
        return true;
    }

}
