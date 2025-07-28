package br.rafaelftdelima.address_manager.exception

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AddressManagerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException)
    static ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body([error: e.message])
    }

    @ExceptionHandler(IllegalArgumentException)
    static ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body([error: e.message])
    }

    @ExceptionHandler(Exception)
    static ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body([error: "Erro interno do servidor: ${ex.message}"])
    }
}
