package geo.ncu.edu.enity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ToString
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private  String name;
    private Integer number;
    private String email;
    private boolean isAdmin;

}
