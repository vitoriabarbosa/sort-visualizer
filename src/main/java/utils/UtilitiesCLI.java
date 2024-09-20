package main.java.utils;

/**
 * A classe Decoracao contém métodos utilitários para exibir mensagens e efeitos gráficos no terminal.
 * Esta classe fornece métodos para exibir um efeito gráfico, uma mensagem de uso, e o título do programa.
 */
public class UtilitiesCLI {
    private static final String ELEMENT = "|=".repeat(25);

    /**
     * Retorna uma string com um efeito gráfico personalizado para ser exibido no terminal.
     * O efeito inclui uma série de asteriscos organizados em um padrão visual.
     *
     * @return Uma string contendo o efeito gráfico.
     */
    public static void getEffectGraphics() {
        String effect = String.format("""
               \t\t             *           *
               \t\t    *        *        *  *
               \t\t    *     *  *        *  *
               \t\t    * %s  *  *  *     *  *
               \t\t *  * %s  *  *  *  *  *  *\s
                %s
               \s""", "\033[31m *\033[0m", "\033[31m *\033[0m", "\033[31m" + ELEMENT);
        System.out.print(effect);
    }

    /**
     * Retorna uma mensagem de uso que instrui o usuário sobre como compilar e executar o programa.
     * A mensagem inclui exemplos de comandos de compilação e execução.
     *
     * @return Uma string contendo a mensagem de uso.
     */
    public static void getMessageUses() {
        String message = """
            \nPara\033[31m compilar\033[0m use: make
            Para\033[32m executar\033[0m use: java -cp out main.java.MainApp a=<algoritmo> t=<tipo> o=<ordem> in=<tipo_valor_lista> [r=<num_elementos> | v=<valores_manuais>] s=<pausa(ms)>
            \n\033[33mExemplos:\033[0m
            
            \033[33mPara gerar valores aleatórios:\033[0m
            java -cp out main.java.MainApp a=b t=c o=az in=r r=20 s=100
            java -cp out main.java.MainApp a=q t=n o=az in=r r=50 s=100

            \033[33mPara passar os valores manualmente:\033[0m
            java -cp out main.java.MainApp a=s t=c o=za in=m v="H,r,x,C,Q,L,w,o,c,z,M,g,p,z,b,R,a,h" s=100
            java -cp out main.java.MainApp a=i t=n o=za in=m v="21,50,3,2,7,17,23,15,38,12,4,47,31" s=100
            """;
        System.out.println(message);
    }

    /**
     * Exibe o título do programa no terminal com uma decoração especial.
     * O título é estilizado com cores ANSI para destacar o nome do programa.
     */
    public static void showTitle() {
        System.out.println("\033[0m\t\t\tSORTING ALGORITHM VIEWER\n \033[31m" + ELEMENT + "\033[0m");
    }
}