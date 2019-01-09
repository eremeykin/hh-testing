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

public class NewLineTests extends BaseTest {

    @Test
    public void noNewLine() throws IOException, URISyntaxException {
        test("Сортировщик");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
//        assertThat(vacancies, everyItem(nameContains(parameter)));
    }

    @Test
    public void windowsNewLine() throws IOException, URISyntaxException {
        test("Сортировщик\r\nупаковщик");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
    }

    @Test
    public void newLine() throws IOException, URISyntaxException {
        test("Сортировщик\nупаковщик");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
    }

    @Test
    public void carriageReturnNewLine() throws IOException, URISyntaxException {
        test("Сортировщик\rупаковщик");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
    }


}
