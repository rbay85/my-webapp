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
                                "passport_no",
                                "numbers",
                                "e-mail"
                        }
                )
        }
)

@NamedQueries({
        @NamedQuery( name = "Client.getAll", query = "SELECT c FROM Client c" ),
        @NamedQuery( name = "Client.getAllByName", query = "SELECT c FROM Client c ORDER BY c.last_name, c.first_name" )
})


public class Client implements Serializable {

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
    @Column( name = "date_of_birth" )
    @Temporal(value = TemporalType.DATE)
    private Date  birthDay;

    // номер паспорта
    @Column( name = "passport_no", length = 10, nullable = false )

    private long passNo;
    // Адрес
    @Column( name = "address" )
    private String address;

    // номера телефонов
    @Column( name = "numbers", nullable = false )
    //@Pattern( regexp="\\(\\d{3}\\)\\d{3}-\\d{4}",
    //          message="{invalid.phonenumber}" )
    private String numbers;

    // электронная почта
    @Column( name = "e-mail" )
    //@Email
    private String eMail;

    // пароль
    @Column( name = "password", nullable = false )
    private String passWord;

    public Client(int i, String fN, String lN, Date dR, long pN, String adrs, String noms, String eM, String pW){

        this.firstName = fN;
        this.lastName = lN;
        this.birthDay = dR;
        this.passNo = pN;
        this.address = adrs;
        this.numbers = noms;
        this.eMail = eM;
        this.passWord = pW;

    }

    // сеттеры
    public void setId( long value )             { id = value; }
    public void setFirstName( String value )    { firstName = value; }
    public void setLastName( String value )     { lastName = value; }
    public void setBirthDay( Date value )       { birthDay = value; }
    public void setPassNo( long value )         { passNo = value; }
    public void setAddress( String value )      { address = value; }
    public void setNumbers( String value )      { numbers = value; }
    public void setEmail( String value )        { eMail = value; }
    public void setPassWord( String value )     { passWord = value; }

    // геттеры
    public long getId()             { return id; }
    public String getFirstName()    { return firstName; }
    public String getLastName()     { return lastName; }
    public String getBirthDay()       {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format( birthDay );
    }
    public long getPassNo()         { return passNo; }
    public String getAddress()      { return address; }
    public String getNumbers()      { return numbers; }
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
                " Numbers = " + numbers + "\n" +
                " E-mail = " + eMail + "\n" +
                " Password = " + passWord + "\n"
                ;
    }
}
