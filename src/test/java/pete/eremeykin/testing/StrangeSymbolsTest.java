package pete.eremeykin.testing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static pete.eremeykin.testing.asserts.ResponseMatchers.statusIsOk;


@RunWith(Parameterized.class)
public class StrangeSymbolsTest extends BaseTest {

    private static String[] blns;

    private enum BlnIndex {
        UNDEFINED(1), NULL(3), NIL(6), TRUE(8), FALSE(9),
        NONE(14), BACK_SLASH_X2(16), BACK_SLASH_X4(17), ZERO(18), ONE(19),
        ONE_DOUBLE(20), DOLLAR_ONE_DOUBLE(21), HALF(22), EXP_NUM(25),
        MINUS_ONE(26), ZERO_DOT_DOT_ZERO(42), MINUS_DOT(56), NINE_NINE_ETC(58),
        NAN(59), INFINITY(60), PHP_BUG_NUM(88),
        STRANGE_SYMBOLS(89, 119), CHINESE(124, 133),
        STRANGE_SYMBOLS_2(134, 185), SQL(426, 429), FILES(453, 456);

        private int start;
        private int end;


        BlnIndex(int index) {
            this.start = index;
            this.end = -1;
        }

        BlnIndex(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    @Parameterized.Parameters(name = "{index}: parameter={0}")
    public static Iterable<Object[]> data() throws IOException {
        readBlns();
        List<Object[]> dataList = new ArrayList<>();
        for (BlnIndex index : BlnIndex.values()) {
            for (int i = index.start; i < index.end; i++) {
                dataList.add(new Object[]{blns[i]});
            }
        }
        return dataList;
    }


    public static void readBlns() throws IOException {
        InputStream stream = StrangeSymbolsTest.class.getClassLoader().getResourceAsStream("blns.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(stream);
        int linesSize = jsonNode.size();
        blns = new String[linesSize];
        for (int i = 0; i < linesSize; i++) {
            blns[i] = jsonNode.get(i).toString();
        }

    }

    public StrangeSymbolsTest(String name) {
        this.parameter = name;
    }

    @Test
    public void test() throws IOException, URISyntaxException {
        test(parameter);
        assertThat(response, statusIsOk());
    }
}
