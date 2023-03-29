package at.htl.entity;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;

@Entity
@Table(name = "LB_ANSWER")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_ID")
    public Long id;

    @Column(name = "A_AnswerText")
    public String answerText;

    @ManyToOne
    public Question question;
}
