package me.uselessmnemonic.whistles.melody;

import org.bukkit.Instrument;
import org.bukkit.Sound;

/**
 * Defines a Melody which can be played independent of a Note Block
 */
public class Melody {

    private final long period;
    private final Note[] notes;
    private final Sound sound;

    /**
     * Creates a Melody
     * @param sound The sound to be used as an instrument
     * @param period The number of ticks in between each Note
     * @param notes A series of Notes to be played sequentially;
     *              null represents a silent note.
     */
    public Melody(Sound sound, long period, Note... notes) {
        this.period = period;
        this.notes = notes;
        this.sound = sound;
    }

    /**
     * Creates a Melody
     * @param instrument An instrument type
     * @param period The number of ticks in between each Note
     * @param notes A sereis of Notes to be played sequentially
     *              null represents a silent note.
     */
    public Melody(Instrument instrument, long period, Note... notes) {
        this.period = period;
        this.notes = notes;
        switch (instrument) {
            case PIANO: this.sound = Sound.BLOCK_NOTE_BLOCK_HARP; break;
            case BASS_DRUM: this.sound = Sound.BLOCK_NOTE_BLOCK_BASEDRUM; break;
            case SNARE_DRUM: this.sound = Sound.BLOCK_NOTE_BLOCK_SNARE; break;
            case STICKS: this.sound = Sound.BLOCK_NOTE_BLOCK_HAT; break;
            case BASS_GUITAR: this.sound = Sound.BLOCK_NOTE_BLOCK_GUITAR; break;
            case FLUTE: this.sound = Sound.BLOCK_NOTE_BLOCK_FLUTE; break;
            case BELL: this.sound = Sound.BLOCK_NOTE_BLOCK_BELL; break;
            case GUITAR: this.sound = Sound.BLOCK_NOTE_BLOCK_GUITAR; break;
            case CHIME: this.sound = Sound.BLOCK_NOTE_BLOCK_CHIME; break;
            case XYLOPHONE: this.sound = Sound.BLOCK_NOTE_BLOCK_XYLOPHONE; break;
            case IRON_XYLOPHONE: this.sound = Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE; break;
            case COW_BELL: this.sound = Sound.BLOCK_NOTE_BLOCK_COW_BELL; break;
            case DIDGERIDOO: this.sound = Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO; break;
            case BIT: this.sound = Sound.BLOCK_NOTE_BLOCK_BIT; break;
            case BANJO: this.sound = Sound.BLOCK_NOTE_BLOCK_BANJO; break;
            case PLING: this.sound = Sound.BLOCK_NOTE_BLOCK_PLING; break;
            default: this.sound = Sound.BLOCK_NOTE_BLOCK_HARP; break;
        }
    }

    /**
     * Gets the number of Notes in the Melody
     * @return The number of Notes in the Melody
     */
    public int size() {
        return notes.length;
    }

    /**
     * Gets the Sound used in the Melody
     * @return The Sound used in the Melody
     */
    public Sound getSound() {
        return sound;
    }

    /**
     * Gets the Note used at a given index
     * @param index The index into the list of Notes
     * @return A Note, which can be null
     * @throws ArrayIndexOutOfBoundsException if the index reaches outside
     *         of the list of Notes
     */
    public Note getNote(int index) {
        return notes[index];
    }

    /**
     * Gets the period in between each note in ticks
     * @return The period in between each note
     */
    public long getPeriod() {
        return period;
    }
}
