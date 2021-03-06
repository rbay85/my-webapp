package main.java.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Entity
@Table( name = "client" )

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
    @Column( name = "date" , nullable = false )
    @Temporal(value = TemporalType.DATE)
    @Past
    private Date  birthDay;

    // номер паспорта
    @Column( name = "passNo", length = 12, nullable = false )

    private String passNo;
    // Адрес
    @Column( name = "address" )
    private String address;

    // Юзер
    @OneToOne(mappedBy = "client")
    private User user;

    // номера контрактов
    @OneToMany( mappedBy = "client", cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List<Contract> contractList;


    // пустой конструктор
    public Client() {}

    // сеттеры
    public void setFirstName    ( String firstName )            { this.firstName = firstName; }
    public void setLastName     ( String lastName )             { this.lastName = lastName; }
    public void setBirthDay     ( Date birthDay )               { this.birthDay = birthDay; }
    public void setPassNo       ( String passNo )               { this.passNo = passNo; }
    public void setAddress      ( String address )              { this.address = address; }
    public void setUser         ( User user )                   { this.user = user; }
    public void setContractList ( List<Contract> contractList ) { this.contractList = contractList; }


    // геттеры
    public int getId()                      { return id; }
    public String getFirstName()            { return firstName; }
    public String getLastName()             { return lastName; }
    public String getPassNo()               { return passNo; }
    public String getAddress()              { return address; }
    public User getUser()                   { return user; }
    public List<Contract> getContractList() { return contractList; }
    public Date getBirthDayInDate()         { return birthDay; }
    public String getBirthDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format( birthDay );
    }


    // переопределения
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return " <br>" +
                " <br>client: " + id + "<br>" +
                " " +  firstName + "<br>" +
                " " + lastName + "<br>" +
                " " + dateFormat.format( birthDay ) + "<br>" +
                " passport: " + passNo + "<br>" +
                " address: " + address + "<br>" +
                " contracts: <br>" + contractList + "<br>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (!firstName.equals(client.firstName)) return false;
        if (!lastName.equals(client.lastName)) return false;
        if (birthDay != null ? !birthDay.equals(client.birthDay) : client.birthDay != null) return false;
        if (!passNo.equals(client.passNo)) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;
        return contractList != null ? contractList.equals(client.contractList) : client.contractList == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + passNo.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (contractList != null ? contractList.hashCode() : 0);
        return result;
    }
}
