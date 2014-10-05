package wad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wad.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    public Page<Message> findAll(Pageable pageable);
}
