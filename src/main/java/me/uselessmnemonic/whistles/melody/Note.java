package me.uselessmnemonic.whistles.melody;

public class Note {

    public static final Note FSh0 = new Note(0.5f);
    public static final Note G0 = Note.noteBlockUses(1);
    public static final Note GSh0 = Note.noteBlockUses(2);
    public static final Note A0 = Note.noteBlockUses(3);
    public static final Note ASh0 = Note.noteBlockUses(4);
    public static final Note B0 = Note.noteBlockUses(5);

    public static final Note C1 = Note.noteBlockUses(6);
    public static final Note CSh1 = Note.noteBlockUses(7);
    public static final Note D1 = Note.noteBlockUses(8);
    public static final Note DSh1 = Note.noteBlockUses(9);
    public static final Note E1 = Note.noteBlockUses(10);
    public static final Note F1 = Note.noteBlockUses(11);
    public static final Note FSh1 = new Note(1.0f);
    public static final Note G1 = Note.noteBlockUses(13);
    public static final Note GSh1 = Note.noteBlockUses(14);
    public static final Note A1 = Note.noteBlockUses(15);
    public static final Note ASh1 = Note.noteBlockUses(16);
    public static final Note B1 = Note.noteBlockUses(17);

    public static final Note C2 = Note.noteBlockUses(18);
    public static final Note CSh2 = Note.noteBlockUses(19);
    public static final Note D2 = Note.noteBlockUses(20);
    public static final Note DSh2 = Note.noteBlockUses(21);
    public static final Note E2 = Note.noteBlockUses(22);
    public static final Note F2 = Note.noteBlockUses(23);
    public static final Note FSh2 = new Note(2.0f);

    public final float pitch;
    private Note(float pitch) {
        this.pitch = pitch;
    }

    public static Note noteBlockUses(int uses) {
        return new Note((float) Math.pow(2.0, ((uses % 25) - 12) / 12.0));
    }
}
