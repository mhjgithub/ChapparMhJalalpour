package j.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "tb_customer")
public class Customer {
    // ____________________________________________________________ fields
    // _________________ ID  name  age
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMoshtari", unique = true)
    private Long idMoshtari;

//    @NotNull
//    @NotBlank
    @Column(name = "fN")
    @NotEmpty(message = "نام نیاز است و نمی‌تواند خالی باشد")
    private String fN;

    @Column(name = "ln")
    private String lN;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "email")
    private String email;
    // _________________ OneToMany
//    @OneToMany(mappedBy = "mhjCustomersTable" , fetch = FetchType.LAZY)
//    @OneToMany(fetch = FetchType.LAZY , orphanRemoval = true)
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    public Customer() {

    }

    public String getlN() {
        return lN;
    }

    public void setlN(String lN) {
        this.lN = lN;
    }

    // ____________________________________________________________ to string
    @Override
    public String toString() {
        return "مشتری" + "<" + idMoshtari + " " + fN + " "+ lN + " "+ email + " " + description + '>';
    }
    // ____________________________________________________________ auto generated constructor and setters and getters

//    @Override
    public String toStringOld() {
        return "Customer{" +
                "idMoshtari=" + idMoshtari +
                ", fN='" + fN + '\'' +
                ", ln='" + lN + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", orders=" + orders +
                '}';
    }

    public Long getIdMoshtari() {
        return idMoshtari;
    }

    public void setIdMoshtari(Long idMoshtari) {
        this.idMoshtari = idMoshtari;
    }

    public String getfN() {
        return fN;
    }

    public void setfN(String fN) {
        this.fN = fN;
    }

    public String getLN() {
        return lN;
    }

    public void setLN(String ln) {
        this.lN = ln;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Customer(Long idMoshtari, String fN, String lN, String description, String email, List<Order> orders) {
        this.idMoshtari = idMoshtari;
        this.fN = fN;
        this.lN = lN;
        this.description = description;
        this.email = email;
        this.orders = orders;
    }

    // ____________________________________________________________ getters & setters
    // ____________________________________________________________ constructor
}
