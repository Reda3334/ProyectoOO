// Definición de una Tarea
public class Tarea {
    String nombre;
    boolean finalizada;

    Tarea(String nombre) {
        this.nombre = nombre;
        this.finalizada = false;
    }

    public void marcarFinalizada() {
        this.finalizada = true;
    }

    @Override
    public String toString() {
        return nombre + " - " + (finalizada ? "Finalizada" : "Pendiente");
    }
}
