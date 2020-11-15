package me.uselessmnemonic.whistles.melody;

import org.bukkit.Instrument;
import org.bukkit.Sound;

public class Melody {

    private final long period;
    private final Note[] notes;
    private final Sound sound;

    public Melody(Sound sound, long period, Note... notes) {
        this.period = period;
        this.notes = notes;
        this.sound = sound;
    }

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

    public int size() {
        return notes.length;
    }

    public Sound getSound() {
        return sound;
    }

    public Note getNote(int index) {
        return notes[index];
    }

    public long getPeriod() {
        return period;
    }
}
