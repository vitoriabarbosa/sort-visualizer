package main.java;

import main.java.view.SortViewerFrame;

/**
 * A classe {@code Main} é a classe principal que inicia a aplicação de visualização de algoritmos de ordenação.
 * Ela cria uma instância da {@code SortViewerFrame} e a torna visível.
 */
public class MainApp {

    /**
     * O método principal que inicializa a aplicação.
     * Cria uma nova instância de {@code SortViewerFrame} e a torna visível.
     *
     * @param args os argumentos da linha de comando (não utilizados nesta classe).
     */
    public static void main(String[] args) {
        new SortViewerFrame().setVisible(true);
    }
}
