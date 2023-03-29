package at.htl.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LB_ANSWEREDQUESTION")
public class AnsweredQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AQ_ID")
    public Long id;

    @ManyToOne
    public Answer answer;

    @ManyToOne
    public Person person;

    public Date dateAnswered;
}
