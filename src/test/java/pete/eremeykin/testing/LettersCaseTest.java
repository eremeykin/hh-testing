package pete.eremeykin.testing;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Every.everyItem;
import static pete.eremeykin.testing.asserts.ResponseMatchers.statusIsOk;
import static pete.eremeykin.testing.asserts.VacancyMatchers.nameContains;

public class LettersCaseTest extends BaseTest {

    @Test
    public void upperCase() throws IOException, URISyntaxException {
        test("СЛЕСАРЬ");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
        assertThat(vacancies, everyItem(nameContains(parameter)));
    }

    @Test
    public void randomCase() throws IOException, URISyntaxException {
        test("сЛЕСарь");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
        assertThat(vacancies, everyItem(nameContains(parameter)));
    }

}
