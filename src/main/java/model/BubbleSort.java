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
     * A ordenação é realizada em um novo thread para permitir a visualização em tempo real.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    @Override
    public void sort(int[] array) {
        new Thread(() -> {
            boolean swapped;
            for (int i = 0; i < array.length - 1; i++) {
                swapped = false;
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (ascending ? array[j] > array[j + 1] : array[j] < array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swapped = true;
                        panel.setIntArray(array);
                        highlightBar(j);
                        sleep();
                    }
                }
                if (!swapped) break;
            }
            highlightBar(-1); // Reset highlight when done
        }).start();
    }

    /**
     * Ordena um array de caracteres usando o algoritmo de ordenação por bolha.
     * A ordenação é realizada em um novo thread para permitir a visualização em tempo real.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    @Override
    public void sort(char[] array) {
        new Thread(() -> {
            boolean swapped;
            for (int i = 0; i < array.length - 1; i++) {
                swapped = false;
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (ascending ? array[j] > array[j + 1] : array[j] < array[j + 1]) {
                        char temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swapped = true;
                        panel.setCharArray(array);
                        highlightBar(j);
                        sleep();
                    }
                }
                if (!swapped) break;
            }
            highlightBar(-1); // Reset highlight when done
        }).start();
    }
}
