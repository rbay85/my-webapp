
package main.java;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "contracts",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "id",
                                "phone"
                        }
                )
        }
)
@NamedQueries({
        @NamedQuery( name = "Contract.getAll", query = "SELECT ct FROM Contract ct" ),
        @NamedQuery( name = "Contract.getByPhone", query = "SELECT ct FROM Contract ct WHERE ct.phone = :name" )
})


public class Contract implements Serializable{

    private static final long serialVersionUID = 2L;

    // идентификационный номер контракта
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false )
    private long id;

    // Номер
    @Column( name = "phone", nullable = false )
    //@Pattern( regexp="\\(\\d{3}\\)\\d{3}-\\d{4}",
    //        message="{invalid phonenumber}" )
    private String phone;

    // Тариф
    @Column( name = "tariff_id", nullable = false )
    private int tariff_id;

    // Список выбранных опций
    @Column( name = "option_id" )
    private int option_id;

    // блокирока
    @Column( name = "lock" )
    private int lock;

    // пустой конструктор
    public Contract() {}

    public Contract( String phone, int tariff_id, int option_id, int lock) {

        this.phone = phone;
        this.tariff_id = tariff_id;
        this.option_id = option_id;
        this.lock = lock;
    }

    // сеттеры
    public void setNumber( String value )     { phone = value; }
    public void setTariff_id( int value )     { tariff_id = value; }
    public void setOption_id( int value )     { option_id = value; }
    public void setLock( int value )          { lock = value; }

    // геттеры
    public long getId()         { return id; }
    public String getNumber()   { return phone; }
    public int getTariff_id()   { return tariff_id; }
    public int getOption_id()   { return option_id; }
    public int getLock()        { return lock; }

    // выдать все одной строкой
    @Override
    public String toString() {
        return " \nContract: " + "\n" +
                " ID = " + id + "\n" +
                " Phone No = " +  phone + "\n" +
                " Tariff Id = " + tariff_id + "\n" +
                " Option Id = " + option_id + "\n" +
                " Lock = " + lock + "\n"
                ;
    }

}
