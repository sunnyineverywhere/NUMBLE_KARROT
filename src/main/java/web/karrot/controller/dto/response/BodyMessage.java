package web.karrot.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BodyMessage {
    private StatusEnum status;
    private String message;
    private Object data;

    @Builder
    public BodyMessage(StatusEnum status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
