package main.java.model;

import main.java.view.SortPanel;

/**
 * A classe {@code Sorting} é uma classe abstrata que fornece a estrutura básica para algoritmos de ordenação.
 * Contém métodos e campos comuns a todos os algoritmos de ordenação, como a referência ao painel de visualização,
 * a configuração da ordem de ordenação e a duração da pausa entre as iterações.
 */
public abstract class Sorting {
    protected SortPanel panel;
    protected boolean ascending;
    protected int pauseDuration;

    /**
     * Construtor para inicializar a classe {@code Sorting} com os parâmetros necessários.
     *
     * @param panel o painel onde a ordenação será visualizada.
     * @param ascending indica se a ordenação deve ser em ordem crescente ({@code true}) ou decrescente ({@code false}).
     * @param pauseDuration a duração da pausa entre as iterações da ordenação em milissegundos.
     */
    public Sorting(SortPanel panel, boolean ascending, int pauseDuration) {
        this.panel = panel;
        this.ascending = ascending;
        this.pauseDuration = pauseDuration;
    }

    /**
     * Método para ordenar um array de inteiros e medir o tempo de execução.
     *
     * @param array o array de inteiros a ser ordenado.
     * @return o tempo de execução em milissegundos.
     */
    public long sort(int[] array) {
        long startTime = System.currentTimeMillis();
        executeSort(array); // Método específico de cada algoritmo.
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Método para ordenar um array de caracteres e medir o tempo de execução.
     *
     * @param array o array de caracteres a ser ordenado.
     * @return o tempo de execução em milissegundos.
     */
    public long sort(char[] array) {
        long startTime = System.currentTimeMillis();
        executeSort(array); // Método específico de cada algoritmo.
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Método abstrato específico de cada algoritmo para ordenar um array de inteiros.
     * As subclasses devem implementar esse método.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    protected abstract void executeSort(int[] array);

    /**
     * Método abstrato específico de cada algoritmo para ordenar um array de caracteres.
     * As subclasses devem implementar esse método.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    protected abstract void executeSort(char[] array);

    /**
     * Destaca a barra correspondente ao índice especificado no painel de visualização.
     *
     * @param index o índice da barra a ser destacada.
     */
    protected void highlightBar(int index) {
        panel.setCurrentBar(index);
    }

    /**
     * Pausa a execução do algoritmo por um período de tempo especificado.
     *
     * A pausa é controlada pelo campo {@code pauseDuration} e é implementada usando o método {@code Thread.sleep()}.
     */
    protected void sleep() {
        try {
            Thread.sleep(pauseDuration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
