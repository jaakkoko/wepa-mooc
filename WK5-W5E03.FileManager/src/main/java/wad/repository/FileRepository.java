package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
