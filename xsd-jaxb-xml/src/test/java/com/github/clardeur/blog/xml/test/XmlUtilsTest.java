package com.github.clardeur.blog.xml.test;

import com.github.clardeur.blog.xml.XmlUtils;
import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
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
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Units tests for {@see XmlUtils} functions.
 *
 * @author clardeur
 */
public class XmlUtilsTest {

    @Test
    public void testRemoveNamespace() throws IOException, TransformerException, SAXException {
        InputStream xmlSource = XmlUtilsTest.class.getResourceAsStream("/xml/with-namespace.xml");
        InputStream xmlExpected = XmlUtilsTest.class.getResourceAsStream("/xml/without-namespace.xml");
        StreamSource source = new StreamSource(xmlSource);
        StreamResult result = new StreamResult(new StringWriter());
        XmlUtils.removeNamespace(source, result);
        Diff diff = new Diff(inputStreamToString(xmlExpected), result.getWriter().toString());
        DetailedDiff detailedDiff = new DetailedDiff(diff);
        List<Difference> diffs = detailedDiff.getAllDifferences();
        assertNotNull(diffs);
        assertEquals(0, diffs.size());
    }

    private String inputStreamToString(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer);
        return writer.toString();
    }
}
