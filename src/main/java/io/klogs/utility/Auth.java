package io.klogs.utility;

import io.klogs.ClientOptions;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.ClassicHttpRequest;

import java.time.Instant;
import java.util.Random;

public class Auth {
    private static final Random rnd = new Random();
    private static final char[] chars = "0123456789qwertyuopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
    private static final long TICKS_AT_UNIX_EPOCH = 621355968000000000L;

    private static long getUtcNowTicks() {
        Instant now = Instant.now(); // UTC
        long epochSeconds = now.getEpochSecond();
        int nano = now.getNano(); // 0..999,999,999
        long nanos = epochSeconds * 1_000_000_000L + nano;
        return nanos / 100 + TICKS_AT_UNIX_EPOCH;
    }

    public static void setApiKeyHeaders(ClassicHttpRequest http, ClientOptions options) {

        var randomString = RandomStringUtils.random(32, 0, 32, true, true, chars, rnd);
        var ticks = getUtcNowTicks();
        var apiKey = options.getApiKey();
        var signature = Hmac.Sha256(apiKey.concat(randomString).concat(Long.toString(ticks)), options.getSecretKey());

        http.setHeader("X-Api-Key", apiKey);
        http.setHeader("X-Klogs-Rnd", randomString);
        http.setHeader("X-Klogs-Timestamp", Long.toString(ticks));
        http.setHeader("X-Klogs-Signature", signature);
    }
}
