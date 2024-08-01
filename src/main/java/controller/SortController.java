package main.java.controller;

import main.java.model.BubbleSort;
import main.java.model.InsertionSort;
import main.java.model.QuickSort;
import main.java.model.SelectionSort;
import main.java.view.SortPanel;

/**
 * A classe {@code SortController} é responsável por controlar a execução de algoritmos de ordenação.
 * Ela delega a execução dos algoritmos de ordenação para as classes específicas de algoritmos,
 * fornecendo um painel para visualização, a duração da pausa entre iterações e a ordem de ordenação.
 */
public class SortController {
    private SortPanel panel;
    private int pauseDuration;
    private boolean ascending;

    /**
     * Construtor para inicializar o {@code SortController}.
     *
     * @param panel o painel onde a ordenação será visualizada.
     * @param pauseDuration a duração da pausa entre as iterações do algoritmo de ordenação.
     * @param order a ordem de ordenação ("Ascending (ZA)" para crescente, qualquer outro valor para decrescente).
     */
    public SortController(SortPanel panel, int pauseDuration, String order) {
        this.panel = panel;
        this.pauseDuration = pauseDuration;
        this.ascending = "Ascending (ZA)".equals(order);
    }

    /**
     * Executa o algoritmo de ordenação por bolha (Bubble Sort) para um array de inteiros.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    public void bubbleSort(int[] array) {
        new BubbleSort(panel, ascending, pauseDuration).sort(array);
    }

    /**
     * Executa o algoritmo de ordenação por bolha (Bubble Sort) para um array de caracteres.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    public void bubbleSort(char[] array) {
        new BubbleSort(panel, ascending, pauseDuration).sort(array);
    }

    /**
     * Executa o algoritmo de ordenação por seleção (Selection Sort) para um array de inteiros.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    public void selectionSort(int[] array) {
        new SelectionSort(panel, ascending, pauseDuration).sort(array);
    }

    /**
     * Executa o algoritmo de ordenação por seleção (Selection Sort) para um array de caracteres.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    public void selectionSort(char[] array) {
        new SelectionSort(panel, ascending, pauseDuration).sort(array);
    }

    /**
     * Executa o algoritmo de ordenação por inserção (Insertion Sort) para um array de inteiros.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    public void insertionSort(int[] array) {
        new InsertionSort(panel, ascending, pauseDuration).sort(array);
    }

    /**
     * Executa o algoritmo de ordenação por inserção (Insertion Sort) para um array de caracteres.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    public void insertionSort(char[] array) {
        new InsertionSort(panel, ascending, pauseDuration).sort(array);
    }

    /**
     * Executa o algoritmo de ordenação rápida (Quick Sort) para um array de inteiros.
     *
     * @param array o array de inteiros a ser ordenado.
     * @param low o índice inicial do intervalo a ser ordenado.
     * @param high o índice final do intervalo a ser ordenado.
     */
    public void quickSort(int[] array, int low, int high) {
        new QuickSort(panel, ascending, pauseDuration).sort(array);
    }

    /**
     * Executa o algoritmo de ordenação rápida (Quick Sort) para um array de caracteres.
     *
     * @param array o array de caracteres a ser ordenado.
     * @param low o índice inicial do intervalo a ser ordenado.
     * @param high o índice final do intervalo a ser ordenado.
     */
    public void quickSort(char[] array, int low, int high) {
        new QuickSort(panel, ascending, pauseDuration).sort(array);
    }
}
