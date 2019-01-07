package pete.eremeykin.testing.apiclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Vacancy {
    private String name;

    public Vacancy(String name) {
        this.name = name;
    }

    public static List<Vacancy> fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        if (jsonNode.get("items") == null) {
            return new ArrayList<>();
        }
        int itemsSize = jsonNode.get("items").size();
        List<Vacancy> vacancies = new ArrayList<>(itemsSize);
        for (int i = 0; i < itemsSize; i++) {
            String name = jsonNode.get("items").get(i).get("name").toString();
            vacancies.add(new Vacancy(name));
        }
        return vacancies;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "name='" + name + '\'' +
                '}';
    }
}
