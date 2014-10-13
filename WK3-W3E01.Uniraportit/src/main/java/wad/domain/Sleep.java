package wad.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Sleep extends AbstractPersistable<Long> {
    
    @Temporal(TemporalType.DATE)
    @NotNull
    @DateTimeFormat(pattern = "d.M.y H.m")
    private Date start;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    @DateTimeFormat(pattern = "d.M.y H.m")
    private Date end;
    
    @NotBlank
    @NotNull
    @NotEmpty
    private String feeling;
    

    public void setStart(Date start){
        this.start = start;
    }
    
    public Date getStart(){
        return this.start;
    }
    
    public void setEnd(Date end){
        this.end = end;
    }
    
    public Date getEnd(){
        return this.end;
    }
    
    public void setFeeling(String feeling){
        this.feeling = feeling;
    }
    
    public String getFeeling(){
        return this.feeling;
    }
}
