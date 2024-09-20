package main.java.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A classe {@code ButtonCustom} é uma subclasse de {@code JButton} que define botões personalizados
 * para a interface gráfica, com cores, tamanhos e fontes específicas.
 */
public class ButtonUtil extends JButton {
    /**
     * Construtor da classe {@code ButtonCustom}. Cria um botão com o texto especificado e define
     * uma ação a ser executada quando o botão é clicado.
     *
     * @param text   O texto a ser exibido no botão.
     * @param action O {@code ActionListener} que define a ação ao clicar no botão.
     */
    public ButtonUtil(String text, ActionListener action) {
        this.setText(text);
        this.addActionListener(action);
        this.setPreferredSize(new Dimension(100, 40));
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setBackground(new Color(0, 105, 1, 255));
        this.setFocusPainted(false);
    }
}
