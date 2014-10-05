package wad.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Aircraft extends AbstractPersistable<Long> {

    private String name;
    @ManyToOne
    @JoinColumn
    private Airport airport;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the airport
     */
    public Airport getAirport() {
        return airport;
    }

    /**
     * @param airport the airport to set
     */
    public void setAirport(Airport airport) {
        this.airport = airport;
    }

        
}
