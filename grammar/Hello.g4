/*
 * Example antlr4 grammar, reused from https://theantlrguy.atlassian.net/wiki/display/ANTLR4/Getting+Started+with+ANTLR+v4
 */
grammar Hello;

/*
 * By default, Antlr puts generated java files into the default java package.
 * That's not really best practice and makes 
 */
@header{ 
	package antlr4ide.examples.hello.parser; 
} 

r  : 'hello' ID ;         // match keyword hello followed by an identifier
ID : [a-z]+ ;             // match lower-case identifiers
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
