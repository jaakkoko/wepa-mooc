package wad.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private Map<Item, Long> items;

    public ShoppingCart() {
        this.items = new TreeMap<>();
    }

    public Map<Item, Long> getItems() {
        return items;
        
    }
    
    @Transactional
    public void addToCart(Item item) {
       if(this.items.get(item) != null){
           this.items.put(item, this.items.get(item) + 1);
       }else{
           this.items.put(item, Long.valueOf(1));
       }
       
    }
    
    @Transactional
    public void removeFromCart(Item item){
       
        this.items.put(item, this.items.get(item) - 1);
        if(this.items.get(item) == 0)this.items.remove(item);
    }
    
}