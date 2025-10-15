# Projeto: Compilador/Interpretador Simples (Java 21)

Este projeto demonstra a criação de um compilador e interpretador simples, implementando um pequeno pipeline de processamento de código: **Scanner (Análise Léxica) -> Parser (Análise Sintática e Geração de Código Intermediário) -> Interpretador (Execução)**.


## 🚀 Como Executar

O projeto pode ser executado através da classe principal `Main.java`.

1.  **Pré-requisitos:** Certifique-se de ter o **JDK 21** instalado e configurado.
2.  **Compilação:** Compile os arquivos Java.
    ```bash
    javac Main.java Parser.java Scanner.java Interpretador.java TokenType.java Token.java
    ```
3.  **Execução:** Execute a classe `Main`.
    ```bash
    java Main
    ```

O código de entrada está definido diretamente na classe `Main.java` e o programa exibirá o código intermediário gerado pelo `Parser` e, em seguida, o resultado da execução pelo `Interpretador`.

## 📂 Estrutura do Projeto

| Arquivo | Componente | Descrição |
| :--- | :--- | :--- |
| `TokenType.java` | Léxico | Definição dos tipos de *tokens* (símbolos) da linguagem. |
| `Token.java` | Léxico (Record) | Implementa um `record` para representar um *token* (tipo e lexema). |
| `Scanner.java` | Scanner | Responsável pela **Análise Léxica**. Converte a entrada de texto em uma sequência de *tokens*. |
| `Parser.java` | Parser | Responsável pela **Análise Sintática** e **Geração de Código Intermediário**. Ele segue uma gramática definida para gerar comandos de máquina de pilha. |
| `Interpretador.java` | Interpretador | Executa o código intermediário gerado pelo `Parser`. Implementa uma máquina de pilha com suporte a variáveis. Definição de um `record` simples para os comandos da máquina de pilha (`ADD`, `SUB`, `PUSH`, `POP`, `PRINT`). |
| `Main.java` | Principal | Contém o código de exemplo de entrada, inicializa o `Parser` e o `Interpretador`, e coordena a execução. |

## ⚙️ Funcionalidades Implementadas

O projeto implementa uma linguagem de script extremamente simples com as seguintes funcionalidades:

### Gramática Suportada

O `Parser` reconhece a seguinte gramática (notação EBNF simplificada):

* `<program>` ::= `<statement>`\*
* `<statement>` ::= `<letStatement>` \| `<printStatement>`
* `<letStatement>` ::= `'let'` `IDENT` `'='` `<expr>` `';'`
* `<printStatement>` ::= `'print'` `<expr>` `';'`
* `<expr>` ::= `<term>` (`'+'` `<term>` \| `'-'` `<term>`)\*
* `<term>` ::= `NUMBER` \| `IDENT`

### Máquina de Pilha e Variáveis

O código intermediário gerado pelo `Parser` utiliza comandos de uma máquina de pilha (Stack Machine) e é interpretado pela classe `Interpretador`.

| Comando | Descrição |
| :--- | :--- |
| `PUSH <valor>` | Coloca um valor (literal numérico ou o valor de uma variável) no topo da pilha. |
| `ADD` | Retira os dois valores do topo da pilha, soma-os e coloca o resultado de volta. |
| `SUB` | Retira os dois valores do topo da pilha, subtrai o segundo valor retirado do primeiro e coloca o resultado de volta. |
| `POP <nome_variavel>` | Retira o valor do topo da pilha e o armazena na variável especificada. |
| `PRINT` | Retira o valor do topo da pilha e o imprime na saída padrão. |

### Exemplo de Entrada (`Main.java`)

```java
let a = 42 + 2;
let b = 15 + 3;
print a + b;
