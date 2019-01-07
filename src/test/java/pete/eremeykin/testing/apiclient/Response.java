package pete.eremeykin.testing.apiclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        try (InputStream inputStream = original.getEntity().getContent()) {
            HttpEntity entity = original.getEntity();
            Charset encoding = ContentType.getOrDefault(entity).getCharset();
            this.text = IOUtils.toString(inputStream, encoding);
            this.code = original.getStatusLine().getStatusCode();
        }
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        String pretty;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(getText());
            pretty = "\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (IOException e) {
            pretty = "broken json";
        }
        return "Response: " + pretty;
    }
}
