package pete.eremeykin.testing.asserts;

import org.hamcrest.Matcher;
import pete.eremeykin.testing.apiclient.Response;

public class ResponseMatchers {

    private static final int CODE_OK = 200;
    private static final int CODE_BAD_GATEWAY = 502;

    public static Matcher<Response> statusIsOk() {
        return new ResponseCodeMatcher(CODE_OK);
    }

    public static Matcher<Response> statusIsBadGateway() {
        return new ResponseCodeMatcher(CODE_BAD_GATEWAY);
    }

}

