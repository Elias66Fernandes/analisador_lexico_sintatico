

public class Scanner {

    private final byte[] input;
    private int current;

    public Scanner (byte[] input) {
        this.input = input;
    }

    private String number() {
        int start = current ;
        while (Character.isDigit(peek())) {
            advance();
        }

        return new String(input, start, current-start);
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

    public String nextToken () {
        char ch = peek();
        if (ch == '0') {
            advance();
            return Character.toString(ch);
        }  else if (Character.isDigit(ch))
            return number();

        return switch (ch) {
            case '+', '-' -> {
                advance();
                yield Character.toString(ch);
            }
            default -> throw new Error("lexical error");
        };

    }
}