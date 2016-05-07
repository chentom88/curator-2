package io.pivotal.hackday.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pivotal on 5/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpsManagerInfo {
    private List<Product> products = new ArrayList<Product>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OpsManagerInfo{" +
                "products=" + products +
                '}';
    }
}
