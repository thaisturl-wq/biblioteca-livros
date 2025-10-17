package com.serratec.livro.exception;

import java.time.LocalDateTime;

/**
 * Modelo de resposta que será enviado ao cliente em caso de erro/validação.
 */
public class ErroResposta {
    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String mensagem;
    private String path;

    public ErroResposta(LocalDateTime timestamp, int status, String erro, String mensagem, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.path = path;
    }

    // getters (padrão)
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getErro() { return erro; }
    public String getMensagem() { return mensagem; }
    public String getPath() { return path; }
}
