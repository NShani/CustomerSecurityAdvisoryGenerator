package org.wso2.security.advisory.utils;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * This is used to populate the Pdf object from the data received from the PMT API.
 *
 */
public class Pdf {

    @SerializedName("pdf-title")
    private String pdfTitle = "Security Update for Multiple WSO2 Products ";

    @SerializedName("pdf-name")
    private String pdfName="WSO2-2017-0235";

    @SerializedName("pdf-date")
    private Date date;

    @SerializedName("severity")
    private String severity="Low";

    @SerializedName("score")
    private String score=  "6.3 (CVSS:3.0/AV:N/AC:L/PR:L/UI:R/S:U/C:H/I:N/A:L)";

    @SerializedName("overview")
    private String overview;

    @SerializedName("description")
    private String description;

    @SerializedName("impact")
    private String impact;

    @SerializedName("solution")
    private String solution;

    @SerializedName("public-disclosure")
    private String publicDisclosure;

    @SerializedName("notes")
    private String notes;

    @SerializedName("affected-products")
    private ArrayList<Product> affectedProducts = new ArrayList<>();

    @SerializedName("patch-list")
    private ArrayList<String> patchList;

    @SerializedName("patch-map")
    public Map<Product, String> getPatchMap() {
        return patchMap;
    }

    public void setPatchMap(Map<Product, String> patchMap) {
        this.patchMap = patchMap;
    }

    @SerializedName("patch-map")
    private Map<Product,String> patchMap;

    @SerializedName("thanks")
    private String thanks;


    public Pdf() throws IOException {
        setOverview();
        setDescription("d");
        setImpact("hh");
        setSolution("def");
        setPatchTable();
        setPatchList();
        setPublicDisclosure();
        setNotes();
        setDate();
        setThanks();

    }

    public void setDate() {
        date=new Date();
        System.out.println(date);
    }

    public Date getDate() {
        return date;
    }

    public String getThanks() {

        return thanks;
    }

    public void setThanks() {
        thanks="Thanks, WSO2 Team.";
    }

    public void setOverview(String overview) {
        overview="This is overview";
        this.overview = overview;
    }

    public void setPublicDisclosure( ) throws IOException {

        publicDisclosure="Please note the public disclosure date of this security update is xxth May 201x and therefore we strongly recommend that all affected customers apply the provided patch prior to that date.";
    }

    public void setNotes() {

        this.notes = "We have already tested these patches in-house. However we strongly recommend you to test this in your development/test environments before applying in the production setups.  ";
    }

    public ArrayList<String> getPatchList() {
        return patchList;
    }

    public void setPatchList() {
        patchList=new ArrayList<String>();
        patchList.add("WSO2-CARBON-PATCH-4.x.x-xxxx");
        patchList.add("WSO2-CARBON-PATCH-4.x.x-xxyy");
        patchList.add("WSO2-CARBON-PATCH-4.x.y-yyyy");
        patchList.add("WSO2-CARBON-PATCH-4.x.y-xyxy");
        patchList.add("WSO2-CARBON-PATCH-4.y.x-xxxx");
        patchList.add("WSO2-CARBON-PATCH-4.y.x-yyyy");
    }


    public void setOverview() {
        this.overview = "A potential authorization bypassing vulnerability is detected in the email templates page in management console of the above mentioned WSO2 Servers.";
    }

    public String getDescription() throws IOException {
        return description;
    }

    public void setDescription(String description) {
        this.description = "Modifying email templates of identity management notifications such as password recovery,account recovery, etc., is an administrative functionality supported from WSO2 server’s management console. Only users with admin privileges should be allowed to do such operations. It has been found that non admin users also can modify email templates provided that they can access the management console.";
    }

    public String getImpact() throws IOException {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = "An attacker having the management console access with a valid non-admin user account can change email templates to mislead users and even perform man in the middle attacks to steal information like account recovery confirmations, and a limited set of user account information.";
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution="Please see below for details on patching or updating the affected component:\n" +
                "For\u200B \u200B WSO2 Update Manager (WUM)\u200B Supported Products\n" +
                "Please use WUM to update the following products. Patch can be used in case WUM is not\n" +
                "applicable";
    }

    public String getTitle() {
        return pdfTitle;
    }

    public void setTitle(String title) {
        this.pdfTitle = title;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }


    public void setPatchTable() {

        ArrayList<String> versions= new ArrayList<>();
        versions.add("1.0.0");
        versions.add("1.0.1");
        affectedProducts.add(new Product("APIM","WSO2 API MAnager",versions));
        affectedProducts.add(new Product("APPM","WSO2 APP Manager",versions));
        affectedProducts.add(new Product("AppServer","WSO2 App Server",versions));
    }

    public ArrayList<Product> getAffectedProducts() {
        return affectedProducts;
    }

    public void setAffectedProducts(ArrayList<Product> affectedProducts) {
        this.affectedProducts = affectedProducts;
    }
}
