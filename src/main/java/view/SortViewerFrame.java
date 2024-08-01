package main.java.view;

import main.java.controller.SortController;

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
    private JTextField sizeField;
    private JTextField pauseField;
    private JButton startButton;
    private JButton menuButton;

    /**
     * Construtor para inicializar a janela do visualizador de ordenação.
     * Configura o painel principal, os componentes de controle e adiciona ouvintes de eventos para os botões.
     */
    public SortViewerFrame() {
        setTitle("Sorting Algorithm Viewer");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new SortPanel();
        add(panel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.SOUTH);

        controlPanel.add(new JLabel("Número de Elementos:"));
        sizeField = new JTextField("10", 5);
        controlPanel.add(sizeField);

        algorithmBox = new JComboBox<>(new String[]{"Selection Sort (S)", "Bubble Sort (B)", "Insertion Sort (I)", "Quick Sort (Q)"});
        controlPanel.add(new JLabel("Algorítmos:"));
        controlPanel.add(algorithmBox);

        typeBox = new JComboBox<>(new String[]{"Numérico (N)", "Caracter (C)"});
        controlPanel.add(new JLabel("Tipo:"));
        controlPanel.add(typeBox);

        orderBox = new JComboBox<>(new String[]{"Crescente (ZA)", "Decrescente (AZ)"});
        controlPanel.add(new JLabel("Ordem:"));
        controlPanel.add(orderBox);

        pauseField = new JTextField("500", 5);
        controlPanel.add(new JLabel("Pausa (ms):"));
        controlPanel.add(pauseField);

        startButton = new JButton("Start");
        controlPanel.add(startButton);

        menuButton = new JButton("Menu");
        controlPanel.add(menuButton);

        startButton.addActionListener(e -> startSorting());

        menuButton.addActionListener(e -> showMenu());

        showMenu(); // Show menu on startup
    }

    /**
     * Exibe um menu para que o usuário insira os parâmetros desejados para o algoritmo de ordenação.
     * Atualiza os componentes de seleção de algoritmos, tipos e ordens com os valores fornecidos pelo usuário.
     */
    private void showMenu() {
        JPanel menuPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField algorithmField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField orderField = new JTextField();

        menuPanel.add(new JLabel("Algorítmo (S/B/I/Q):"));
        menuPanel.add(algorithmField);

        menuPanel.add(new JLabel("Tipo de Lista (N/C):"));
        menuPanel.add(typeField);

        menuPanel.add(new JLabel("Ordem (AZ/ZA):"));
        menuPanel.add(orderField);

        int result = JOptionPane.showConfirmDialog(this, menuPanel, "Menu", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String algorithm = algorithmField.getText().trim().toUpperCase();
            String type = typeField.getText().trim().toUpperCase();
            String order = orderField.getText().trim().toUpperCase();

            if (!validateInput(algorithm, type, order)) {
                JOptionPane.showMessageDialog(this, "Input inválido. Entre com os valores corretos.", "Error", JOptionPane.ERROR_MESSAGE);
                showMenu();
                return;
            }

            algorithmBox.setSelectedItem(getAlgorithmFullName(algorithm));
            typeBox.setSelectedItem(getTypeFullName(type));
            orderBox.setSelectedItem(getOrderFullName(order));
        }
    }

    /**
     * Valida os valores inseridos pelo usuário para o algoritmo, tipo e ordem.
     *
     * @param algorithm o valor do algoritmo inserido.
     * @param type o valor do tipo inserido.
     * @param order o valor da ordem inserido.
     * @return {@code true} se todos os valores forem válidos; {@code false} caso contrário.
     */
    private boolean validateInput(String algorithm, String type, String order) {
        return (algorithm.matches("[SBIQ]") && type.matches("[NC]") && order.matches("AZ|ZA"));
    }

    /**
     * Retorna o nome completo do algoritmo com base na abreviação fornecida.
     *
     * @param algorithm a abreviação do algoritmo.
     * @return o nome completo do algoritmo.
     */
    private String getAlgorithmFullName(String algorithm) {
        switch (algorithm) {
            case "S":
                return "Selection Sort (S)";
            case "B":
                return "Bubble Sort (B)";
            case "I":
                return "Insertion Sort (I)";
            case "Q":
                return "Quick Sort (Q)";
            default:
                return null;
        }
    }

    /**
     * Retorna o nome completo do tipo com base na abreviação fornecida.
     *
     * @param type a abreviação do tipo.
     * @return o nome completo do tipo.
     */
    private String getTypeFullName(String type) {
        switch (type) {
            case "N":
                return "Numérico (N)";
            case "C":
                return "Caracter (C)";
            default:
                return null;
        }
    }

    /**
     * Retorna o nome completo da ordem com base na abreviação fornecida.
     *
     * @param order a abreviação da ordem.
     * @return o nome completo da ordem.
     */
    private String getOrderFullName(String order) {
        switch (order) {
            case "AZ":
                return "Decrescente (AZ)";
            case "ZA":
                return "Crescente (ZA)";
            default:
                return null;
        }
    }

    /**
     * Inicia a ordenação com base nas configurações fornecidas pelo usuário e no tipo de dados selecionado.
     */
    private void startSorting() {
        int numElements = Integer.parseInt(sizeField.getText());
        int pauseDuration = Integer.parseInt(pauseField.getText());
        String algorithm = (String) algorithmBox.getSelectedItem();
        String type = (String) typeBox.getSelectedItem();
        String order = (String) orderBox.getSelectedItem();

        control = new SortController(panel, pauseDuration, order);

        if ("Numérico (N)".equals(type)) {
            if (numElements > 1000) {
                JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int[] array = generateRandomNumbers(numElements);
            panel.setIntArray(array);
            switch (Objects.requireNonNull(algorithm)) {
                case "Selection Sort (S)":
                    control.selectionSort(array);
                    break;
                case "Bubble Sort (B)":
                    control.bubbleSort(array);
                    break;
                case "Insertion Sort (I)":
                    control.insertionSort(array);
                    break;
                case "Quick Sort (Q)":
                    control.quickSort(array, 0, array.length - 1);
                    break;
            }
        } else {
            if (numElements > 1000) {
                JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            char[] array = generateRandomChars(numElements);
            panel.setCharArray(array);
            switch (algorithm) {
                case "Selection Sort (S)":
                    control.selectionSort(array);
                    break;
                case "Bubble Sort (B)":
                    control.bubbleSort(array);
                    break;
                case "Insertion Sort (I)":
                    control.insertionSort(array);
                    break;
                case "Quick Sort (Q)":
                    control.quickSort(array, 0, array.length - 1);
                    break;
            }
        }
    }

    /**
     * Gera um array de inteiros aleatórios com o número especificado de elementos.
     *
     * @param numElements o número de elementos no array.
     * @return o array de inteiros aleatórios.
     */
    private int[] generateRandomNumbers(int numElements) {
        int[] array = new int[numElements];
        for (int i = 0; i < numElements; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }

    /**
     * Gera um array de caracteres aleatórios com o número especificado de elementos.
     *
     * @param numElements o número de elementos no array.
     * @return o array de caracteres aleatórios.
     */
    private char[] generateRandomChars(int numElements) {
        char[] array = new char[numElements];
        for (int i = 0; i < numElements; i++) {
            array[i] = (char) (Math.random() * 26 + 'A');
        }
        return array;
    }
}
