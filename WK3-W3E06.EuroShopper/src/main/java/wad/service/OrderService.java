package wad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import wad.domain.Item;
import wad.domain.Order;
import wad.domain.OrderItem;
import wad.domain.ShoppingCart;
import wad.domain.UserDetails;
import wad.repository.ItemRepository;
import wad.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private HttpSession session;
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> list() {
        return orderRepository.findAll();
    }

    @Transactional
    public void placeOrder(UserDetails userDetails) {
        if(session.getAttribute("cart")!=null){
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        Map<Item,Long>map = cart.getItems();
        List<Item>items = new ArrayList<>(map.keySet());
        List<OrderItem>orderItems = new ArrayList<>();
        
        for(Item item : items){
            OrderItem oi = new OrderItem();
            oi.setItem(item);
            oi.setItemCount(map.get(item));
            orderItems.add(oi);
            for(long i=0; i<oi.getItemCount(); i++)cart.removeFromCart(item);
            session.setAttribute("cart", cart);
        }
        
        Order order = new Order();
        order.setUserDetails(userDetails);
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        }
    }
}
