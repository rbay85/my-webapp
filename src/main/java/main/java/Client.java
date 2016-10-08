package main.java;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(
        name = "clients",
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
    private long id;

    // Имя
    @Column( name = "first_name", nullable = false )
    private String firstName;

    // Фамилия
    @Column( name = "last_name", nullable = false )
    private String lastName;

    // день рождения
    @Column( name = "date" )
    @Temporal(value = TemporalType.DATE)
    private Date  birthDay;

    // номер паспорта
    @Column( name = "passport", length = 12, nullable = false )

    private String passNo;
    // Адрес
    @Column( name = "address" )
    private String address;

    // электронная почта
    @Column( name = "email" )
    //@Email
    private String eMail;

    // пароль
    @Column( name = "password", nullable = false )
    private String passWord;

    public Client( String fN, String lN, Date dR, String pN, String adrs, String eM, String pW){

        this.firstName = fN;
        this.lastName = lN;
        this.birthDay = dR;
        this.passNo = pN;
        this.address = adrs;
        this.eMail = eM;
        this.passWord = pW;

    }

    // сеттеры
    public void setFirstName( String value )    { firstName = value; }
    public void setLastName( String value )     { lastName = value; }
    public void setBirthDay( Date value )       { birthDay = value; }
    public void setPassNo( String value )       { passNo = value; }
    public void setAddress( String value )      { address = value; }
    public void setEmail( String value )        { eMail = value; }
    public void setPassWord( String value )     { passWord = value; }

    // геттеры
    public long getId()             { return id; }
    public String getFirstName()    { return firstName; }
    public String getLastName()     { return lastName; }
    public String getBirthDay()     {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format( birthDay );
    }
    public String getPassNo()       { return passNo; }
    public String getAddress()      { return address; }
    public String getEmail()        { return eMail; }
    public String getPassWord()     { return passWord; }

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
                " E-mail = " + eMail + "\n" +
                " Password = " + passWord + "\n"
                ;
    }
}
