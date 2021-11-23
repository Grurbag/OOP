@echo off

set CD=%~dp0
set mainPath=timoshinov_i_b
set binPath=%CD%bin

set main=%mainPath%\main\*.class
set shapes=%mainPath%\shapes\*.class
set chessBoard=%mainPath%\chessBoard\*.class

cd %binPath%
jar cvfe ../Chess_1.jar timoshinov_i_b.main.Main %Main% %Shapes% %ChessBoard%