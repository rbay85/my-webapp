package main.java;

import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(
        name = "client",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "id",
                                "passport",
                                "email"
                        }
                )
        }
)

@NamedQueries({
        @NamedQuery( name = "Client.getAll", query = "SELECT c FROM Client c" ),
        @NamedQuery( name = "Client.getByName", query = "SELECT c FROM Client c WHERE c.lastName = :name" )
})


public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    // идентификационный номер клиента
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false )
    private int id;

    // Имя
    @Column( name = "first_name", nullable = false )
    private String firstName;

    // Фамилия
    @Column( name = "last_name", nullable = false )
    private String lastName;

    // день рождения
    @Column( name = "date" )
    @Temporal(value = TemporalType.DATE)
    @Past
    private Date  birthDay;

    // номер паспорта
    @Column( name = "passport", length = 12, nullable = false )

    private String passNo;
    // Адрес
    @Column( name = "address" )
    private String address;

    // номера контрактов
    @OneToMany( mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private Set<Contract> contractSet;

    // электронная почта
    @Column( name = "email" )
    @Email
    private String email;

    // пароль
    @Column( name = "password", nullable = false )
    private String passWord;

    // пустой конструктор
    public Client() {}

    // сеттеры
    public void setFirstName    ( String firstName )            { this.firstName = firstName; }
    public void setLastName     ( String lastName )             { this.lastName = lastName; }
    public void setBirthDay     ( Date birthDay )               { this.birthDay = birthDay; }
    public void setPassNo       ( String passNo )               { this.passNo = passNo; }
    public void setAddress      ( String address )              { this.address = address; }
    public void setContractSet  ( Set<Contract> contractSet )   { this.contractSet = contractSet; }
    public void setEmail        ( String email )                { this.email = email; }
    public void setPassWord     ( String passWord )             { this.passWord = passWord; }

    // геттеры
    public int getId()                      { return id; }
    public String getFirstName()            { return firstName; }
    public String getLastName()             { return lastName; }
    public String getPassNo()               { return passNo; }
    public String getAddress()              { return address; }
    public Set<Contract> getContractSet()   { return contractSet; }
    public String getEmail()                { return email; }
    public String getPassWord()             { return passWord; }
    public String getBirthDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format( birthDay );
    }

    // выдать все одной строкой
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return " \nClient: " + "\n" +
                " ID = " + id + "\n" +
                " First Name = " +  firstName + "\n" +
                " Last Name = " + lastName + "\n" +
                " Birth Day = " + dateFormat.format( birthDay ) + "\n" +
                " Passport Number = " + passNo + "\n" +
                " Address = " + address + "\n" +
                " Contracts = " + contractSet + "\n" +
                " E-mail = " + email + "\n" +
                " Password = " + passWord + "\n"
                ;
    }

    // переопределить equals, hashCode !
}
