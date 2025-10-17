package com.serratec.livro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Classe que intercepta exceções lançadas pelos controllers e retorna
 * respostas padronizadas para o front-end.
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Trata erros de validação (@Valid) e retorna status 400 com detalhes.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest req) {
        // concatena mensagens do campo: "campo: mensagem"
        String mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                mensagens,
                req.getDescription(false) // ex: uri=/livros
        );

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler genérico para outras exceções inesperadas — evita expor stacktrace.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handleGeneric(Exception ex, WebRequest req) {
        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                ex.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
