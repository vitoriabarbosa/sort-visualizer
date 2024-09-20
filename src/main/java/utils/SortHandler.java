package main.java.utils;

import main.java.controller.SortController;
import main.java.view.SortPanel;

import java.util.Objects;

/**
 * A classe SortHandler é responsável por gerenciar o processo de ordenação,
 * coordenando entre o controlador de ordenação e o painel de visualização.
 * Suporta a ordenação de arrays de inteiros e caracteres usando vários
 * algoritmos de ordenação.
 * <p>
 * Exemplo de uso:
 * <pre>
 * {@code
 * SortController control = new SortController();
 * SortPanel panel = new SortPanel();
 * SortHandler handler = new SortHandler(control, panel);
 * handler.sort("Selection Sort (S)", new int[]{3, 1, 4, 1, 5});
 * handler.sort("Quick Sort (Q)", new char[]{'Z', 'A', 'C', 'B'});
 * }
 * </pre>
 * </p>
 */
public class SortHandler {
    private final SortController control;
    private final SortPanel panel;

    /**
     * Constrói um SortHandler com o controlador e o painel especificados.
     *
     * @param control o SortController que manipula os algoritmos de ordenação
     * @param panel   o SortPanel que visualiza o processo de ordenação
     */
    public SortHandler(SortController control, SortPanel panel) {
        this.control = control;
        this.panel = panel;
    }

    /**
     * Ordena um array de inteiros usando o algoritmo de ordenação especificado.
     * O resultado é exibido no SortPanel associado.
     * <p>
     * Algoritmos suportados:
     * <ul>
     *   <li>"Selection Sort (S)"</li>
     *   <li>"Bubble Sort (B)"</li>
     *   <li>"Insertion Sort (I)"</li>
     *   <li>"Quick Sort (Q)"</li>
     * </ul>
     * </p>
     *
     * @param algorithm o nome completo do algoritmo de ordenação a ser utilizado
     * @param array     o array de inteiros a ser ordenado
     * @throws NullPointerException se o algoritmo for nulo
     */

    public void sort(String algorithm, int[] array) {
        panel.setIntArray(array);
        switch (Objects.requireNonNull(algorithm)) {
            case "Selection Sort (S)" -> control.selectionSort(array);
            case "Bubble Sort (B)" -> control.bubbleSort(array);
            case "Insertion Sort (I)" -> control.insertionSort(array);
            case "Quick Sort (Q)" -> control.quickSort(array, 0, array.length - 1);
        }
    }

    /**
     * Ordena um array de caracteres usando o algoritmo de ordenação especificado.
     *
     * @param algorithm o nome completo do algoritmo de ordenação a ser utilizado
     * @param array     o array de caracteres a ser ordenado
     * @throws NullPointerException se o algoritmo for nulo
     */
    public void sort(String algorithm, char[] array) {
        panel.setCharArray(array);
        switch (Objects.requireNonNull(algorithm)) {
            case "Selection Sort (S)" -> control.selectionSort(array);
            case "Bubble Sort (B)" -> control.bubbleSort(array);
            case "Insertion Sort (I)" -> control.insertionSort(array);
            case "Quick Sort (Q)" -> control.quickSort(array, 0, array.length - 1);
        }
    }
}
