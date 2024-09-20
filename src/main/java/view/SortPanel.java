package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A classe {@code SortPanel} é um painel personalizado para exibir visualmente a ordenação de arrays de inteiros ou caracteres.
 * Ela usa uma imagem em buffer para desenhar barras representando os valores dos arrays.
 */
public class SortPanel extends JPanel {
    private Object array;
    private boolean isCharArray;
    private BufferedImage buffer;
    private int currentBar = -1;

    private final int TOP_PADDING = 50;    // superior
    private final int LEFT_PADDING = 10;   // esquerdo

    /**
     * Define o array de inteiros a ser exibido e atualiza o buffer de imagem.
     *
     * @param array o array de inteiros a ser exibido.
     */
    public void setIntArray(int[] array) {
        this.array = array;
        this.isCharArray = false;
        updateBuffer();
    }

    /**
     * Define o array de caracteres a ser exibido e atualiza o buffer de imagem.
     *
     * @param array o array de caracteres a ser exibido.
     */
    public void setCharArray(char[] array) {
        this.array = array;
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
        if (buffer != null) g.drawImage(buffer, 0, 0, this);
    }

    /**
     * Atualiza o buffer de imagem para refletir o estado atual do array.
     */
    private void updateBuffer() {
        int width = getWidth();
        int height = getHeight();
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffer.createGraphics();

        // Calcula o tamanho do painel
        int rightPadding = 10; // direito
        int usableWidth = width - LEFT_PADDING - rightPadding;
        int bottomPadding = 50; // inferior
        int usableHeight = height - TOP_PADDING - bottomPadding;

        if (array != null) drawArray(g2d, usableWidth, usableHeight);
        g2d.dispose();
        repaint();
    }

    /**
     * Desenha o array no painel, onde cada valor é representado por uma barra.
     *
     * @param g o objeto {@code Graphics} usado para desenhar.
     * @param usableWidth a largura útil do painel.
     * @param usableHeight a altura útil do painel.
     */
    private void drawArray(Graphics g, int usableWidth, int usableHeight) {
        int length = isCharArray ? ((char[]) array).length : ((int[]) array).length;
        int barSpacing = 3;
        int barWidth = (usableWidth - (length - 1) * barSpacing) / length;

        // Define uma altura mínima para as barras
        int minBarHeight = 5;

        // Calcula o valor máximo e mínimo no array
        int maxValue = isCharArray ? 26 : getMaxValue((int[]) array);
        int minValue = isCharArray ? 0 : getMinValue((int[]) array);

        // Calcula o ponto central (zero) onde as barras negativas começam a crescer para baixo
        int zeroY = TOP_PADDING + usableHeight * maxValue / (maxValue - minValue);

        // Desenha a linha preta abaixo das barras
        g.setColor(Color.BLACK);
        g.drawLine(LEFT_PADDING, zeroY, LEFT_PADDING + usableWidth, zeroY);

        for (int i = 0; i < length; i++) {
            int value = isCharArray ? ((char[]) array)[i] - 'A' + 1 : ((int[]) array)[i];

            if (!isCharArray) {
                if (value == 0) {
                    int x = LEFT_PADDING + i * (barWidth + barSpacing);
                    g.setColor(Color.WHITE);
                    g.drawString("0", x + (barWidth / 2) - 5, zeroY - 5); // Ajusta a posição do texto "0"
                    continue;
                }
            }

            int barHeight = (int) (((double) Math.abs(value) / maxValue) * ((double) usableHeight / 2));
            barHeight = Math.max(barHeight, minBarHeight);

            int x = LEFT_PADDING + i * (barWidth + barSpacing);
            int y;
            if (value >= 0) y = zeroY - barHeight;
            else y = zeroY;

            // Destaca a barra atual
            if (i == currentBar) g.setColor(Color.RED);
            else g.setColor(Color.decode("#CCCCCC"));
            g.fillRect(x, y, barWidth, barHeight);

            g.setColor(Color.BLACK);
            g.fillRect(x, y, barWidth, 10);

            // Ajusta a posição do texto para garantir que ele esteja visível
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(isCharArray ? String.valueOf(((char[]) array)[i])
                    : String.valueOf(value), x + (barWidth / 2) - 5, y - 5);
        }
    }

    /**
     * Retorna o valor mínimo em um array de inteiros.
     *
     * @param array o array de inteiros.
     * @return o valor mínimo no array.
     */
    private int getMinValue(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int value : array) {
            if (value < min) min = value;
        }
        return min;
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
            if (value > max) max = value;
        }
        return max;
    }
}
