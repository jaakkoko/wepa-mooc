package wad.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import wad.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    @RestResource(exported = false)
    @Override
    public void delete(Long id);
 
    @RestResource(exported= false)
    @Override
    public <S extends Reservation> S save(S s);

    
}
