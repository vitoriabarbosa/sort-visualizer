package main.java.utils;

/**
 * A classe InputValidator fornece métodos utilitários para validar entradas do usuário
 * e obter os nomes completos dos algoritmos de ordenação, tipos de dados e ordens de ordenação.
 */
public class InputValidator {

    public static boolean validateInput(String algorithm, String type, String order) {
        return (algorithm.matches("[SBIQ]") && type.matches("[NC]") && order.matches("AZ|ZA"));
    }

    public static String getAlgorithmFullName(String algorithm) {
        return switch (algorithm) {
            case "S" -> "Selection Sort (S)";
            case "B" -> "Bubble Sort (B)";
            case "I" -> "Insertion Sort (I)";
            case "Q" -> "Quick Sort (Q)";
            default -> null;
        };
    }

    public static String getTypeFullName(String type) {
        return switch (type) {
            case "N" -> "Numérico (N)";
            case "C" -> "Caractere (C)";
            default -> null;
        };
    }

    public static String getOrderFullName(String order) {
        return switch (order) {
            case "AZ" -> "Crescente (AZ)";
            case "ZA" -> "Decrescente (ZA)";
            default -> null;
        };
    }
}
