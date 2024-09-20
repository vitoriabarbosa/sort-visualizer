package main.java;

import javax.swing.SwingUtilities;

import main.java.view.SortViewerFrame;
import main.java.utils.ArgumentProcessor;
import main.java.utils.ArgumentProcessor.ProcessedArgs;

/**
 * A classe {@code MainApp} é a classe principal que inicia a aplicação de visualização de algoritmos de ordenação.
 * Ela processa os argumentos da linha de comando e inicializa a interface gráfica.
 */
public class MainApp {
    public static void main(String[] args) {
        // processar argumentos usando a classe ArgumentProcessor
        ProcessedArgs processedArgs = ArgumentProcessor.processArguments(args);

        // inicializa a interface gráfica
        SwingUtilities.invokeLater(() ->
                new SortViewerFrame(processedArgs.algorithm, processedArgs.type, processedArgs.order,
                        processedArgs.valueListType, processedArgs.numElements,
                        processedArgs.manualValues, processedArgs.pauseDuration).setVisible(true)
        );
    }
}
