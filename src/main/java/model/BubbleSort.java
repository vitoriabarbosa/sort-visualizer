package main.java.model;

import main.java.view.SortPanel;

/**
 * A classe {@code BubbleSort} implementa o algoritmo de ordenação por bolha (Bubble Sort).
 * Extende a classe {@code Sorting} e fornece a implementação específica do método de ordenação para arrays de inteiros e caracteres.
 */
public class BubbleSort extends Sorting {

    /**
     * Construtor para inicializar a classe {@code BubbleSort} com os parâmetros necessários.
     *
     * @param panel o painel onde a ordenação será visualizada.
     * @param ascending indica se a ordenação deve ser em ordem crescente ({@code true}) ou decrescente ({@code false}).
     * @param pauseDuration a duração da pausa entre as iterações da ordenação em milissegundos.
     */
    public BubbleSort(SortPanel panel, boolean ascending, int pauseDuration) {
        super(panel, ascending, pauseDuration);
    }

    /**
     * Ordena um array de inteiros usando o algoritmo de ordenação por bolha.
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
     * Ordena um array de caracteres usando o algoritmo de ordenação por bolha.
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
     * Executa a ordenação por bolha em um array de inteiros.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    @Override
    protected void executeSort(int[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (ascending ? array[j] > array[j + 1] : array[j] < array[j + 1]) {
                    // Troca os elementos em um array de inteiros.
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                    panel.setIntArray(array);
                    highlightBar(j);  // Destaca o elemento trocado
                    sleep();
                }
            }
            if (!swapped) break;
        }
        highlightBar(-1); // Restaura a cor original das barras
    }

    /**
     * Executa a ordenação por bolha em um array de caracteres.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    @Override
    protected void executeSort(char[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (ascending ? array[j] > array[j + 1] : array[j] < array[j + 1]) {
                    // Troca os elementos em um array de caracteres.
                    char temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                    panel.setCharArray(array);
                    highlightBar(j);  // Destaca o elemento trocado
                    sleep();
                }
            }
            if (!swapped) break;
        }
        highlightBar(-1); // Restaura a cor original das barras
    }
}
