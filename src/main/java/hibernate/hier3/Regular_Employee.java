package hibernate.hier3;


import javax.persistence.*;

@Entity
@Table(name="regularemployee103")
@PrimaryKeyJoinColumn(name="ID")
public class Regular_Employee extends Employee{

    @Column(name="salary")
    private float salary;

    @Column(name="bonus")
    private int bonus;

//setters and getters
}