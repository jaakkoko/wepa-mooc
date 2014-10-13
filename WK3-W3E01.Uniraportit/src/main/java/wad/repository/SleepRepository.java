package wad.repository;

import wad.domain.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepRepository extends JpaRepository<Sleep, Long> {
    
}