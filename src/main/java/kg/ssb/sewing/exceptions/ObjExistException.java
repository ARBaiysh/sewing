package kg.ssb.sewing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjExistException extends RuntimeException {
    public ObjExistException(String message) {
        super(message);
    }
}
