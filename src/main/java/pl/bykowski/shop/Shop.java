package pl.bykowski.shop;

import org.springframework.beans.factory.annotation.Autowired;

public class Shop {

    private Cart cart;

    @Autowired
    public Shop (Cart cart){
        this.cart = cart;
    }

    public void purchase (){
        cart.addProductToCart(new Product(1, "keyboard"));
    }
}
