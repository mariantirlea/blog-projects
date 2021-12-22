package blog.mariantirlea.java.json_xml_list_serializer.domain;

import java.util.Arrays;

public class DataFactory {

    public static Students createListOfTwoStudents() {
        Student john = new Student("John", (short) 25);
        Student karl = new Student("Karl", (short) 34);

        Students studentsList = new Students();
        studentsList.setStudents(Arrays.asList(john, karl));

        return studentsList;
    }
}
