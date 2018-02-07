import java.io.*;

public class Stripper {

private States state;

public Stripper() {
	state = States.INCODE;
}

public void process(char ch) {

		switch(state) {
			case INCODE: 
				state = incode(ch);
				break;
			case INCOMMENT:
				state = incomment(ch);
				break;
			case INSTRING:
				state = instring(ch);
				break;
			case INLITERAL:
				state = inliteral(ch);
				break;
			case FOUNDSLASH:
				state = foundslash(ch);	
				break;
			case FOUNDSTAR:
				state = foundstar(ch);
				break;
			case FOUNDBSLASH1:
				state = foundbslash1(ch);
				break;
			case FOUNDBSLASH2:
				state = foundbslash2(ch);
				break;
			case INLINECOMMENT:
				state = inlinecomment(ch);
				break;
			default:
				break;
		}
}
private States incode(char ch) {

	switch(ch) {
		case '/':
			return States.FOUNDSLASH;
		case '"':
			System.out.print('"');
			return States.INSTRING;
		case '\'':
			System.out.print('\'');
			return States.INLITERAL;
		default:
			System.out.print(ch);
			return States.INCODE;
	}
}
private States incomment(char ch) {

	switch (ch) {
		case '*':
			return States.FOUNDSTAR;
		default:
			return States.INCOMMENT;
	}
}
private States instring(char ch) {

	switch (ch) {
		case '"':
			System.out.print('"');
			return States.INCODE;
		case '\\':
			System.out.print('\\');
			return States.FOUNDBSLASH1;
		default:
			System.out.print(ch);
			return States.INSTRING;	
	}
}
private States inliteral(char ch) {
	
	switch (ch) {
		case '\'':
			System.out.print('\'');	
			return States.INCODE;
		case '\\':
			System.out.print('\\');
			return States.FOUNDBSLASH2;
		default:
			System.out.print(ch);
			return States.INLITERAL;
	}
}
private States foundslash(char ch) {

	switch (ch) {
		case '*':
			return States.INCOMMENT;
		case '/':
			return States.INLINECOMMENT;
		default:
			System.out.print('/');
			System.out.print(ch);
			return States.INCODE;
	}
}
private States inlinecomment(char ch) {
	switch (ch) {
		case '\n':
			System.out.println();
			return States.INCODE;
		default:
			return States.INLINECOMMENT;
	}
}
private States foundstar(char ch) {
		
	switch (ch) {
		case '/':
			return States.INCODE;
		case '*':
			return States.FOUNDSTAR;
		default:
			return States.INCOMMENT;
	}
}
private States foundbslash1(char ch) {

	System.out.print(ch);
	return States.INSTRING;
}
private States foundbslash2(char ch) {

	System.out.print(ch);
	return States.INLITERAL;
}
}
