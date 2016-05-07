package io.pivotal.hackday.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by pivotal on 5/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String guid;
    private String installation_name;
    private String product_version;

    @Override
    public String toString() {
        return "Product{" +
                "guid='" + guid + '\'' +
                ", installation_name='" + installation_name + '\'' +
                ", product_version='" + product_version + '\'' +
                '}';
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getInstallation_name() {
        return installation_name;
    }

    public void setInstallation_name(String installation_name) {
        this.installation_name = installation_name;
    }

    public String getProduct_version() {
        return product_version;
    }

    public void setProduct_version(String product_version) {
        this.product_version = product_version;
    }
}
