package hibernate.hier3;

import javax.persistence.*;

@Entity
@Table(name = "employee103")
@Inheritance(strategy=InheritanceType.JOINED)
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

//setters and getters
}
