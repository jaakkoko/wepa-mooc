package wad.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
// Order, joka tietokantataulun nimeksi tulisi oletuksena, 
// on tietokantasovelluksen varaama sana: nimetään taulu erikseen
@Table(name = "PRODUCTORDER")
public class Order extends AbstractPersistable<Long> {

    @Length(min=4,max=30)
    private String name;
    @Length(min=4,max=50)
    private String address;
    @NotEmpty
    private String[] items;

    public Order() {
    }

    public Order(String name, String address, String[] items) {
        this.name = name;
        this.address = address;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
}
