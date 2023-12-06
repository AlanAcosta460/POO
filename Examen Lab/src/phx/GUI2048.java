package phx;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Clase que representa la interfaz gráfica del juego 2048.
 */
public class GUI2048 extends JFrame {

    private static final long serialVersionUID = 1L;

    /** Etiqueta para mostrar mensajes en la barra de estado. */
    JLabel statusBar;

    /** Título de la ventana del juego. */
    private static final String TITLE = "2048 in Java";

    /** Mensaje de victoria. */
    public static final String WIN_MSG = "You already win, but you can continue";

    /** Mensaje de derrota. */
    public static final String LOSE_MSG = "You lose, press 'r' to try again!";

    /** Nombre del jugador. */
    public static String nickName;

    /**
     * Método principal que inicia el juego 2048.
     *
     * @param args Argumentos de la línea de comandos (opcionalmente, la meta del juego).
     */
    public static void main(String[] args) {

        GUI2048 game = new GUI2048();
        Board board = new Board(game);
        if (args.length != 0 && args[0].matches("[0-9]*")) {
            Board.GOAL = Value.of(Integer.parseInt(args[0]));
        }
        KeySetting kb = KeySetting.getkeySetting(board);
            board.addKeyListener(kb);
        game.add(board);
        
        game.setLocationRelativeTo(null);
        game.setVisible(true);

        nickName = JOptionPane.showInputDialog("Write your nickname", "Player");
    }

    /**
     * Constructor de la clase GUI2048.
     */
    public GUI2048() {
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(340, 400);
        setResizable(false);

        statusBar = new JLabel("");
        add(statusBar, BorderLayout.SOUTH);
    }

    /**
     * Método llamado cuando el jugador gana el juego.
     *
     * @param maxTileValue   Valor máximo alcanzado en una casilla.
     * @param numberOfMoves  Número total de movimientos realizados.
     * @param alreadyWrite   Indica si el mensaje ya ha sido mostrado.
     */
    void win(int maxTileValue, int numberOfMoves, boolean alreadyWrite) {
        statusBar.setText(WIN_MSG);
        if (!alreadyWrite)
            ScoresFile.write(nickName, maxTileValue, numberOfMoves);
    }

    /**
     * Método llamado cuando el jugador pierde el juego.
     *
     * @param maxTileValue   Valor máximo alcanzado en una casilla.
     * @param numberOfMoves  Número total de movimientos realizados.
     * @param alreadyWrite   Indica si el mensaje ya ha sido mostrado.
     */
    void lose(int maxTileValue, int numberOfMoves, boolean alreadyWrite) {
        JOptionPane.showMessageDialog(this, LOSE_MSG);  
        if (!alreadyWrite)
            ScoresFile.write(nickName, maxTileValue, numberOfMoves);
    }
}
