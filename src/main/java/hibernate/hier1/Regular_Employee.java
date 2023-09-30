package hibernate.hier1;

import javax.persistence.*;

@Entity
@DiscriminatorValue("regularemployee")
public class Regular_Employee extends Employee{

    @Column(name="salary")
    private float salary;

    @Column(name="bonus")
    private int bonus;

//setters and getters
}