package main.java.model;

import main.java.view.SortPanel;

/**
 * A classe {@code InsertionSort} implementa o algoritmo de ordenação por inserção (Insertion Sort).
 * Extende a classe {@code Sorting} e fornece a implementação específica do método de ordenação para arrays de inteiros e caracteres.
 */
public class InsertionSort extends Sorting {

    /**
     * Construtor para inicializar a classe {@code InsertionSort} com os parâmetros necessários.
     *
     * @param panel o painel onde a ordenação será visualizada.
     * @param ascending indica se a ordenação deve ser em ordem crescente ({@code true}) ou decrescente ({@code false}).
     * @param pauseDuration a duração da pausa entre as iterações da ordenação em milissegundos.
     */
    public InsertionSort(SortPanel panel, boolean ascending, int pauseDuration) {
        super(panel, ascending, pauseDuration);
    }

    /**
     * Ordena um array de inteiros usando o algoritmo de ordenação por inserção.
     *
     * @param array o array de inteiros a ser ordenado.
     * @return o tempo de execução da ordenação em milissegundos.
     */
    @Override
    public long sort(int[] array) {
        long startTime = System.currentTimeMillis();
        executeSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Ordena um array de caracteres usando o algoritmo de ordenação por inserção.
     *
     * @param array o array de caracteres a ser ordenado.
     * @return o tempo de execução da ordenação em milissegundos.
     */
    @Override
    public long sort(char[] array) {
        long startTime = System.currentTimeMillis();
        executeSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Executa a ordenação por inserção em um array de inteiros.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    @Override
    protected void executeSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && (ascending ? array[j] > key : array[j] < key)) {
                array[j + 1] = array[j];
                j--;
                panel.setIntArray(array);
                highlightBar(j + 1); // Destaca a barra atual
                sleep();
            }
            array[j + 1] = key;
            panel.setIntArray(array);
            highlightBar(i);
            sleep();
        }
        highlightBar(-1); // Restaura a cor original das barras
    }

    /**
     * Executa a ordenação por inserção em um array de caracteres.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    @Override
    protected void executeSort(char[] array) {
        for (int i = 1; i < array.length; i++) {
            char key = array[i];
            int j = i - 1;
            while (j >= 0 && (ascending ? array[j] > key : array[j] < key)) {
                array[j + 1] = array[j];
                j--;
                panel.setCharArray(array);
                highlightBar(j + 1);  // Destaca a barra atual
                sleep();
            }
            array[j + 1] = key;
            panel.setCharArray(array);
            highlightBar(i);
            sleep();
        }
        highlightBar(-1); // Restaura a cor original das barras
    }
}
