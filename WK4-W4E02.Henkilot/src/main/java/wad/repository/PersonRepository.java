package wad.repository;

import wad.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    @Override
    public void delete(Long id);

}
