package wad.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.ShoppingCart;
import wad.repository.ItemRepository;

@Controller
@RequestMapping("/cart")
@Scope("session")
public class ShoppingCartController{

    @Autowired
    private HttpSession session;
    
    @Autowired 
    ItemRepository itemRepository;
    
   
    @RequestMapping(method=RequestMethod.GET)
    public String showCart(Model model){
        if(session.getAttribute("cart") != null){
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            model.addAttribute("items", cart.getItems());
        }else{
            ShoppingCart cart = new ShoppingCart();
            session.setAttribute("cart", cart);
            model.addAttribute("items", cart.getItems());
        }
        
        return "/WEB-INF/views/cart.jsp";
    }
    
    @Transactional
    @RequestMapping(value="/items/{id}", method = RequestMethod.POST)
    public String addToCart(@PathVariable long id){
        if(session.getAttribute("cart") != null){
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            cart.addToCart(this.itemRepository.findOne(id));
            session.setAttribute("cart", cart);
        }else{
            ShoppingCart cart = new ShoppingCart();
            cart.addToCart(this.itemRepository.findOne(id));
            session.setAttribute("cart", cart);
            
        }      
        return "redirect:/cart";
    }
    
    @Transactional
    @RequestMapping(value="/items/{id}",method = RequestMethod.DELETE)
    public String removeFromCart(@PathVariable long id){
        if(session.getAttribute("cart") != null){
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            cart.removeFromCart(this.itemRepository.findOne(id));
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }
}