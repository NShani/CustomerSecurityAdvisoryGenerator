package org.wso2.security.advisory.handler;

import com.lowagie.text.DocumentException;
import org.w3c.dom.Document;
import org.wso2.msf4j.template.MustacheTemplateEngine;
import org.wso2.security.advisory.exception.AdminException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * This class will handle all the PDF generation related tasks.
 *
 */
public class PdfHandler {

    /**
     * This method populates the html from the template using the provided data.
     *
     * @param pdfInfoMap Map which contains the basic pdf details to be generated
     * @throws AdminException If the html string is {@code null}
     */
    public String populateHTML(Map<String, Object> pdfInfoMap, String templateName) throws AdminException {

        String html=MustacheTemplateEngine.instance().render(templateName,pdfInfoMap);
        if (html == null) {
            throw new AdminException("HTML generation failed.");
        }
        return html;
    }

    /**
     * This method creates the PDF.
     *
     * @param htmlString   This contains the generated html string
     * @param tempFilePath The temp file path which will be used to create the temp PDF file
     * @throws AdminException If the PDF creation fails
     */
    public void createPDF(String htmlString, String tempFilePath) throws AdminException {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(htmlString.getBytes(StandardCharsets.UTF_8));
            Document document = XMLResource.load(byteArrayInputStream).getDocument();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(document, "/");
            renderer.layout();
            try (FileOutputStream fileOutputStream = new FileOutputStream(tempFilePath)) {
                renderer.createPDF(fileOutputStream);
            }
        } catch (DocumentException | IOException e) {
            throw new AdminException("PDF creation failed: ", e);
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException ignore) {
                    //Ignored. At this point, the file is saved and the GC will remove the object later because there
                    //are no references to it.
                }
            }
        }
    }
}