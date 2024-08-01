package main.java.model;

import main.java.view.SortPanel;

/**
 * A classe {@code QuickSort} implementa o algoritmo de ordenação rápida (Quick Sort).
 * Extende a classe {@code Sorting} e fornece a implementação específica do método de ordenação para arrays de inteiros e caracteres.
 */
public class QuickSort extends Sorting {

    /**
     * Construtor para inicializar a classe {@code QuickSort} com os parâmetros necessários.
     *
     * @param panel o painel onde a ordenação será visualizada.
     * @param ascending indica se a ordenação deve ser em ordem crescente ({@code true}) ou decrescente ({@code false}).
     * @param pauseDuration a duração da pausa entre as iterações da ordenação em milissegundos.
     */
    public QuickSort(SortPanel panel, boolean ascending, int pauseDuration) {
        super(panel, ascending, pauseDuration);
    }

    /**
     * Ordena um array de inteiros usando o algoritmo de ordenação rápida (Quick Sort).
     * A ordenação é realizada em um novo thread para permitir a visualização em tempo real.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    @Override
    public void sort(int[] array) {
        new Thread(() -> quickSortHelper(array, 0, array.length - 1)).start();
    }

    /**
     * Ordena um array de caracteres usando o algoritmo de ordenação rápida (Quick Sort).
     * A ordenação é realizada em um novo thread para permitir a visualização em tempo real.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    @Override
    public void sort(char[] array) {
        new Thread(() -> quickSortHelper(array, 0, array.length - 1)).start();
    }

    /**
     * Método auxiliar para realizar a ordenação rápida em um array de inteiros.
     *
     * @param array o array de inteiros a ser ordenado.
     * @param low o índice inicial do subarray.
     * @param high o índice final do subarray.
     */
    private void quickSortHelper(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSortHelper(array, low, pi - 1);
            quickSortHelper(array, pi + 1, high);
        }
        panel.setCurrentBar(-1); // Reset highlight when done
    }

    /**
     * Método auxiliar para realizar a ordenação rápida em um array de caracteres.
     *
     * @param array o array de caracteres a ser ordenado.
     * @param low o índice inicial do subarray.
     * @param high o índice final do subarray.
     */
    private void quickSortHelper(char[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSortHelper(array, low, pi - 1);
            quickSortHelper(array, pi + 1, high);
        }
        panel.setCurrentBar(-1); // Reset highlight when done
    }

    /**
     * Particiona o array de inteiros em torno de um pivô.
     *
     * @param array o array de inteiros a ser particionado.
     * @param low o índice inicial do subarray.
     * @param high o índice final do subarray.
     * @return o índice do pivô após a partição.
     */
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (ascending ? array[j] < pivot : array[j] > pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                panel.setIntArray(array);
                highlightBar(j);
                sleep();
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        panel.setIntArray(array);
        highlightBar(high);
        sleep();
        return i + 1;
    }

    /**
     * Particiona o array de caracteres em torno de um pivô.
     *
     * @param array o array de caracteres a ser particionado.
     * @param low o índice inicial do subarray.
     * @param high o índice final do subarray.
     * @return o índice do pivô após a partição.
     */
    private int partition(char[] array, int low, int high) {
        char pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (ascending ? array[j] < pivot : array[j] > pivot) {
                i++;
                char temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                panel.setCharArray(array);
                highlightBar(j);
                sleep();
            }
        }
        char temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        panel.setCharArray(array);
        highlightBar(i + 1); // Highlight the new position of pivot
        sleep();
        return i + 1;
    }
}
