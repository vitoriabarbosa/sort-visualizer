package main.java.model;

import main.java.view.SortPanel;

/**
 * A classe {@code SelectionSort} implementa o algoritmo de ordenação por seleção (Selection Sort).
 * Extende a classe {@code Sorting} e fornece a implementação específica do método de ordenação para arrays de inteiros e caracteres.
 */
public class SelectionSort extends Sorting {

    /**
     * Construtor para inicializar a classe {@code SelectionSort} com os parâmetros necessários.
     *
     * @param panel o painel onde a ordenação será visualizada.
     * @param ascending indica se a ordenação deve ser em ordem crescente ({@code true}) ou decrescente ({@code false}).
     * @param pauseDuration a duração da pausa entre as iterações da ordenação em milissegundos.
     */
    public SelectionSort(SortPanel panel, boolean ascending, int pauseDuration) {
        super(panel, ascending, pauseDuration);
    }

    /**
     * Ordena um array de inteiros usando o algoritmo de ordenação por seleção (Selection Sort).
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
     * Ordena um array de caracteres usando o algoritmo de ordenação por seleção (Selection Sort).
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
     * Ordena um array de inteiros usando o algoritmo de ordenação por seleção (Selection Sort).
     *
     * @param array o array de inteiros a ser ordenado.
     */
    @Override
    protected void executeSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                highlightBar(j); // Destaca o elemento sendo comparado
                if (ascending ? array[j] < array[minIndex] : array[j] > array[minIndex]) {
                    minIndex = j;
                }
                sleep();
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
            panel.setIntArray(array);
            highlightBar(i); // Destaca o elemento trocado
            sleep();
        }
        highlightBar(-1); // Restaura a cor original das barras
    }

    /**
     * Ordena um array de caracteres usando o algoritmo de ordenação por seleção (Selection Sort).
     *
     * @param array o array de caracteres a ser ordenado.
     */
    @Override
    protected void executeSort(char[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                highlightBar(j); // Destaca o elemento sendo comparado
                if (ascending ? array[j] < array[minIndex] : array[j] > array[minIndex]) {
                    minIndex = j;
                }
                sleep();
            }
            char temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
            panel.setCharArray(array);
            highlightBar(i); // Destaca o elemento trocado
            sleep();
        }
        highlightBar(-1); // Restaura a cor original das barras
    }
}
