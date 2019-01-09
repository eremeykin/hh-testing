package pete.eremeykin.testing;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static pete.eremeykin.testing.asserts.ResponseMatchers.statusIsOk;

public class SqlInjectionTest extends BaseTest {

    @Test
    public void singleQuote() throws IOException, URISyntaxException {
        test("Сортировщик' OR 1=1 -- 1");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
    }

    @Test
    public void doubleQuotes() throws IOException, URISyntaxException {
        test("Сортировщик\" OR 1=1");
        assertThat(response, statusIsOk());
        assertThat(vacancies, hasSize(DEFAULT_ITEMS_NUM));
    }

}
