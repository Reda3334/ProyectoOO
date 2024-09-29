import java.util.ArrayList;

// Definición de un Programador
public class Programador extends Usuario {
    ArrayList<Proyecto> proyectos = new ArrayList<>();

    Programador(String nombre) {
        super(nombre, "Programador");
    }

    public void asignarProyecto(Proyecto p) {
        if (!proyectos.contains(p)) {
            proyectos.add(p);
        }
    }

    public void listarProyectos() {
        for (Proyecto p : proyectos) {
            System.out.println(p);
        }
    }

    public void listarTareas(Proyecto p) {
        for (Tarea t : p.obtenerTareas(this)) {
            System.out.println(t);
        }
    }
}
