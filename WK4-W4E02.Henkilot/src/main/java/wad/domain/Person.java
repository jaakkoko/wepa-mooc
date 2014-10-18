package wad.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="Person")
public class Person extends AbstractPersistable<Long>{


}