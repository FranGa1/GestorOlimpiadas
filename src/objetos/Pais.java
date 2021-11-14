package objetos;

import java.util.Locale;

public class Pais {
    private String nombre;
    private int id;

    public Pais(String nombre) {
        this.nombre = nombre.trim().toUpperCase(Locale.ROOT);
    }

    public Pais(String nombre, int id) {
        this.nombre = nombre.trim().toUpperCase(Locale.ROOT);
        this.id = id;
    }

    public Pais() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
