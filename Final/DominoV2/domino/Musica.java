package domino;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Esta clase gestiona la reproducción de la música del juego de domino.
 */
public class Musica {
    private static Clip musica = null;
    
    /**
     * Reinicia la música del juego.
     */
    public void reiniciarMusica() {
        try {
            if (musica != null)
                musica.stop();

            musica = AudioSystem.getClip();
            musica.open(AudioSystem.getAudioInputStream(Controlador.class.getResource("/recursos/MoonlightSonata.wav")));
            musica.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (Exception e) {}
    }

    /**
     * Reproduce la música de victoria al finalizar el juego.
     */
    public void musicaFinal() {
        try {
            if (musica != null)
                musica.stop();
            
            musica = AudioSystem.getClip();
            musica.open(AudioSystem.getAudioInputStream(Controlador.class.getResource("/recursos/Victoria.wav")));
            musica.start();
        } catch (Exception e) {} 
    }
}
