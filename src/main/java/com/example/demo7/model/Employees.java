package com.example.demo7.model;



import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="employees",uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="firstname")
    @NotNull
    @Size(min=20, max=30)
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "departement_id"), name = "departement_id")
    private Departement departement;



    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "competence_employee",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private Set<Competence> competences = new HashSet<>();





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Set<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
    }
}
