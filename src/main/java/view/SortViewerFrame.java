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
    private JTextField sizeField;
    private JTextField pauseField;

    /**
     * Construtor para inicializar a janela do visualizador de ordenação.
     * Configura o painel principal, os componentes de controle e adiciona ouvintes de eventos para os botões.
     */
    public SortViewerFrame() {
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
        sizeField = new JTextField("20", 5);    // número de elementos "padrão"
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

        pauseField = new JTextField("100", 5);  // velocidade "padrão"
        controlPanel.add(new JLabel("Pausa (ms):"));
        controlPanel.add(pauseField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonUtil startButton = new ButtonUtil("Start", e -> startSorting());

        buttonPanel.add(Box.createHorizontalStrut(40));
        buttonPanel.add(startButton);
        controlPanel.add(buttonPanel);
        showMenu();
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

            if (!InputValidator.validateInput(algorithm, type, order)) {
                JOptionPane.showMessageDialog(this, "Input inválido. Entre com os valores corretos.", "Error", JOptionPane.ERROR_MESSAGE);
                showMenu();
                return;
            }

            algorithmBox.setSelectedItem(InputValidator.getAlgorithmFullName(algorithm));
            typeBox.setSelectedItem(InputValidator.getTypeFullName(type));
            orderBox.setSelectedItem(InputValidator.getOrderFullName(order));
        }
    }

    /**
     * Inicia a ordenação com base nas configurações fornecidas pelo usuário e no tipo de dados selecionado.
     */
    private void startSorting() {
        try {
            int numElements = Integer.parseInt(sizeField.getText());
            int pauseDuration = Integer.parseInt(pauseField.getText());
            String algorithm = (String) algorithmBox.getSelectedItem();
            String type = (String) typeBox.getSelectedItem();
            String order = (String) orderBox.getSelectedItem();

            control = new SortController(panel, pauseDuration, order);
            SortHandler sortHandler = new SortHandler(control, panel);

            if ("Numérico (N)".equals(type)) {
                if (numElements > 100) {
                    JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int[] array = DataGenerator.randomNumbers(numElements);
                sortHandler.sort(algorithm, array);
            } else {
                if (numElements > 100) {
                    JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                char[] array = DataGenerator.randomChars(numElements);
                sortHandler.sort(algorithm, array);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
}
