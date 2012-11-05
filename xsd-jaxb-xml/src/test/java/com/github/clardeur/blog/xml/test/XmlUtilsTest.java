package com.github.clardeur.blog.xml.test;

import com.github.clardeur.blog.xml.XmlUtils;
import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLAssert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Units tests for {@see XmlUtils} functions.
 *
 * @author clardeur
 */
public class XmlUtilsTest {

    @Test
    public void testRemoveNamespace() throws IOException, TransformerException, SAXException {
        Source source = new StreamSource(XmlUtilsTest.class.getResourceAsStream("/xml/with-namespace.xml"));
        StreamResult result = new StreamResult(new StringWriter());
        XmlUtils.removeNamespace(source, result);
        InputStream expected = XmlUtilsTest.class.getResourceAsStream("/xml/without-namespace.xml");
        Diff diff = new Diff(inputStreamToString(expected), result.getWriter().toString());
        XMLAssert.assertXMLEqual(diff, diff.identical());
    }

    private String inputStreamToString(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer);
        return writer.toString();
    }
}
