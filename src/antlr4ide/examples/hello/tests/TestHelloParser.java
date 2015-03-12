package antlr4ide.examples.hello.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import antlr4ide.examples.hello.parser.HelloLexer;
import antlr4ide.examples.hello.parser.HelloParser;

public class TestHelloParser {

	@Test
	public void test() throws IOException {
		ANTLRInputStream input = new ANTLRInputStream("hello world");
		HelloLexer lexer = new HelloLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HelloParser parser = new HelloParser(tokens);
        ParseTree tree = parser.r(); // begin parsing at 'r' rule
        assertEquals(tree.toStringTree(parser), "(r hello world)");
 	}

}
