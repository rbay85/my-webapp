
package main.java.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;


@Entity
@Table( name = "contract" )

@NamedQueries({
        @NamedQuery( name = "Contract.getAll", query = "SELECT ct FROM Contract ct" ),
        @NamedQuery( name = "Contract.getByPhone", query = "SELECT ct FROM Contract ct WHERE ct.phone = :phone" )
})


public class Contract implements Serializable{

    private static final long serialVersionUID = 2L;

    // идентификационный номер контракта
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false )
    private int id;

    // Номер
    @Column( name = "phone", nullable = false, unique = true )
    @Pattern( regexp = "\\(\\d{3}\\)\\d{3}-\\d{4}", message = "{invalid phone number!}" )
    private String phone;

    // идентификационный номер соответсвующего контракта
    @ManyToOne
    @JoinColumn( name = "client_id", nullable = false )
    private Client client;

    // Тариф
    @OneToOne
    @JoinColumn( name = "tariff_id", nullable = false )
    private Tariff tariff;

    // Выбранные опций
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinTable(
            name = "contract_option",
            joinColumns = @JoinColumn( name = "contract_id" ),
            inverseJoinColumns = @JoinColumn( name = "option_id" ) )
    private List<Option> optionList;

    // блокирока
    @Column( name = "is_locked" )
    private int is_locked;

    // пустой конструктор
    public Contract() {}

    // сеттеры
    public void setPhone        ( String phone )            { this.phone = phone; }
    public void setClient       ( Client client )           { this.client = client; }
    public void setTariff       ( Tariff tariff )           { this.tariff = tariff; }
    public void setOptionList   ( List<Option> optionList ) { this.optionList = optionList; }
    public void setIs_locked    ( int is_locked )           { this.is_locked = is_locked; }

    // геттеры
    public int getId()                  { return id; }
    public String getPhone()            { return phone; }
    public Client getClient()           { return client; }
    public Tariff getTariff()           { return tariff; }
    public List<Option> getOptionList() { return optionList; }
    public int getIs_locked()           { return is_locked; }

    // переопределения
    @Override
    public String toString() {
        return " <br>contract: " + id + "<br>" +
                " phone no: " +  phone + "<br>" +
                " " + tariff + "<br>" +
                " " + optionList + "<br>" +
                " lock: " + is_locked + "<br>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;

        Contract contract = (Contract) o;

        if (id != contract.id) return false;
        if (!phone.equals(contract.phone)) return false;
        if (!client.equals(contract.client)) return false;
        return tariff.equals(contract.tariff);

    }

    @Override
    public int hashCode() {
        int result = id;
        /*
        result = 31 * result + phone.hashCode();
        result = 31 * result + client.hashCode();
        result = 31 * result + tariff.hashCode();
        */
        return result;
    }
}
