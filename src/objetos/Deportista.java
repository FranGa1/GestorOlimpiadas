package objetos;

import java.util.List;

public class Deportista {
    private int id;
    private String nombres, apellidos, email, telefono;
    private Pais pais;
    private List<Disciplina> disciplinas;

    public Deportista(){}

    public Deportista(String nombres, String apellidos, String email, String telefono, Pais pais, List<Disciplina> disciplinas) {
        this.nombres = nombres.trim();
        this.apellidos = apellidos.trim();
        this.email = email.trim();
        this.telefono = telefono.trim();
        this.pais = pais;
        this.disciplinas = disciplinas;
    }

    public Deportista(String nombres, String apellidos, String email, String telefono, Pais pais, List<Disciplina> disciplinas,int id) {
        this.nombres = nombres.trim();
        this.apellidos = apellidos.trim();
        this.email = email.trim();
        this.telefono = telefono.trim();
        this.pais = pais;
        this.disciplinas = disciplinas;
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Deportista{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", pais=" + pais +
                ", disciplinas=" + disciplinas +
                '}';
    }
}