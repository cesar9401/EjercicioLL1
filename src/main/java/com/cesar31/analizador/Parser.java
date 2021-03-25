package com.cesar31.analizador;

import java.io.IOException;

/**
 *
 * @author cesar31
 */
public final class Parser {

    private final int ID = 1;
    private final int NUM = 2;
    private final int LPAREN = 3;
    private final int RPAREN = 4;
    private final int PLUS = 5;
    private final int TIMES = 6;
    private final int EOF = 0;

    private Lexer lexer;
    private Token token;
    private boolean accept;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        accept = true;
        avanzar();
    }

    public void parse() {
        E();
    }

    private void avanzar() {
        try {
            token = lexer.yylex();
            if (token.type != 0) {
                System.out.println("avanzar -> " + token.value);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void consumir(int tk) {
        if (tk == token.type) {
            // Avanzar
            avanzar();
        } else {
            // Error
            System.out.println("Error al consumir, valor: " + token.value + ", Linea: " + token.line + ", Columna: " + token.column);
            System.out.println("tipo: " + tk);
            accept = false;
        }
    }

    private void E() {
        switch (token.type) {
            case ID:
            case NUM:
            case LPAREN:
                T();
                Ep();
                break;
            default:
                //Error
                error(token);
        }

        if (token.type == 0) {
            if (accept) {
                System.out.println("Cadena aceptada");
            }
        }
    }

    private void T() {
        switch (token.type) {
            case ID:
            case NUM:
            case LPAREN:
                F();
                Tp();
                break;
            default:
                //Error
                error(token);
        }
    }

    private void Ep() {
        switch (token.type) {
            case RPAREN:
            case EOF:
                // lambda
                break;

            case PLUS:
                // Verificar consumif(PLUS);
                consumir(PLUS);
                T();
                Ep();
                break;
            default:
                //Error
                error(token);
        }
    }

    private void F() {
        switch (token.type) {
            case ID:
                consumir(ID);
                break;

            case NUM:
                consumir(NUM);
                break;

            case LPAREN:
                consumir(LPAREN);
                E();
                consumir(RPAREN);
                break;
            default:
                error(token);
        }
    }

    private void Tp() {
        switch (token.type) {
            case RPAREN:
            case PLUS:
            case EOF:
                // lambda
                break;

            case TIMES:
                // Verificar consumir(TIMES);
                consumir(TIMES);
                F();
                Tp();
                break;

            default:
                //Error
                error(token);
        }
    }

    private void error(Token t) {
        System.out.println("Error, Valor: " + t.value + ", linea: " + t.line + ", columna: " + t.column);
        accept = false;
    }
}
