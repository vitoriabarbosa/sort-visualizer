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
     *
     * @param array o array de inteiros a ser ordenado.
     * @return o tempo de execução em milissegundos.
     */
    @Override
    public long sort(int[] array) {
        long startTime = System.currentTimeMillis();
        executeSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Ordena um array de caracteres usando o algoritmo de ordenação rápida (Quick Sort).
     *
     * @param array o array de caracteres a ser ordenado.
     * @return o tempo de execução em milissegundos.
     */
    @Override
    public long sort(char[] array) {
        long startTime = System.currentTimeMillis();
        executeSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Executa o algoritmo Quick Sort em um array de inteiros.
     * Este método é chamado internamente para realizar a ordenação do array.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    @Override
    protected void executeSort(int[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    /**
     * Executa o algoritmo Quick Sort em um array de caracteres.
     * Este método é chamado internamente para realizar a ordenação do array.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    @Override
    protected void executeSort(char[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    /**
     * Método auxiliar para realizar a ordenação rápida em um array de inteiros.
     * Este método particiona o array e chama recursivamente o Quick Sort nos subarrays.
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
        panel.setCurrentBar(-1); // Restaura a cor original das barras
    }

    /**
     * Método auxiliar para realizar a ordenação rápida em um array de caracteres.
     * Este método particiona o array e chama recursivamente o Quick Sort nos subarrays.
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
        panel.setCurrentBar(-1); // Restaura a cor original das barras
    }

    /**
     * Particiona um array de inteiros em torno de um pivô, utilizado no Quick Sort.
     * Move todos os elementos menores que o pivô para a esquerda e os maiores para a direita.
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
                highlightBar(j); // Destaca o elemento sendo comparado
                sleep();
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        panel.setIntArray(array);
        highlightBar(high); // Destaca a nova posição do pivô
        sleep();
        return i + 1;
    }

    /**
     * Particiona um array de caracteres em torno de um pivô, utilizado no Quick Sort.
     * Move todos os elementos menores que o pivô para a esquerda e os maiores para a direita.
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
        highlightBar(i + 1); // Destaca a nova posição do pivô
        sleep();
        return i + 1;
    }
}
