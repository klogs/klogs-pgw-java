package io.klogs.net;

import io.klogs.ClientOptions;
import io.klogs.model.Error;
import io.klogs.model.Response;
import io.klogs.utility.Auth;
import io.klogs.utility.Json;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.net.URI;
import java.util.Map;
import java.util.function.Function;

public class HttpClient {

    private final ClientOptions options;

    public HttpClient(ClientOptions options) {
        this.options = options;
    }

    protected <T extends Response> T get(String requestUri, Map<String, String> headers, Function<ClassicHttpResponse, T> responseHandler) {
        try (var httpclient = HttpClients.createDefault()) {

            var req = new HttpUriRequestBase("GET", new URI(this.options.getBaseURL() + requestUri));

            Auth.setApiKeyHeaders(req, this.options);

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    req.setHeader(header.getKey(), header.getValue());
                }
            }

            return httpclient.execute(req, response -> {
                var statusCode = response.getCode();

                if (statusCode >= 200 && statusCode < 300) {
                    return responseHandler.apply(response);
                }

                return (T)new Response().setError(new Error().setSummary("Error in http request"));
            });
        } catch (Exception e) {
            return (T) new Response().setError(new Error().setSummary(e.getMessage()));
        }
    }

    protected <T extends Response> T post(String requestUri, Object body, Map<String, String> headers, Function<ClassicHttpResponse, T> responseHandler) {
        try (var httpclient = HttpClients.createDefault()) {

            var payload = Json.toJson(body);

            var req = ClassicRequestBuilder.post(new URI(this.options.getBaseURL() + requestUri))
                    .setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                    .setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON))
                    .build();

            Auth.setApiKeyHeaders(req, this.options);

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    req.setHeader(header.getKey(), header.getValue());
                }
            }

            return httpclient.execute(req, response -> {
                var statusCode = response.getCode();

                if (statusCode >= 200 && statusCode < 300) {
                    return responseHandler.apply(response);
                }

                return (T)new Response().setError(new Error().setSummary("Error in http request"));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
