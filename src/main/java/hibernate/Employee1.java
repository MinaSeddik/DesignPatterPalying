package hibernate;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.hibernate.annotations.Cache;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import spring.User;

@Entity
@Table(name="Employee",
        uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE, region="employee")
@Getter
@Setter
@Slf4j
public class Employee1 implements Serializable {

    @Id
//    GenerationType.IDENTITY : It is responsibility of database to generate unique identifier.
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true, length=11)
    private int id;

    @Column(name="name", length=20, nullable=true)
    private String name;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Column(name="role", length=20, nullable=true)
    private String role;

    @Column(name="insert_time", nullable=true)
    private Date insertTime;

//    Collections aren't cached by default, and we need to explicitly mark them as cacheable
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany
    private Collection<Address> addresses;


    @Transient
    int age;

    @CreatedBy
    private User creator;

    @LastModifiedBy
    private User modifier;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date modifiedAt;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

//    We don't have to specify the @Enumerated annotation at all if we're going to persist the Gender by the enum‘s ordinal.
    @Enumerated(EnumType.STRING)
    private Gender gender;


}