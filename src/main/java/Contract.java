
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
                                "numbers"
                        }
                )
        }
)
@NamedQueries({
        @NamedQuery( name = "Contract.getAll", query = "SELECT ct FROM Contract ct" ),
        @NamedQuery( name = "Contract.getAllByName", query = "SELECT ct FROM Contract ct ORDER BY ct.name" )
})


public class Contract implements Serializable{

    // идентификационный номер контракта
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false )
    private long id;

    // Номер
    @Column( name = "numbers", nullable = false )
    //@Pattern( regexp="\\(\\d{3}\\)\\d{3}-\\d{4}",
    //         message="{invalid.phonenumber}" )
    private String number;

    // Тариф
    @Column( name = "tariff", nullable = false )
    private String tariff;

    // Список выбранных опций
    @Column( name = "list_of_chosen_options" )
    private String chosenOptionList;

    public Contract(int i, String no, String t, String chOL) {

        this.number = no;
        this.tariff = t;
        this.chosenOptionList = chOL;
    }

    //сеттеры
    public void setId( long value )                 { id = value; }
    public void setNumber( String value )           { number = value; }
    public void setTariff( String value )           { tariff = value; }
    public void setChosenOptionList( String value ) { chosenOptionList = value; }

    //геттеры
    public long getId()                 { return id; }
    public String getNumber()           { return number; }
    public String getTariff()           { return tariff; }
    public String getChosenOptionList() { return chosenOptionList; }

    // выдать все одной строкой
    @Override
    public String toString() {
        return " \nContract: " + "\n" +
                " ID = " + id + "\n" +
                " Number = " +  number + "\n" +
                " Tariff = " + tariff + "\n" +
                " List of chosen options = " + chosenOptionList + "\n"
                ;
    }

}
