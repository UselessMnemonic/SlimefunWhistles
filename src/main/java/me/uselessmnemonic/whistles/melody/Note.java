package me.uselessmnemonic.whistles.melody;

public class Note {

    public static final Note FSh0 = new Note(0.5f);

    public static final Note G0 = new Note(0.529732f);
    public static final Note GSh0 = new Note(0.561231f);
    public static final Note A0 = new Note(0.594604f);
    public static final Note ASh0 = new Note(0.629961f);
    public static final Note B0 = new Note(0.667420f);
    public static final Note C0 = new Note(0.707107f);
    public static final Note CSh0 = new Note(0.749154f);
    public static final Note D0 = new Note(0.793701f);
    public static final Note DSh0 = new Note(0.840896f);
    public static final Note E0 = new Note(0.890899f);
    public static final Note F0 = new Note(0.943874f);

    public static final Note FSh = new Note(1.0f);

    public static final Note G1 = new Note(1.059463f);
    public static final Note GSh1 = new Note(1.122462f);
    public static final Note A1 = new Note(1.189207f);
    public static final Note ASh1 = new Note(1.259921f);
    public static final Note B1 = new Note(1.334840f);
    public static final Note C1 = new Note(1.414214f);
    public static final Note CSh1 = new Note(1.498307f);
    public static final Note D1 = new Note(1.587401f);
    public static final Note DSh1 = new Note(1.681793f);
    public static final Note E1 = new Note(1.781797f);
    public static final Note F1 = new Note(1.887749f);

    public static final Note FSh1 = new Note(2.0f);

    public final float pitch;
    public Note(float pitch) {
        this.pitch = pitch;
    }
}
