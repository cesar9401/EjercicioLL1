#!/usr/bin/bash
echo "Compilando lexer..."
jflex lexer.flex
echo "Lexer compilado!"
echo "Copiando Lexer.java"
cp -i Lexer.java /home/cesar31/Java/Analizador/src/main/java/com/cesar31/analizador/
echo "FormsLex.java copiado!!!"
echo `date`
