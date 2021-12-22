package blog.mariantirlea.java.json_xml_list_serializer.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import static blog.mariantirlea.java.json_xml_list_serializer.domain.DataFactory.createListOfTwoStudents;
import static org.assertj.core.api.Assertions.assertThat;

public class TestJSON {

    @Test
    void testJSONStringIsCorrectWhenProvidingObject() {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(createListOfTwoStudents());

        assertThat(json).isEqualToNormalizingNewlines("""
            [
              {
                "name": "John",
                "age": 25
              },
              {
                "name": "Karl",
                "age": 34
              }
            ]""");

    }

    @Test
    void testObjectIsCorrectWhenProvidingJSONString(){
        String json = """
            [
              {
                "name": "John",
                "age": 25
              },
              {
                "name": "Karl",
                "age": 34
              }
            ]""";

        Students object = new GsonBuilder().create().fromJson(json, Students.class);
        assertThat(object).isEqualTo(DataFactory.createListOfTwoStudents());

    }

}
