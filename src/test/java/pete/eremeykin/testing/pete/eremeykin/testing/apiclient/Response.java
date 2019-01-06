package pete.eremeykin.testing.pete.eremeykin.testing.apiclient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class Response {

    private final HttpResponse original;
    private final String text;
    private final int code;


    Response(HttpResponse original) throws IOException {
        this.original = original;
        InputStream inputStream = original.getEntity().getContent();
        HttpEntity entity = original.getEntity();
        ContentType contentType = ContentType.getOrDefault(entity);
        Charset encoding = contentType.getCharset();
        this.text = IOUtils.toString(inputStream, encoding);
        this.code = original.getStatusLine().getStatusCode();
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
