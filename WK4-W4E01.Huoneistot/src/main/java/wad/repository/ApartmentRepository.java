package wad.repository;

import wad.domain.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    @Override
    public void delete(Long id);

}
