package br.com.ga.exceptions;

public class ExpiredToken extends Exception {
    public ExpiredToken() {
        super("Token expirado");
    }

    public ExpiredToken(String msg) {
        super(msg);
    }
}
