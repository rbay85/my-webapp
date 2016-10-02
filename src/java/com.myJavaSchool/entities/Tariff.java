
package com.myJavaSchool.entities;

import javax.persistence.*;
import java.io.Serializable;

public class Tariff implements Serializable{

    private long id;            // идентификационный номер тарифа
    private String name;        // Название
    private double price;       // Цена
    private String optionList;  // Список возможных опций

    public Tariff(long iD, String n, double p, String oL){

        this.id = iD;
        this.name = n;
        this.price = p;
        this.optionList = oL;
    }

    //сеттеры
    public void setId( long value ) { id = value; }
    public void setName( String value ) { name = value; }
    public void setPrice( double value ) { price = value; }
    public void setOptionList( String value ) { optionList = value; }

    //геттеры
    public long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getOptionList() { return optionList; }

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
