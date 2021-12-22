package blog.mariantirlea.java.json_xml_list_serializer.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestXML {

    @Test
    void testXMLStringIsCorrectWhenProvidingObject() throws JsonProcessingException {

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.configure( ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );

        String xml = xmlMapper.writeValueAsString(DataFactory.createListOfTwoStudents());

        assertThat(xml).isEqualToNormalizingNewlines("""
            <?xml version='1.0' encoding='UTF-8'?>
            <students>
              <student>
                <name>John</name>
                <age>25</age>
              </student>
              <student>
                <name>Karl</name>
                <age>34</age>
              </student>
            </students>
            """);
    }

    @Test
    void testObjectIsCorrectWhenProvidingXMLString() throws JsonProcessingException {

        String xml = """
            <?xml version='1.0' encoding='UTF-8'?>
            <students>
              <student>
                <name>John</name>
                <age>25</age>
              </student>
              <student>
                <name>Karl</name>
                <age>34</age>
              </student>
            </students>
            """;

        XmlMapper xmlMapper = new XmlMapper();
        Students object = xmlMapper.readValue(xml, Students.class);

        assertThat(object).isEqualTo(DataFactory.createListOfTwoStudents());
    }

}
