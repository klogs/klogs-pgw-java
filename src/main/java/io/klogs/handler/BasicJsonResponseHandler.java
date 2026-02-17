package io.klogs.handler;

import io.klogs.model.Response;
import io.klogs.utility.Json;
import org.apache.hc.core5.http.ClassicHttpResponse;

import java.util.function.Function;

public class BasicJsonResponseHandler<T extends Response> {

    private Class<T> clazz = null;

    public  BasicJsonResponseHandler(Class<T> clazz){
        this.clazz = clazz;
    }

    public final Function<ClassicHttpResponse, T> handler = response -> {
        var entity = response.getEntity();

        try (var stream = entity.getContent()) {
            var content = new String(stream.readAllBytes());
            return Json.toObject(content, this.clazz);

        } catch (Exception ex) {
            return null;
        }
    };
}
