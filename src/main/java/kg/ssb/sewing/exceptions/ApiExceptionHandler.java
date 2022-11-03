package kg.ssb.sewing.exceptions;

import kg.ssb.sewing.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ObjExistException.class})
    public ResponseEntity<?> objExistException(ObjExistException exception) {
        MessageResponse messageResponse = new MessageResponse(exception.getMessage());
        return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }
}

