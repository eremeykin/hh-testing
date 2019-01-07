package pete.eremeykin.testing.asserts;

import pete.eremeykin.testing.apiclient.Response;
import pete.eremeykin.testing.apiclient.Vacancy;

public class VacancyMatchers {

    public static org.hamcrest.Matcher<Vacancy> nameContains(String substring) {
        return new VacancyNameContains(substring);
    }
}
