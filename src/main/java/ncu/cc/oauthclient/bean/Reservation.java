package ncu.cc.oauthclient.bean;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String studentId;
    private  String chineseName;
    private String email;
    private String classroom;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String info;
    private boolean isBatch;

    @Transient
    private Date endDate; //臨時屬性，用在批量新增頁面
}
