
package main.java;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "tariffs", uniqueConstraints = { @UniqueConstraint( columnNames = {"id"} ) } )

@NamedQueries({
        @NamedQuery( name = "Tariff.getAll", query = "SELECT t FROM Tariff t" ),
        @NamedQuery( name = "Tariff.getAllByName", query = "SELECT t FROM Tariff t ORDER BY t.name" )
})


public class Tariff implements Serializable{

    // идентификационный номер тарифа
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false )
    private long id;

    // Название
    @Column( name = "name", nullable = false )
    private String name;

    // Цена
    @Column( name = "price", nullable = false )
    private double price;

    // Список возможных опций
    @Column( name = "options" )
    private String optionList;

    public Tariff(int i, String n, double p, String oL){

        this.name = n;
        this.price = p;
        this.optionList = oL;
    }

    //сеттеры
    public void setId( long value )             { id = value; }
    public void setName( String value )         { name = value; }
    public void setPrice( double value )        { price = value; }
    public void setOptionList( String value )   { optionList = value; }

    //геттеры
    public long getId()             { return id; }
    public String getName()         { return name; }
    public double getPrice()        { return price; }
    public String getOptionList()   { return optionList; }

    // выдать все одной строкой
    @Override
    public String toString() {
        return " \nTariff: " + "\n" +
                " ID = " + id + "\n" +
                " Name = " +  name + "\n" +
                " Price = " + price + "\n" +
                " List of options = " + optionList + "\n"
                ;
    }
}
