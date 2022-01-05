package blog.mariantirlea.java.jsonxmllistserializerwebapp.domain;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

class ClassAsArrayAdapter implements JsonSerializer<Students>, JsonDeserializer<Students> {
    @Override
    public JsonElement serialize(Students students, Type type, JsonSerializationContext context) {
        return context.serialize(students.getStudents());
    }

    @Override
    public Students deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        Students wrapper = new Students();
        wrapper.setStudents(context.deserialize(jsonElement, new TypeToken<ArrayList<Student>>() {}.getType()));
        return wrapper;
    }
}
