package blog.mariantirlea.java.jsonxmllistserializerwebapp;

import blog.mariantirlea.java.jsonxmllistserializerwebapp.domain.Student;
import blog.mariantirlea.java.jsonxmllistserializerwebapp.domain.Students;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
class StudentsController {

  private static final Students studentsList = new Students();

  static {
    Student john = new Student("John", (short) 25);
    Student karl = new Student("Karl", (short) 34);

    studentsList.setStudents(Arrays.asList(john, karl));
  }

  @GetMapping("/students")
  public Students getAll() {
    return studentsList;
  }

  @GetMapping("/students/{name}")
  public Student getByName(@PathVariable String name) {

    return studentsList.getStudents().stream().filter(student -> student.getName().equals(name)).findFirst().orElseThrow(() -> new RuntimeException("No Student found with name: " + name));

  }

}