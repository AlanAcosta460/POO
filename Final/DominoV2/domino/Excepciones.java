package domino;

/**
 * Excepción lanzada cuando se recibe una respuesta inválida.
 */
class RespuestaInvalidaException extends Exception {
    /**
     * Constructor de la excepción.
     * @param mensaje Mensaje que describe la naturaleza de la respuesta inválida.
     */
    public RespuestaInvalidaException(String mensaje) {
        super(mensaje);
    }
}

/**
 * Excepción lanzada cuando se recibe un índice de ficha inválido.
 */
class IndiceFichaInvalidoException extends Exception {
    /**
     * Constructor de la excepción.
     * @param mensaje Mensaje que describe la naturaleza del índice de ficha inválido.
     */
    public IndiceFichaInvalidoException(String mensaje) {
        super(mensaje);
    }
}

/**
 * Excepción lanzada cuando se recibe una orientación inválida.
 */
class OrientacionInvalidaException extends Exception {
    /**
     * Constructor de la excepción.
     * @param mensaje Mensaje que describe la naturaleza de la orientación inválida.
     */
    public OrientacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
