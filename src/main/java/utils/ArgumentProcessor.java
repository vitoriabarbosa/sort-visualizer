package main.java.utils;

/**
 * Classe responsável por processar argumentos passados pela linha de comando
 * e validar sua conformidade com as expectativas do programa.
 */
public class ArgumentProcessor {

    /**
     * Classe interna que contém os parâmetros processados.
     */
    public static class ProcessedArgs {
        public String algorithm;
        public String type;
        public String order;
        public String valueListType;
        public int numElements;
        public String manualValues;
        public int pauseDuration;

        /**
         * Construtor da classe ProcessedArgs.
         *
         * @param algorithm     O algoritmo de ordenação especificado.
         * @param type          O tipo de dados a serem ordenados (ex: numérico, caractere).
         * @param order         A ordem de ordenação (ex: ascendente, descendente).
         * @param valueListType O tipo de entrada de valores (ex: manual ou aleatório).
         * @param numElements   O número de elementos a serem gerados aleatoriamente.
         * @param manualValues  Os valores manuais especificados.
         * @param pauseDuration A duração da pausa entre iterações durante a visualização.
         */
        public ProcessedArgs(String algorithm, String type, String order, String valueListType, int numElements, String manualValues, int pauseDuration) {
            this.algorithm = algorithm;
            this.type = type;
            this.order = order;
            this.valueListType = valueListType;
            this.numElements = numElements;
            this.manualValues = manualValues;
            this.pauseDuration = pauseDuration;
        }
    }

    /**
     * Processa os argumentos passados pela linha de comando e retorna uma instância de ProcessedArgs.
     *
     * @param args Os argumentos passados pela linha de comando.
     * @return Uma instância de ProcessedArgs contendo os parâmetros processados.
     */
    public static ProcessedArgs processArguments(String[] args) {
        String algorithm = null;
        String type = null;
        String order = null;
        String valueListType = null;
        int numElements = 0;
        String manualValues = null;
        int pauseDuration = 0;

        for (String arg : args) {
            if (arg.startsWith("a=")) {
                algorithm = arg.substring(2).toUpperCase();
            } else if (arg.startsWith("t=")) {
                type = arg.substring(2).toUpperCase();
            } else if (arg.startsWith("o=")) {
                order = arg.substring(2).toUpperCase();
            } else if (arg.startsWith("in=")) {
                valueListType = arg.substring(3).toUpperCase();
            } else if (arg.startsWith("r=")) {
                try {
                    numElements = Integer.parseInt(arg.substring(2));
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Número de elementos 'r=' deve ser um inteiro.");
                    System.exit(1);
                }
            } else if (arg.startsWith("v=")) {
                manualValues = arg.substring(2);
            } else if (arg.startsWith("s=")) {
                try {
                    pauseDuration = Integer.parseInt(arg.substring(2));
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Duração da pausa 's=' deve ser um inteiro.");
                    System.exit(1);
                }
            }
        }

        // verifica os parâmetros obrigatórios.
        if (algorithm == null || type == null || order == null || valueListType == null || pauseDuration == 0) {
            UtilitiesCLI.getEffectGraphics();
            UtilitiesCLI.showTitle();
            UtilitiesCLI.getMessageUses();
            System.exit(1);
        }

        // valida o tipo de lista.
        if ("r".equals(valueListType) && (numElements == 0)) {
            System.out.println("Erro: O número de elementos 'r=' deve ser especificado quando in='r'.");
            System.exit(1);
        } else if ("m".equals(valueListType) && (manualValues == null || manualValues.isEmpty())) {
            System.out.println("Erro: Os valores manuais 'v=' devem ser especificados quando in='m'.");
            System.exit(1);
        }

        // valida a entrada.
        if (!InputValidator.validateInput(algorithm, type, order, valueListType)) {
            System.out.println("Erro: Parâmetros inválidos.");
            System.exit(1);
        }

        return new ProcessedArgs(algorithm, type, order, valueListType, numElements, manualValues, pauseDuration);
    }
}