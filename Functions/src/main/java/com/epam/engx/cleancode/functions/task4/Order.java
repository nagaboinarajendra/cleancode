package com.epam.engx.cleancode.functions.task4;

import com.epam.engx.cleancode.functions.task4.thirdpartyjar.Product;

import java.util.Iterator;
import java.util.List;

public class Order {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        double orderPrice = 0.0;
        getAvaliableProducts();
        return getProductPrice(orderPrice);
    }

    private Double getProductPrice(double orderPrice) {
        for (Product p : products)
            orderPrice += p.getProductPrice();
        return orderPrice;
    }

    private void getAvaliableProducts() {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (isProductAvaliable(product))
                iterator.remove();
        }
    }

    private boolean isProductAvaliable(Product product) {
        return !product.isAvailable();
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
