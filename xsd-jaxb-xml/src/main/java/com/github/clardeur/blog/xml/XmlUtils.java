package com.github.clardeur.blog.xml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Utilities methods for XML files.
 *
 * @author clardeur
 */
public class XmlUtils {

    private static final InputStream XSLT_REMOVE_NAMESPACE;

    static {
        XSLT_REMOVE_NAMESPACE = XmlUtils.class.getResourceAsStream("/remove-namespace.xslt");
        if (XSLT_REMOVE_NAMESPACE == null)
            throw new ExceptionInInitializerError(new FileNotFoundException("No XSLT resource is found!"));
    }

    /**
     * Remove all namespaces from the XML source into the XML output.
     *
     * @param xmlSource the XML source
     * @param xmlOutput the XML result
     * @throws TransformerException the TransformerException
     */
    public static void removeNamespace(Source xmlSource, Result xmlOutput) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(XSLT_REMOVE_NAMESPACE));
        transformer.transform(xmlSource, xmlOutput);
    }
}
