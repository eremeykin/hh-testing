package pete.eremeykin.testing.asserts;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import pete.eremeykin.testing.apiclient.Response;

class ResponseCodeMatcher extends TypeSafeMatcher<Response> {

    private final int expectedCode;

    ResponseCodeMatcher(int expectedCode) {
        this.expectedCode = expectedCode;
    }

    @Override
    protected boolean matchesSafely(Response response) {
        return response.getCode() == expectedCode;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("response code is " + expectedCode);
    }

    @Override
    protected void describeMismatchSafely(Response item, Description description) {
        description.appendText("was " + item.getCode());
    }
}