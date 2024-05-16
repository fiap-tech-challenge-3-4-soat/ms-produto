package br.com.tech.challenge.msproduto.infrastructure.http.resource.v1;

import br.com.tech.challenge.msproduto.application.dto.InputErrorDTO;
import br.com.tech.challenge.msproduto.core.domain.exception.ProdutoNaoEncontradoException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<InputErrorDTO> constraintViolation(ProdutoNaoEncontradoException exception) {
        return new ResponseEntity<>(new InputErrorDTO(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<InputErrorDTO> constraintViolation(ConstraintViolationException exception) {
        return new ResponseEntity<>(new InputErrorDTO(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
