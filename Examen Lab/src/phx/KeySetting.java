package phx;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_H;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_K;
import static java.awt.event.KeyEvent.VK_L;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Clase que gestiona la configuración de teclas para el juego 2048.
 */
public class KeySetting extends KeyAdapter {

     /** Mapeo de códigos de teclas a métodos del tablero. */
    private static final HashMap<Integer, Method> keyMapping = new HashMap<>();

    /** Arreglo de códigos de teclas para las teclas de flechas y reinicio. */
    private static Integer[] KEYS = { VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT, VK_R };

    /** Arreglo de códigos de teclas para las teclas vi (h, j, k, l). */
    private static Integer[] VI_KEY = { VK_K, VK_J, VK_H, VK_L };

    /** Nombres de los métodos correspondientes a las teclas. */
    private static String[] methodName = { "up", "down", "left", "right", "initTiles" };

    /** Referencia al tablero del juego. */
    private static Board board;

    /** Instancia única de la clase KeySetting. */
    private static final KeySetting INSTANCE = new KeySetting();
    
    /**
     * Método que devuelve la instancia única de KeySetting y establece el tablero asociado.
     *
     * @param b Tablero del juego.
     * @return Instancia única de KeySetting.
     */
    public static KeySetting getkeySetting(Board b) {
        board = b;
        return INSTANCE;
    }

    /**
     * Aplicacion del patron Singleton
     */
    private KeySetting() {
        initKey(KEYS);
        initKey(VI_KEY);
    }

    /**
     * Inicializa los códigos de teclas en el arreglo kcs.
     *
     * @param kcs Arreglo de códigos de teclas.
     */
    void initKey(Integer[] kcs) {
        for (int i = 0; i < kcs.length; i++) {
            try {
                keyMapping.put(kcs[i], Board.class.getMethod(methodName[i]));
            } catch (NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Utiliza reflexión para invocar el método asociado a la tecla presionada.
     */
    @Override
    public void keyPressed(KeyEvent k) {
        super.keyPressed(k);
        Method action = keyMapping.get(k.getKeyCode());
        if (action == null) {
            System.gc();
            return;
        }
        try {
            action.invoke(board);
            board.repaint();
        } catch (InvocationTargetException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (!board.canMove()) {  
            board.host.lose(board.maxTileValue, board.numberOfMoves, board.alreadyWrite);
            board.alreadyWrite = true;
        }

    }

}
