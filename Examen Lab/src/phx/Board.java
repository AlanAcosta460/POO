package phx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Clase que representa el tablero del juego 2048.
 */
public class Board extends JPanel {
    private static final long serialVersionUID = -1790261785521495991L;
    
    /* Número de filas y columnas en el tablero */
    public static final int ROW = 4;

    /* Arreglo utilizado para iterar */
    public static final int[] _0123 = { 0, 1, 2, 3 };

    /** Instancia del juego GUI2048 al que pertenece el tablero. */
    final GUI2048 host;

    /** Arreglo de casillas en el tablero. */
    private Tile[] tiles;

    /** Valor final del juego. */
    public static Value GOAL = Value._2048;

    /** Número de movimientos realizados. */
    public int numberOfMoves = 0;

    /** Valor de la casilla mas alta. */
    public int maxTileValue = 0;

     /** Indica si ya se ha guardo la puntuacion en el archivo. */
    public boolean alreadyWrite = false;

    /**
     * Constructor de la clase Board.
     *
     * @param f Instancia de GUI2048 que contiene este tablero.
     */
    public Board(GUI2048 f) {
        host = f;
        setFocusable(true);
        initTiles();
    }

    /**
     * Inicializa el juego y se utiliza para comenzar una nueva partida.
     */
    public void initTiles() {
        tiles = new Tile[ROW * ROW];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = Tile.ZERO;
        }
        addTile();
        addTile();
        host.statusBar.setText("");

        numberOfMoves = 0;
        maxTileValue = 0;
        alreadyWrite = false;
    }

    /**
     * Mueve todas las casillas hacia el lado izquierdo.
     */
    public void left() {
        boolean needAddTile = false;
        for (int i : _0123) {
            Tile[] origin = getLine(i);
            Tile[] afterMove = moveLine(origin);
            Tile[] merged = mergeLine(afterMove);
            setLine(i, merged);
            if (!needAddTile && !Arrays.equals(origin, merged)) {
                needAddTile = true;
            }
        }

        if (needAddTile) {
            addTile();
        }

        numberOfMoves++;
        updateMaxTileValue();
    }

    /**
     * Mueve las casillas hacia el lado derecho.
     */
    public void right() {
        tiles = rotate(180);
        left();
        tiles = rotate(180);
    }

    /**
     * Mueve las casillas hacia arriba.
     */
    public void up() {
        tiles = rotate(270);
        left();
        tiles = rotate(90);
    }

    /**
     * Mueve las casillas hacia abajo.
     */
    public void down() {
        tiles = rotate(90);
        left();
        tiles = rotate(270);
    }

    /**
     * Obtiene la casilla en la posición (x, y) del tablero.
     *
     * @param x Coordenada x de la casilla.
     * @param y Coordenada y de la casilla.
     * @return Casilla en la posición (x, y).
     */
    private Tile tileAt(int x, int y) {
        return tiles[x + y * ROW];
    }

    /**
     * Genera una nueva casilla en el espacio disponible.
     */
    private void addTile() {
        List<Integer> list = availableIndex();
        int idx = list.get((int) (Math.random() * list.size()));
        tiles[idx] = Tile.randomTile();
    }

    /**
     * Actualiza el registro de la casilla mas alta
     */
    private void updateMaxTileValue() {
        for (Tile tile : tiles)
            if (tile.value().score() > maxTileValue)
                maxTileValue = tile.value().score();
    }

    /**
     * Consulta el  arreglo de casillas y obtiene la lista de índices de casillas vacías.
     *
     * @return Lista de índices de casillas vacías.
     */
    private List<Integer> availableIndex() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].empty())
                list.add(i);
        }
        return list;
    }

    /**
     * Indica si el tablero está lleno.
     *
     * @return true si el tablero está lleno, false si hay casillas vacías.
     */
    private boolean isFull() {
        return availableIndex().size() == 0;
    }

    /**
     * Indica si es posible realizar movimientos en el tablero.
     *
     * @return true si se pueden realizar movimientos, false si no hay movimientos posibles.
     */
    boolean canMove() {
        if (!isFull()) {
            return true;
        }
        for (int x : _0123) {
            for (int y : _0123) {
                Tile t = tileAt(x, y);
                if ((x < ROW - 1 && t.equals(tileAt(x + 1, y)))
                        || (y < ROW - 1 && t.equals(tileAt(x, y + 1)))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Rota las casillas en el tablero en el ángulo especificado en sentido horario.
     *
     * @param dgr Grados de rotación (90, 180 o 270).
     * @return Arreglo de casillas después de la rotación.
     */
    private Tile[] rotate(int dgr) {
        Tile[] newTiles = new Tile[ROW * ROW];
        int offsetX = 3, offsetY = 3;
        if (dgr == 90) {
            offsetY = 0;
        } else if (dgr == 180) {
        } else if (dgr == 270) {
            offsetX = 0;
        } else {
            throw new IllegalArgumentException(
                    "Only can rotate 90, 180, 270 degree");
        }
        double radians = Math.toRadians(dgr);
        int cos = (int) Math.cos(radians);
        int sin = (int) Math.sin(radians);
        for (int x : _0123) {
            for (int y : _0123) {
                int newX = (x * cos) - (y * sin) + offsetX;
                int newY = (x * sin) + (y * cos) + offsetY;
                newTiles[(newX) + (newY) * ROW] = tileAt(x, y);
            }
        }
        return newTiles;
    }

    /**
     * Mueve la línea de casillas no vacías hacia la izquierda.
     *
     * @param oldLine Arreglo de casillas de la línea original.
     * @return Arreglo de casillas después de mover la línea.
     */
    private Tile[] moveLine(Tile[] oldLine) {
        LinkedList<Tile> l = new LinkedList<>();
        for (int i : _0123) {
            if (!oldLine[i].empty())
                l.addLast(oldLine[i]);
        }
        if (l.size() == 0) {
            return oldLine;
        } else {
            Tile[] newLine = new Tile[4];
            ensureSize(l, 4);
            for (int i : _0123) {
                newLine[i] = l.removeFirst();
            }
            return newLine;
        }
    }

    /**
     * Fusiona la línea de casillas originales y devuelve una nueva línea.
     *
     * @param oldLine Arreglo de casillas de la línea original.
     * @return Arreglo de casillas después de la fusión.
     */
    private Tile[] mergeLine(Tile[] oldLine) {
        LinkedList<Tile> list = new LinkedList<Tile>();
        for (int i = 0; i < ROW; i++) {
            if (i < ROW - 1 && !oldLine[i].empty()
                    && oldLine[i].equals(oldLine[i + 1])) {
                // can be merge, double the val
                Tile merged = oldLine[i].getDouble();
                i++; // skip next one!
                list.add(merged);
                if (merged.value() == GOAL) {
                    // reach goal, show message
                    host.win(maxTileValue, numberOfMoves, alreadyWrite);
                    alreadyWrite = true;
                }
            } else {
                list.add(oldLine[i]);
            }
        }
        ensureSize(list, 4);
        return list.toArray(new Tile[4]);
    }

    /**
     * Añade la casilla vacía a la lista de casillas hasta que tenga el tamaño especificado.
     *
     * @param l Lista de casillas.
     * @param s Tamaño deseado.
     */
    private static void ensureSize(List<Tile> l, int s) {
        while (l.size() < s) {
            l.add(Tile.ZERO);
        }
    }

    /**
     * Obtiene la i-ésima línea del tablero.
     *
     * @param idx Índice de la línea.
     * @return Arreglo de casillas de la i-ésima línea.
     */
    private Tile[] getLine(int idx) {
        Tile[] result = new Tile[4];
        for (int i : _0123) {
            result[i] = tileAt(i, idx);
        }
        return result;
    }

    /**
     * Establece la i-ésima línea del tablero con el arreglo especificado.
     *
     * @param idx Índice de la línea.
     * @param re  Arreglo de casillas para reemplazar la línea actual.
     */
    private void setLine(int idx, Tile[] re) {
        for (int i : _0123) {
            tiles[i + idx * ROW] = re[i];
        }
    }

    /** Color de fondo del tablero. */
    private static final Color BG_COLOR = new Color(0xbbada0);

    /** Fuente utilizada para el texto en el tablero. */
    private static final Font STR_FONT = new Font(Font.SANS_SERIF,
                                                    Font.BOLD, 17);

    /**
     * Método de pintado para dibujar el tablero y las casillas.
     *
     * @param g Objeto Graphics utilizado para dibujar.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.setFont(STR_FONT);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int y : _0123) {
            for (int x : _0123) {
                drawTile(g, tiles[x + y * ROW], x, y);
            }
        }
    }

    /** Tamaño de un lado del cuadrado de la casilla. */
    private static final int SIDE = 64;

    /** Margen entre las casillas. */
    private static final int MARGIN = 16;

    /**
     * Dibuja una casilla con un número y color específicos en las coordenadas (x, y).
     *
     * @param g    Objeto Graphics utilizado para dibujar.
     * @param tile Casilla a dibujar.
     * @param x    Coordenada x.
     * @param y    Coordenada y.
     */
    private void drawTile(Graphics g, Tile tile, int x, int y) {
        Value val = tile.value();
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        g.setColor(val.color());
        g.fillRect(xOffset, yOffset, SIDE, SIDE);
        g.setColor(val.fontColor());
        if (val.score() != 0)
            g.drawString(tile.toString(), xOffset
                    + (SIDE >> 1) - MARGIN, yOffset + (SIDE >> 1));
    }

    /**
     * Calcula el desplazamiento de las coordenadas para la posición especificada.
     *
     * @param arg Coordenada.
     * @return Desplazamiento calculado.
     */
    private static int offsetCoors(int arg) {
        return arg * (MARGIN + SIDE) + MARGIN;
    }
}
