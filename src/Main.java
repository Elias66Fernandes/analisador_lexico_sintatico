public class Main {
    public static void main(String[] args) {

        String input = """
            let a = 42 + 2;
            let b = 15 + 3;
            print a + b;
        """;

        Parser p = new Parser(input.getBytes());
        p.parse();

        System.out.println("--- Código Gerado ---");
        System.out.println(p.output());

        System.out.println("--- Código Interpretado ---");
        Interpretador i = new Interpretador(p.output());
        i.run();

    }
}

