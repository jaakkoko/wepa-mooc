package wad.repository;

import wad.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @RestResource(exported = false)
    @Override
    public void delete(Long id);

}
