package org.wso2.security.advisory.utils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * This is used to populate the Product object which is affected by patches, from the data received from the PMT API.
 *
 */
public class Product {

    @SerializedName("product-code")
    private String productCode;

    @SerializedName("product-name")
    private String productName;

    @SerializedName("patch-number")
    private String patchNumber;

    @SerializedName("product-versions")
    private ArrayList<String> version = new ArrayList<>();

    Product(String productCode, String productName, ArrayList<String> version){
        this.productCode=productCode;
        this.productName=productName;
        this.version= version;
    }

    public String getProductCode(){
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ArrayList<String> getVersion() {
        return version;
    }

    public String getPatchNumber() {
        return patchNumber;
    }

    public void setPatchNumber(String patchNumber) {
        this.patchNumber = patchNumber;
    }

}
