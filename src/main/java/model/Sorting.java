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
     * Método abstrato para ordenar um array de inteiros.
     *
     * @param array o array de inteiros a ser ordenado.
     */
    public abstract void sort(int[] array);

    /**
     * Método abstrato para ordenar um array de caracteres.
     *
     * @param array o array de caracteres a ser ordenado.
     */
    public abstract void sort(char[] array);

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
