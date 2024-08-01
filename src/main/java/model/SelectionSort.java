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
     * A ordenação é realizada em um novo thread para permitir a visualização em tempo real.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    @Override
    public void sort(int[] array) {
        new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    highlightBar(j);
                    if (ascending ? array[j] < array[minIndex] : array[j] > array[minIndex]) {
                        minIndex = j;
                    }
                    sleep();
                }
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                panel.setIntArray(array);
                highlightBar(i);
                sleep();
            }
            highlightBar(-1); // Reset highlight when done
        }).start();
    }

    /**
     * Ordena um array de caracteres usando o algoritmo de ordenação por seleção (Selection Sort).
     * A ordenação é realizada em um novo thread para permitir a visualização em tempo real.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    @Override
    public void sort(char[] array) {
        new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    highlightBar(j);
                    if (ascending ? array[j] < array[minIndex] : array[j] > array[minIndex]) {
                        minIndex = j;
                    }
                    sleep();
                }
                char temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                panel.setCharArray(array);
                highlightBar(i);
                sleep();
            }
            highlightBar(-1); // Reset highlight when done
        }).start();
    }
}
