package pete.eremeykin.testing;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static pete.eremeykin.testing.asserts.ResponseMatchers.statusIsBadGateway;
import static pete.eremeykin.testing.asserts.ResponseMatchers.statusIsOk;
import static pete.eremeykin.testing.asserts.VacancyMatchers.nameContains;

public class LengthTests extends BaseTest {

    private static final int LONG_NAME_REPETITIONS = 500;

    @Test
    public void emptyName() throws IOException, URISyntaxException {
        test("");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
    }

    @Test
    public void singleCharName() throws IOException, URISyntaxException {
        test("г");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
    }

    @Test
    public void shortName() throws IOException, URISyntaxException {
        test("гид");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
        assertThat(vacancies, everyItem(nameContains(parameter)));
    }


    @Test
    public void mediumName() throws IOException, URISyntaxException {
        test("Менеджер");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
        assertThat(vacancies, everyItem(nameContains(parameter)));
    }


    @Test
    public void longName() throws IOException, URISyntaxException {
        test("Шлифовщик полировщик по прецизионной обработке полупроводниковых материалов");
        assertThat(response, statusIsOk());
    }

    @Test
    public void extraLongName() throws IOException, URISyntaxException {
        StringBuilder stringBuilder = new StringBuilder();
        String base = "шлифовщик полировщик";
        for (int i = 0; i < LONG_NAME_REPETITIONS; i++) {
            stringBuilder.append(base);
            stringBuilder.append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String extraLongString = stringBuilder.toString();

        test(extraLongString);
        assertThat(response, statusIsBadGateway());
        assertThat(vacancies, everyItem(nameContains(parameter)));
    }
}
