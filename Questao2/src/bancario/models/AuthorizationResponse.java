package bancario.models;

public record AuthorizationResponse(boolean authorized,
                                    String code,
                                    String message) {

    @Override
    public String toString() {
        return "AuthorizationResponse{" +
                "authorized=" + authorized +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
