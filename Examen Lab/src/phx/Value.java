package phx;

import java.awt.Color;

/**
 * Esta enumeración representa el valor de las casillas en la cuadrícula del juego 2048.
 * Se utiliza para almacenar los números y colores correspondientes.
 * 
 * @author phoenix
 */
public enum Value {
    _0   (0,    0x776e65, 0xcdc0b4),
    _2   (2,    0x776e65, 0xeee4da),
    _4   (4,    0x776e65, 0xede0c8),
    _8   (8,    0xf9f6f2, 0xf2b179),
    _16  (16,   0xf9f6f2, 0xf59563),
    _32  (32,   0xf9f6f2, 0xf67c5f),
    _64  (64,   0xf9f6f2, 0xf65e3b),
    _128 (128,  0xf9f6f2, 0xedcf72),
    _256 (256,  0xf9f6f2, 0xedcc61),
    _512 (512,  0xf9f6f2, 0xedc850),
    _1024(1024, 0xf9f6f2, 0xedc53f),
    _2048(2048, 0xf9f6f2, 0xedc22e);

     /** Puntuación asociada al valor. */
    private final int score;

    /** Color de fondo de la casilla. */
    private final Color color;

    /** Color de la fuente de la casilla. */
    private final Color fontColor;

    /**
     * Constructor de la enumeración Value.
     *
     * @param n Número asociado al valor.
     * @param f Color de la fuente de la casilla.
     * @param c Color de fondo de la casilla.
     */
    Value(int n, int f, int c) {
        score = n;
        color = new Color(c);
        fontColor = new Color(f);
    }

    /**
     * Método de fábrica para obtener una instancia de Value.
     * Este método es un envoltorio de Enum.valueOf().
     *
     * @param num Número para obtener la instancia de Value.
     * @return Instancia de Value correspondiente al número.
     */
    static Value of(int num) {
        return Value.valueOf("_" + num);
    }

    /**
     * Obtiene el color de la fuente de la casilla.
     *
     * @return Color de la fuente de la casilla.
     */
    public Color fontColor() {
        return fontColor;
    }

    /**
     * Obtiene el color de fondo de la casilla.
     *
     * @return Color de fondo de la casilla.
     */
    public Color color() {
        return color;
    }

    /**
     * Obtiene la puntuación asociada al valor.
     *
     * @return Puntuación asociada al valor.
     */
    public int score() {
        return score;
    }
}
