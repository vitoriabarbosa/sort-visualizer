package main.java.view;

import main.java.controller.SortController;
import main.java.utils.ButtonUtil;
import main.java.utils.DataGenerator;
import main.java.utils.InputValidator;
import main.java.utils.SortHandler;

import javax.swing.*;
import java.awt.*;

/**
 * A classe {@code SortViewerFrame} é a janela principal da aplicação que permite ao usuário visualizar e controlar
 * a execução de algoritmos de ordenação. Fornece uma interface gráfica para selecionar algoritmos, tipos de dados,
 * ordem de ordenação e outros parâmetros, além de iniciar a ordenação.
 */
public class SortViewerFrame extends JFrame {
    private SortPanel panel;
    private SortController control;
    private JComboBox<String> algorithmBox;
    private JComboBox<String> typeBox;
    private JComboBox<String> orderBox;
    private JComboBox<String> valueListBox;
    private JTextField sizeField;
    private JTextField pauseField;
    private JTextField manualInputField;

    /**
     * Construtor para inicializar a janela do visualizador de ordenação.
     * Configura o painel principal, os componentes de controle e adiciona ouvintes de eventos para os botões.
     *
     * @param algorithm     O algoritmo de ordenação selecionado.
     * @param type          O tipo de dado (numérico ou caractere) a ser ordenado.
     * @param order         A ordem de ordenação (crescente ou decrescente).
     * @param valueList     O modo de geração da lista de valores (aleatório ou manual).
     * @param numElements   O número de elementos a serem ordenados.
     * @param manualValues  Os valores inseridos manualmente, caso selecionado.
     * @param pauseDuration A duração da pausa entre cada iteração do algoritmo de ordenação.
     */
    public SortViewerFrame(String algorithm, String type, String order, String valueList, int numElements, String manualValues, int pauseDuration) {
        setTitle("Sorting Algorithm Viewer");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        panel = new SortPanel();
        panel.setBackground(Color.DARK_GRAY);
        add(panel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(controlPanel, BorderLayout.SOUTH);

        controlPanel.add(new JLabel("Número de Elementos:"));
        sizeField = new JTextField(String.valueOf(numElements), 5);
        controlPanel.add(sizeField);

        algorithmBox = new JComboBox<>(new String[]{"Selection Sort (S)", "Bubble Sort (B)", "Insertion Sort (I)", "Quick Sort (Q)"});
        controlPanel.add(new JLabel("Algoritmos:"));
        controlPanel.add(algorithmBox);

        typeBox = new JComboBox<>(new String[]{"Numérico (N)", "Caractere (C)"});
        controlPanel.add(new JLabel("Tipo:"));
        controlPanel.add(typeBox);

        orderBox = new JComboBox<>(new String[]{"Crescente (AZ)", "Decrescente (ZA)"});
        controlPanel.add(new JLabel("Ordem:"));
        controlPanel.add(orderBox);

        valueListBox = new JComboBox<>(new String[]{"Aleatório (R)", "Manual (M)"});
        controlPanel.add(new JLabel("Tipo Valor da Lista:"));
        controlPanel.add(valueListBox);

        manualInputField = new JTextField(manualValues, 20);
        manualInputField.setEnabled("Manual (M)".equals(valueList)); // Habilita o input se for "Manual (M)"
        controlPanel.add(new JLabel("Valores:"));
        controlPanel.add(manualInputField);

        pauseField = new JTextField(String.valueOf(pauseDuration), 5);
        controlPanel.add(new JLabel("Pausa (ms):"));
        controlPanel.add(pauseField);

        algorithmBox.setSelectedItem(InputValidator.getAlgorithmFullName(algorithm));
        typeBox.setSelectedItem(InputValidator.getTypeFullName(type));
        orderBox.setSelectedItem(InputValidator.getOrderFullName(order.toUpperCase()));
        valueListBox.setSelectedItem(InputValidator.getValueListFullName(valueList));

        valueListBox.addActionListener(e -> manualInputField.setEnabled("Manual (M)".equals(valueListBox.getSelectedItem())));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonUtil startButton = new ButtonUtil("Start", e -> startSorting());

        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(startButton);
        controlPanel.add(buttonPanel);
    }

    /**
     * Inicia a ordenação com os parâmetros atualmente selecionados na interface gráfica.
     * Esse método é chamado quando o botão "Start" é pressionado.
     * <p>
     * Ele extrai os parâmetros selecionados pelo usuário, como o algoritmo de ordenação, o tipo de dado, a ordem de
     * ordenação, e a quantidade de elementos a serem ordenados. Dependendo do tipo de dado selecionado (numérico ou
     * caractere) e se a lista será gerada manualmente ou aleatoriamente, a ordenação será iniciada.
     * <p>
     * Durante a execução, o tempo de processamento é medido, e o resultado final, incluindo o tempo total, é exibido.
     */
    private void startSorting() {
        try {
            int numElements = Integer.parseInt(sizeField.getText());
            int pauseDuration = Integer.parseInt(pauseField.getText());
            String algorithm = (String) algorithmBox.getSelectedItem();
            String type = (String) typeBox.getSelectedItem();
            String order = (String) orderBox.getSelectedItem();
            String valueList = (String) valueListBox.getSelectedItem();

            control = new SortController(panel, pauseDuration, order);
            SortHandler sortHandler = new SortHandler(control, panel);

            // Define uma tarefa de ordenação que será executada em uma nova thread
            Runnable sortingTask = () -> {
                long startTime = System.currentTimeMillis();
                try {
                    if ("Numérico (N)".equals(type)) {
                        if (numElements > 100) {
                            JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int[] array;
                        if ("Manual (M)".equals(valueList)) {
                            array = InputValidator.manualInputIntArray(manualInputField.getText());
                        } else array = DataGenerator.randomNumbers(numElements);
                        sortHandler.sort(algorithm, array);
                    } else {
                        if (numElements > 100) {
                            JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        char[] array;
                        if ("Manual (M)".equals(valueList)) {
                            array = InputValidator.manualInputCharArray(manualInputField.getText());
                        } else array = DataGenerator.randomChars(numElements);
                        sortHandler.sort(algorithm, array);
                    }

                    long endTime = System.currentTimeMillis();
                    long runtime = endTime - startTime;

                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(SortViewerFrame.this,
                                "Tempo de execução: " + (runtime / 1000) + " s", "Tempo de Execução", JOptionPane.INFORMATION_MESSAGE);
                    });
                } catch (Exception e) {
                    e.printStackTrace(); // Exceção genérica para exibir a mensagem em casos de erros
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, "Erro durante a ordenação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    });
                }
            };
            new Thread(sortingTask).start(); // Executa a tarefa em uma nova thread separada para não bloquear a interface gráfica
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
}
