package runner;

public class Task {

    private String titulo;
    private String nota;

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    protected String generateTitle() {
        return "AUTO" + System.currentTimeMillis();
    }

    protected String generateNote(int index) {
        return "this is a note " + index;
    }
}
