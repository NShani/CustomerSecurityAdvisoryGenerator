package org.wso2.security.advisory.Manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.wso2.security.advisory.handler.PdfHandler;
import org.wso2.security.advisory.exception.AdminException;
import org.wso2.security.advisory.utils.Constants;
import org.wso2.security.advisory.utils.Pdf;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * This is the manager class which handles HTML generation from the template and PDF creation.
 */
public class PdfManager {

    private static TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {
    };

    public static void main(String[] args) throws AdminException {
        PdfManager pdfManager;
        try {
            pdfManager=new PdfManager();
            pdfManager.process();
        } catch (AdminException e) {
            throw new AdminException("HTML generation failed");
        }
    }

    /**
     * This method process all methods generate HTML and create the pdf.
     * @throws AdminException If the HTML generation fails or PDF creation fails
     */
    public void process() throws  AdminException {
        File tempFile = null;
        PdfHandler pdfHandler;
        try {
            pdfHandler =new PdfHandler();
            tempFile = File.createTempFile("temp", Long.toString(System.nanoTime()));
            System.out.println( Long.toString(System.nanoTime()));
            Pdf pdf=new Pdf();
            String templateName= Constants.HTML_TEMPLATE;
            Gson gson = new Gson();

            String jsonString = gson.toJson(pdf);
            Map<String,Object> pdfInfoMap  = gson.fromJson(jsonString, typeToken.getType());

            String htmlString= pdfHandler.populateHTML(pdfInfoMap,templateName);
            pdfHandler.createPDF(htmlString, tempFile.toString());
        } catch (AdminException e) {
            throw new AdminException("HTML generation failed.");
        }catch (IOException ignored){
            //ignored at this level
        }
    }
}
