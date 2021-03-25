package com.cesar31.analizador;

/**
 *
 * @author cesar31
 */
public class Token {
    
    public int type;
    public int line;
    public int column;
    public Object value;

    public Token(int type, int line, int column) {
        this.type = type;
        this.line = line;
        this.column = column;
    }

    public Token(int type, int line, int column, Object value) {
        this.type = type;
        this.line = line;
        this.column = column;
        this.value = value;
    }
}
