package wad.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class File extends AbstractPersistable<Long> {
    
    private String name;
    private String mediaType;
    private Long size;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
}