package main.java.view;

import main.java.controller.SortController;
import main.java.utils.ButtonUtil;
import main.java.utils.DataGenerator;
import main.java.utils.InputValidator;
import main.java.utils.SortHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * A classe {@code SortViewerFrame} é uma janela principal da aplicação que permite ao usuário visualizar e controlar
 * a execução de algoritmos de ordenação. Ela fornece uma interface gráfica para selecionar algoritmos, tipos de dados,
 * ordem de ordenação, e outros parâmetros, e para iniciar a ordenação.
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
        controlPanel.add(new JLabel("Algorítmos:"));
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
        manualInputField.setEnabled("Manual (M)".equals(valueList)); // habilita se for "Manual (M)"
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

            if ("Numérico (N)".equals(type)) {
                if (numElements > 100) {
                    JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int[] array;
                if ("Manual (M)".equals(valueList)) {
                    array = InputValidator.manualInputIntArray(manualInputField.getText());
                } else {
                    array = DataGenerator.randomNumbers(numElements);
                }
                sortHandler.sort(algorithm, array);
            } else {
                if (numElements > 100) {
                    JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                char[] array;
                if ("Manual (M)".equals(valueList)) {
                    array = InputValidator.manualInputCharArray(manualInputField.getText());
                } else {
                    array = DataGenerator.randomChars(numElements);
                }
                sortHandler.sort(algorithm, array);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
}
