@echo off

set CD=%~dp0
set mainPath=%CD%src\timoshinov_i_b

set Main=-d bin %mainPath%\main\*.java
set Shapes=-d bin %mainPath%\shapes\*.java
set ChessBoard=-d bin %mainPath%\chessBoard\*.java

javac  %Main% %Shapes% %ChessBoard%