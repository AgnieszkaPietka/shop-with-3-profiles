package pl.bykowski.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import pl.bykowski.shop.model.Cart;
import pl.bykowski.shop.model.Product;


@Controller
public class CartController {

    private Cart cart;

    @Autowired
    public CartController(Cart cart){
        this.cart = cart;
        cart.addProductToCart();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getBill (){
        for (Product product : cart.getProductList()){
            System.out.println(product.getName() + " " + product.getPrice());
        }
        System.out.println("Wartość zakupów: " + cart.calculateBill());
    }
}
