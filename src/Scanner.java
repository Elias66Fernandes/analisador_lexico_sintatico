

public class Scanner {

    private final byte[] input;
    private int current;

    public Scanner (byte[] input) {
        this.input = input;
    }

    private char peek () {
        if (current < input.length)
            return (char)input[current];
        return '\0';
    }

    private void advance()  {
        char ch = peek();
        if (ch != '\0') {
            current++;
        }
    }

    private Token number() {
        int start = current ;
        while (Character.isDigit(peek())) {
            advance();
        }

        String n = new String(input, start, current-start)  ;
        return new Token(TokenType.NUMBER, n);
    }

    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            advance();
            ch = peek();
        }
    }

    public Token nextToken () {
        skipWhitespace();
        char ch = peek();
        if (ch == '0') {
            advance();
            return new Token (TokenType.NUMBER, Character.toString(ch));
        }  else if (Character.isDigit(ch))
            return number();


        return switch (ch) {
            case '+' -> {
                advance();
                yield new Token(TokenType.PLUS, "+");
            }
            case '-' -> {
                advance();
                yield new Token(TokenType.MINUS, "-");
            }
            case '\0' -> new Token(TokenType.EOF, "EOF");
            default -> throw new Error("lexical error at " + ch);
        };
    }
}