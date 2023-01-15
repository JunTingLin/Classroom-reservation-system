package geo.ncu.edu.dao;

import geo.ncu.edu.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {

}
