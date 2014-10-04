package wad.domain;

import javax.persistence.Entity;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Registration extends AbstractPersistable<Long> {

    @Length(min=4,max=30)
    private String name;
    @Length(min=4,max=50)
    private String address;
    @Email
    private String email;

    
    public String getName() {
        return name;
    }
    
    public void setName(@Valid String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(@Valid String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@Valid String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 53 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Registration other = (Registration) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.address == null) ? (other.address != null) : !this.address.equals(other.address)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }
}
