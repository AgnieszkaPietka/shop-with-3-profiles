package pl.bykowski.shop.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Cart{

    public Cart(List<Product> productList) {
        this.productList = productList;
    }

    public Cart() {
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public double getVatValue() {
        return vatValue;
    }

    public void setVatValue(double vatValue) {
        this.vatValue = vatValue;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    private List<Product> productList;

    @Value("${standard.vat.value}")
    private double vatValue;
    @Value("${discount.value}")
    private double discountValue;

    public double calculateBill(){
        double result = productList.stream()
                .map(Product::getPrice)
                .reduce(0.00, Double::sum);
        if (vatValue != 0 && discountValue != 0) {
            System.out.println("Dodano podatek VAT i rabat");
            return Math.round((((result += (result * vatValue))) - result * discountValue) * 100.00)/100.00;
        } else if (vatValue !=0 && discountValue == 0){
            System.out.println("Dodano podatek VAT");
            return Math.round((result + (result * vatValue)) * 100.00)/100.00;
        }
        System.out.println("Cena bez podatku i rabatu");
        return result;

    }

    public double generateRandomPrice(double min, double max) {
        double random = new Random().nextDouble();
        return Math.round((min + (random * (max - min))) * 100.00)/100.00;
    }

    public void addProductToCart() {

        productList = new ArrayList<>();
        productList.add(new Product(1,"klawiatura", generateRandomPrice(50.00, 300.00)));
        productList.add(new Product(2,"mysz komputerowa", generateRandomPrice(50.00, 300.00)));
        productList.add(new Product(3,"monitor", generateRandomPrice(50.00, 300.00)));
        productList.add(new Product(4,"pendrive", generateRandomPrice(50.00, 300.00)));
        productList.add(new Product(5,"głośniki", generateRandomPrice(50.00, 300.00)));

        setProductList(productList);
    }

}
