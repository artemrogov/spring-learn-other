package bus.artemrogov.domain.response;

import java.util.List;

public class ErrorResponse {

    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    private String message;
    private List<String> details;
}
