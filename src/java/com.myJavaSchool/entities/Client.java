
package com.myJavaSchool.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "clients" )
@NamedQuery( name = "Client.getAll", query = "SELECT c FROM Client c" )
@NamedQuery( name = "Client.getOrderedByName", query = "SELECT c FROM Client c ORDER BY c.last_name, c.first_name" )

public class Client implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;                                    // идентификационный номер клиента
    @Column( name = "first_name", length = 30 )
    private String firstName;                           // Имя
    @Column( name = "last_name", length = 30 )
    private String lastName;                            // Фамилия
    @Column( name = "date_of_birth", length = 10 )
    private String  birthDay;                           // день рождения
    @Column( name = "passport_no", length = 10 )
    private long passNo;                                // номер паспорта
    @Column( name = "address", length = 200 )
    private String address;                             // Адрес
    @Column( name = "numbers", length = 20 )
    private String numbers;                             // номера телефонов  - стринг, потому что может соержать +-()
    @Column( name = "e-mail", length = 50 )
    private String eMail;                               // электронная почта
    @Column( name = "password", length = 10 )
    private String passWord;                            // пароль

    public Client( String fN, String lN, String dR, long pN, String adrs, String noms, String eM, String pW){

        this.firstName = fN;
        this.lastName = lN;
        this.birthDay = dR;
        this.passNo = pN;
        this.address = adrs;
        this.numbers = noms;
        this.eMail = eM;
        this.passWord = pW;

    }

    //сеттеры
    public void setId( long value ) { id = value; }
    public void setFirstName( String value ) { firstName = value; }
    public void setLastName( String value ) { lastName = value; }
    public void setBirthDay( String value ) { birthDay = value; }
    public void setPassNo( long value ) { passNo = value; }
    public void setAddress( String value ) { address = value; }
    public void setNumbers( String value ) { numbers = value; }
    public void setEmail( String value ) { eMail = value; }
    public void setPassWord( String value ) { passWord = value; }

    //геттеры
    public long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBirthDay() { return birthDay; }
    public long getPassNo() { return passNo; }
    public String getAddress() { return address; }
    public String getNumbers() { return numbers; }
    public String getEmail() { return eMail; }
    public String getPassWord() { return passWord; }

    // выдать все одной строкой
    @Override
    public String toString() {
        return " \nClient: " + "\n" +
                " ID = " + id + "\n" +
                " First Name = " +  firstName + "\n" +
                " Last Name = " + lastName + "\n" +
                " Birth Day = " + birthDay + "\n" +
                " Passport Number = " + passNo + "\n" +
                " Address = " + address + "\n" +
                " Numbers = " + numbers + "\n" +
                " E-mail = " + eMail + "\n" +
                " Password = " + passWord + "\n"
                ;
    }
}
