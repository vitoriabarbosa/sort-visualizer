package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A classe {@code SortPanel} é um painel personalizado para exibir visualmente a ordenação de arrays de inteiros ou caracteres.
 * Ela usa uma imagem em buffer para desenhar barras representando os valores dos arrays.
 */
public class SortPanel extends JPanel {
    private int[] intArray;
    private char[] charArray;
    private boolean isCharArray;
    private BufferedImage buffer;
    private final int barSpacing = 5;
    private int currentBar = -1;

    /**
     * Define o array de inteiros a ser exibido e atualiza o buffer de imagem.
     *
     * @param array o array de inteiros a ser exibido.
     */
    public void setIntArray(int[] array) {
        this.intArray = array;
        this.isCharArray = false;
        updateBuffer();
    }

    /**
     * Define o array de caracteres a ser exibido e atualiza o buffer de imagem.
     *
     * @param array o array de caracteres a ser exibido.
     */
    public void setCharArray(char[] array) {
        this.charArray = array;
        this.isCharArray = true;
        updateBuffer();
    }

    /**
     * Define o índice da barra atual a ser destacada e atualiza o buffer de imagem.
     *
     * @param index o índice da barra a ser destacada.
     */
    public void setCurrentBar(int index) {
        this.currentBar = index;
        updateBuffer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (buffer != null) {
            g.drawImage(buffer, 0, 0, this);
        }
    }

    /**
     * Atualiza o buffer de imagem para refletir o estado atual do array.
     */
    private void updateBuffer() {
        int width = getWidth();
        int height = getHeight();
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffer.createGraphics();
        if (isCharArray) {
            if (charArray != null) {
                drawArray(g2d, charArray);
            }
        } else {
            if (intArray != null) {
                drawArray(g2d, intArray);
            }
        }
        g2d.dispose();
        repaint();
    }

    /**
     * Desenha um array de inteiros no painel, onde cada valor é representado por uma barra.
     *
     * @param g o objeto {@code Graphics} usado para desenhar.
     * @param array o array de inteiros a ser desenhado.
     */
    private void drawArray(Graphics g, int[] array) {
        int width = getWidth();
        int height = getHeight();
        int barWidth = (width - (array.length - 1) * barSpacing) / array.length;
        int maxHeight = getMaxValue(array);

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) (((double) array[i] / maxHeight) * height);
            int x = i * (barWidth + barSpacing);
            int y = height - barHeight;

            if (i == currentBar) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.decode("#CCCCCC"));
            }
            g.fillRect(x, y, barWidth, barHeight);

            g.setColor(Color.BLACK);
            g.fillRect(x, y, barWidth, 30);

            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(array[i]), x + (barWidth / 2) - 5, y + 15);
        }
    }

    /**
     * Desenha um array de caracteres no painel, onde cada caractere é representado por uma barra.
     *
     * @param g o objeto {@code Graphics} usado para desenhar.
     * @param array o array de caracteres a ser desenhado.
     */
    private void drawArray(Graphics g, char[] array) {
        int width = getWidth();
        int height = getHeight();
        int barWidth = (width - (array.length - 1) * barSpacing) / array.length;
        int maxHeight = getMaxValue(array);

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) (((double) (array[i] - 'A') / maxHeight) * height);
            int x = i * (barWidth + barSpacing);
            int y = height - barHeight;

            if (i == currentBar) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.decode("#CCCCCC"));
            }
            g.fillRect(x, y, barWidth, barHeight);

            g.setColor(Color.BLACK);
            g.fillRect(x, y, barWidth, 30);

            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(array[i]), x + (barWidth / 2) - 5, y + 15);
        }
    }

    /**
     * Retorna o valor máximo em um array de inteiros.
     *
     * @param array o array de inteiros.
     * @return o valor máximo no array.
     */
    private int getMaxValue(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Retorna o valor máximo em um array de caracteres.
     *
     * @param array o array de caracteres.
     * @return o valor máximo no array ajustado pela constante 'A'.
     */
    private int getMaxValue(char[] array) {
        int max = Integer.MIN_VALUE;
        for (char value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max - 'A';
    }
}
