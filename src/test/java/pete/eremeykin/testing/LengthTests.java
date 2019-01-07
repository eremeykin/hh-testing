package pete.eremeykin.testing;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pete.eremeykin.testing.apiclient.ApiClient;
import pete.eremeykin.testing.apiclient.Response;
import pete.eremeykin.testing.apiclient.Vacancy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static pete.eremeykin.testing.MetaParameter.Builder.defaultOf;
import static pete.eremeykin.testing.asserts.ResponseMatchers.statusIsBadGateway;
import static pete.eremeykin.testing.asserts.ResponseMatchers.statusIsOk;

@RunWith(Parameterized.class)
public class LengthTests {


    private final MetaParameter parameter;

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
        MetaParameter emptyName = defaultOf("").build();

        MetaParameter shortName = defaultOf("Гид").build();

        MetaParameter mediumName = defaultOf("Менеджер").build();

        MetaParameter.Builder longNameBuilder = new MetaParameter.Builder("Шлифовщик-полировщик по прецизионной обработке полупроводниковых материалов");
        MetaParameter longName = longNameBuilder.checkResponse(statusIsOk()).build();

        StringBuilder stringBuilder = new StringBuilder();
        String base = "шлифовщик-полировщик";
        for (int i = 0; i < 500; i++) {
            stringBuilder.append(base);
            stringBuilder.append("-");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String extraLongString = stringBuilder.toString();
        MetaParameter.Builder extraLongBuilder = new MetaParameter.Builder(extraLongString);
        MetaParameter extraLongName = extraLongBuilder.checkResponse(statusIsBadGateway()).build();

        return Arrays.asList(new Object[][]{
                {emptyName},
                {shortName},
                {mediumName},
                {longName},
                {extraLongName}
        });
    }

    public LengthTests(MetaParameter parameter) {
        this.parameter = parameter;
    }

    @Test
    public void test() throws IOException, URISyntaxException {
        Response response = ApiClient.get(parameter.getName());
        for (Matcher<Response> responseMatcher : parameter.getResponseMatchers()) {
            assertThat(response, responseMatcher);
        }
        List<Vacancy> vacancies = Vacancy.fromJson(response.getText());
        for (Matcher<? super Collection<Vacancy>> vacanciesMatcher : parameter.getVacanciesMatchers()) {
            assertThat(vacancies, vacanciesMatcher);
        }
    }
}

