
package com.myJavaSchool.entities;

import javax.persistence.*;
import java.io.Serializable;

public class Contract implements Serializable{

    private long id;                    // идентификационный номер контракта
    private String number;              // Название
    private String tariff;              // Цена
    private String chosenOptionList;    // Список возможных опций

    public Contract(long iD, String no, String t, String chOL) {

        this.id = iD;
        this.number = no;
        this.tariff = t;
        this.chosenOptionList = chOL;
    }

    //сеттеры
    public void setId( long value ) { id = value; }
    public void setNumber( String value ) { number = value; }
    public void setTariff( String value ) { tariff = value; }
    public void setChosenOptionList( String value ) { chosenOptionList = value; }

    //геттеры
    public long getId() { return id; }
    public String getNumber() { return number; }
    public String getTariff() { return tariff; }
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
