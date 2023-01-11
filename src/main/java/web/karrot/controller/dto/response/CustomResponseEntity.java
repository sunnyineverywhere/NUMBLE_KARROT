package web.karrot.controller.dto.response;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Getter
public class CustomResponseEntity<Message> extends ResponseEntity<BodyMessage> {

    public CustomResponseEntity(HttpStatusCode status) {
        super(status);
    }

    public CustomResponseEntity(Object body, HttpStatusCode status) {
        super((BodyMessage) body, status);
    }

    public CustomResponseEntity(HttpHeaders headers, HttpStatusCode status) {
        super(headers, status);
    }

    public CustomResponseEntity(Object body, HttpHeaders headers, HttpStatusCode status) {
        super((BodyMessage) body, headers, status);
    }

}
