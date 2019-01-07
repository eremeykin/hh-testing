package pete.eremeykin.testing.asserts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import pete.eremeykin.testing.apiclient.Response;

import java.io.IOException;

class ResponseItemsSize extends TypeSafeMatcher<Response> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final int itemSize;

    public ResponseItemsSize(int itemsSize) {
        this.itemSize = itemsSize;
    }

    @Override
    protected boolean matchesSafely(Response response) {
        try {
            JsonNode jsonNode = objectMapper.readTree(response.getText());
            int itemsSize = jsonNode.get("items").size();
            return itemsSize == this.itemSize;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("response has exactly " + itemSize + " items");
    }
}