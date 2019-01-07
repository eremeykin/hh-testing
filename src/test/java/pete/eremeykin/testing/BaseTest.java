package pete.eremeykin.testing;

import pete.eremeykin.testing.apiclient.ApiClient;
import pete.eremeykin.testing.apiclient.Response;
import pete.eremeykin.testing.apiclient.Vacancy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

class BaseTest {

    static final int DEFAULT_ITEMS_NUM = 20;

    Response response;
    List<Vacancy> vacancies;
    String name;

    void test(String name) throws IOException, URISyntaxException {
        this.name = name;
        this.response = ApiClient.getRaw(name.replace(" ", "%20"));
        this.vacancies = Vacancy.fromJson(response.getText());
    }
}
