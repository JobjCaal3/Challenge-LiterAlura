package org.example.literalurachallenge.model;

public enum Lenguaje {
    DE("de","aleman"),
    FR("fr","frances"),
    IT("it","italiano"),
    PT("pt","portugues"),
    RU("ru","ruso"),
    JA("ja","japones"),
    NL("nl","holandes"),
    SV("sv","sueco"),
    PL("pl","polaco"),
    DA("da","danes"),
    FI("fi","finlandes"),
    HE("he","hebreo"),
    NO("no","noruego"),
    CS("cs","checo");

    private String acronimoLenguaje;
    private String nombreLenguaje;
    Lenguaje (String acronimoLenguaje, String nombreLenguaje){
        this.acronimoLenguaje = acronimoLenguaje;
        this.nombreLenguaje = nombreLenguaje;
    }

    public static Lenguaje fromAcronimo(String text) {
        for (Lenguaje categoria : Lenguaje.values()) {
            if (categoria.acronimoLenguaje.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ningun libro con el lenguaje "+text+" encontrado: ");
    }

    public static Lenguaje fromlenguaje(String text) {
        for (Lenguaje categoria : Lenguaje.values()) {
            if (categoria.nombreLenguaje.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ningun libro con el lenguaje "+text+" encontrado: ");
    }
}
