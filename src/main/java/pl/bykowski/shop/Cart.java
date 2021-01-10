package pl.bykowski.shop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart{
    private List<product> productList;

    public Cart() {
        this.productList = new ArrayList<>();
    }

    public void addProductToCart(Product product)
    {
        productList.add(product);
    }

}
