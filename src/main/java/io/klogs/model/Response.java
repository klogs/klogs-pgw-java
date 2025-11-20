package io.klogs.model;

public class Response {
    private boolean success;
    private Error error;

    public boolean getSuccess() {
        return getError() == null;
    }

    public Error getError() {
        return error;
    }

    public Response setError(Error error) {
        this.error = error;

        return this;
    }
}
