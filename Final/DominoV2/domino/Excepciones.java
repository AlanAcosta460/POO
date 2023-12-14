package domino;

/**
 * Excepcion lanzada cuando se recibe una respuesta invalida.
 */
class RespuestaInvalidaException extends Exception {
    /**
     * Constructor de la excepcion.
     * @param mensaje Mensaje que describe la naturaleza de la respuesta invalida.
     */
    public RespuestaInvalidaException(String mensaje) {
        super(mensaje);
    }
}

/**
 * Excepcion lanzada cuando se recibe un indice de ficha invalido.
 */
class IndiceFichaInvalidoException extends Exception {
    /**
     * Constructor de la excepcion.
     * @param mensaje Mensaje que describe la naturaleza del indice de ficha invalido.
     */
    public IndiceFichaInvalidoException(String mensaje) {
        super(mensaje);
    }
}

/**
 * Excepcion lanzada cuando se recibe una orientacion invalida.
 */
class OrientacionInvalidaException extends Exception {
    /**
     * Constructor de la excepcion.
     * @param mensaje Mensaje que describe la naturaleza de la orientacion invalida.
     */
    public OrientacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
