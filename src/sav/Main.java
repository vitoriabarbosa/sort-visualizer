package sav;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\033[35m************************************");
        System.out.println("\tSort Visualizer (Simples)");
        System.out.println("************************************\033[0m");

        if (args.length != 2) {
            System.out.println("\nPara\033[31m compilar\033[0m use: javac -d out src/sav/*.java");
            System.out.println("Para\033[32m executar\033[0m use: java -cp out sav.Main t=<tipo> v=<valores>");
            System.out.println("\n\033[33mExemplos:\033[0m");
            System.out.println("java -cp out sav.Main t=n v=\"0,-1,3,1,10\"");
            System.out.println("java -cp out sav.Main t=c v=\"z,a,Z,A,b\"");
            return;
        }

        String tipoParametro = args[0];
        String valoresParametro = args[1];

        if (!tipoParametro.startsWith("t=") || !valoresParametro.startsWith("v=")) {
            System.out.println("\nParâmetros inválidos. Use t=<tipo> e v=<valores>\n");
            return;
        }

        String tipo = tipoParametro.substring(2);
        String valoresString = valoresParametro.substring(2);

        if (valoresString.isEmpty()) {
            System.out.println("\nValores não fornecidos. Use v=<valores> com uma lista de valores separados por vírgula.\n");
            return;
        }

        String[] ListaValores = valoresString.split(",");

        int iteracoes = 0;
        if (tipo.equals("n")) {
            try {
                Integer[] valoresInt = new Integer[ListaValores.length];
                for (int i = 0; i < ListaValores.length; i++) {
                    valoresInt[i] = Integer.parseInt(ListaValores[i]);
                }
                iteracoes = IntegerSorter.sort(valoresInt);
                printArray(valoresInt);
            } catch (NumberFormatException e) {
                System.out.println("\nValores numéricos inválidos.\n");
                return;
            }
        } else if (tipo.equals("c")) {
            boolean caracterInvalido = false;
            Character[] valoresChar = new Character[ListaValores.length];
            for (int i = 0; i < ListaValores.length; i++) {
                if (ListaValores[i].length() != 1 || !Character.isLetter(ListaValores[i].charAt(0))) {
                    caracterInvalido = true;
                    break;
                }
                valoresChar[i] = ListaValores[i].charAt(0);
            }
            if (caracterInvalido) {
                System.out.println("\nValores de caracteres inválidos.\n");
                return;
            }
            iteracoes = CharacterSorter.sort(valoresChar);
            printArray(valoresChar);
        } else {
            System.out.println("\nTipo inválido. Use 'n' para numérico ou 'c' para caracter.\n");
            return;
        }

        System.out.println("\n Número de iterações: " + iteracoes + "\n");
    }

    private static <T> void printArray(T[] array) {
        System.out.print("\n Ordenação: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}