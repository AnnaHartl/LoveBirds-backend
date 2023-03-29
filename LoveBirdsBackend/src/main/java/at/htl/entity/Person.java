package at.htl.entity;

import javax.persistence.*;

@Entity
@Table(name = "LB_Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    public Long id;
    @Column(name = "P_Firstname")
    public String firstName;

    @Column(name = "P_CoupleId")
    public String coupleId;
}
