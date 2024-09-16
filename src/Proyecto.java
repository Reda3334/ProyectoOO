import java.util.ArrayList;
import java.util.HashMap;

// Definición de un Proyecto
public class Proyecto {
    String nombre;
    Gestor gestor;
    ArrayList<Programador> programadores = new ArrayList<>();
    HashMap<Programador, ArrayList<Tarea>> tareas = new HashMap<>();

    Proyecto(String nombre, Gestor gestor) {
        this.nombre = nombre;
        this.gestor = gestor;
    }

    public void asignarProgramador(Programador p) {
        if (!programadores.contains(p)) {
            programadores.add(p);
            tareas.put(p, new ArrayList<>());
        }
    }

    public void asignarTarea(Programador p, Tarea t) {
        if (programadores.contains(p)) {
            tareas.get(p).add(t);
        }
    }

    public ArrayList<Tarea> obtenerTareas(Programador p) {
        return tareas.get(p);
    }

    @Override
    public String toString() {
        return nombre + " (Gestor: " + gestor.nombre + ")";
    }
}
