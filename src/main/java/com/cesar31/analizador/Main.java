package com.cesar31.analizador;

import java.io.StringReader;

/**
 *
 * @author cesar31
 */
public class Main {

    public static void main(String[] args) {
        // Write your code here
        String input = "5 + id + id2 * 3 + (5 * id3)";
        Lexer lexer = new Lexer(new StringReader(input));
        Parser parser = new Parser(lexer);
        parser.parse();
    }
}
