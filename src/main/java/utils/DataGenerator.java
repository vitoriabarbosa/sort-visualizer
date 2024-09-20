package main.java.utils;

/**
 * A classe DataGenerator fornece métodos utilitários para gerar arrays de números inteiros
 * e caracteres aleatórios.
 * <p>
 * Exemplo de uso:
 * <pre>
 * {@code
 * int[] numeros = DataGenerator.generateRandomNumbers(10);
 * char[] caracteres = DataGenerator.generateRandomChars(10);
 * }
 * </pre>
 * </p>
 */
public class DataGenerator {

    /**
     * Gera um array de números inteiros aleatórios.
     *
     * @param numElements o número de elementos a serem gerados
     * @return um array de números inteiros aleatórios (positivos e negativos)
     */
    public static int[] randomNumbers(int numElements) {
        int[] array = new int[numElements];
        for (int i = 0; i < numElements; i++) {
            array[i] = (int) (Math.random() * 201) - 100;
        }
        return array;
    }

    /**
     * Gera um array de caracteres aleatórios.
     *
     * @param numElements o número de elementos a serem gerados
     * @return um array de caracteres aleatórios
     */
    public static char[] randomChars(int numElements) {
        char[] array = new char[numElements];
        for (int i = 0; i < numElements; i++) {
            array[i] = (char) (StrictMath.random() * 26 + 'A');
        }
        return array;
    }
}
