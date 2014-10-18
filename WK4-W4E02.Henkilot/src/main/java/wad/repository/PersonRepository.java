package wad.repository;

import wad.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    @RestResource(exported = false)
    @Override
    public void delete(Long id);

}
