package sound;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class SoundMain {


    public static void main(String[] args) throws MidiUnavailableException {


        Sequencer sequencer = MidiSystem.getSequencer();

        if (sequencer == null) {
            // Error -- sequencer device is not supported.
            // Inform user and return...
            System.out.println("sequencer device is not supported.");
        } else {
            // Acquire resources and make operational.
            System.out.println("sequencer device opened.");
            sequencer.open();
        }



    }
}
