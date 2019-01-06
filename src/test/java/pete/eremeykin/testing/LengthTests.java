package pete.eremeykin.testing;

import org.junit.Test;
import pete.eremeykin.testing.pete.eremeykin.testing.apiclient.ApiClient;
import pete.eremeykin.testing.pete.eremeykin.testing.apiclient.Response;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

//@RunWith(Parameterized.class)
public class LengthTests {


    @Test
    public void testLength() throws IOException, URISyntaxException {
        Response response = ApiClient.get("");
        System.out.println(response.getText());
        assertThat(response.getCode(), is(200));
    }
}
