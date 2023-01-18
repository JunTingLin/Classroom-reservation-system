package geo.ncu.edu.enity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="classroom_id")
    private Classroom classroom;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


}
