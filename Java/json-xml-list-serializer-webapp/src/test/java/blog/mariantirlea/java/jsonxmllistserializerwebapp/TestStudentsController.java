package blog.mariantirlea.java.jsonxmllistserializerwebapp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestStudentsController {

    @LocalServerPort
    private int randomPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @CsvSource({
            "application/xml,<students><student><name>John</name><age>25</age></student><student><name>Karl</name><age>34</age></student></students>",
            "application/json,'[{\"name\":\"John\",\"age\":25},{\"name\":\"Karl\",\"age\":34}]'"
    })
    void testStudentsListIsReturnedCorrectlyForJSONAndXML(String contentType, String expectedString) throws URISyntaxException {
        final URI uri = new URI("http://localhost:" + randomPort + "/students/");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", contentType);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> stringResponse = restTemplate.exchange(
                uri, HttpMethod.GET, requestEntity, String.class);

        assertThat(stringResponse.getBody()).isEqualTo(expectedString);
    }

    @ParameterizedTest
    @CsvSource({
            "application/xml,<student><name>John</name><age>25</age></student>",
            "application/json,'{\"name\":\"John\",\"age\":25}'"
    })
    void testGetJohnStudentJSONAndXML(String contentType, String expectedString) throws Exception {

        final URI uri = new URI("http://localhost:" + randomPort + "/students/John");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", contentType);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> xmlResponse = restTemplate.exchange(
                uri, HttpMethod.GET, requestEntity, String.class);

        assertThat(xmlResponse.getBody()).isEqualTo(expectedString);
    }

}
