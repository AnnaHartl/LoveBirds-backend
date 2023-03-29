package at.htl.entity;

import javax.persistence.*;

@Entity
@Table(name = "LB_Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Q_ID")
    public Long id;

    @Column(name = "Q_QuestionText")
    public String questionText;

    @Column(name = "Q_Category")
    public Category category;
}
