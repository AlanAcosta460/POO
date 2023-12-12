package domino;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musica {
    private static Clip musica = null;
    
    public void reiniciarMusica() {
        try {
            if (musica != null)
                musica.stop();

            musica = AudioSystem.getClip();
            musica.open(AudioSystem.getAudioInputStream(Controlador.class.getResource("/recursos/MoonlightSonata.wav")));
            musica.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (Exception e) {}
    }

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
