import java.util.ArrayList;

// Definición de un Gestor
public class Gestor extends Usuario {
    ArrayList<Proyecto> proyectos = new ArrayList<>();

    Gestor(String nombre) {
        super(nombre, "Gestor");
    }

    public Proyecto crearProyecto(String nombre) {
        Proyecto p = new Proyecto(nombre, this);
        proyectos.add(p);
        return p;
    }

    public void listarProyectos() {
        for (Proyecto p : proyectos) {
            System.out.println(p);
        }
    }

    public void listarProgramadores(ArrayList<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            if (u instanceof Programador) {
                System.out.println(u);
            }
        }
    }
}
