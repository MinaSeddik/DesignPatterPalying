package hibernate.order_hier;


import javax.persistence.*;

@Entity
@Table(name = "order")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="order_type",discriminatorType=DiscriminatorType.STRING)
public abstract class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

//setters and getters
}