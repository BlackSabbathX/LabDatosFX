package BaseDeDatos;

import Estructura.Habilidad;
import Estructura.Lista;
import Estructura.Titulo;

public class Emparejador {

    public static boolean cumpleRequisitos(int idVacante, int idAspirante) {
        Vacante vacante = Vacante.getVacanteAt(Vacante.indexOf(idVacante));
        Aspirante aspirante = Aspirante.getAspiranteAt(Aspirante.indexOf(idAspirante));

        Lista<Titulo> titulosV = vacante.getTitulos();
        Lista<Titulo> titulosA = aspirante.getTitulos();
        Lista<Habilidad> habilidadesV = vacante.getHabilidades();
        Lista<Habilidad> habilidadesA = aspirante.getHabilidades();

        if (titulosV.getItemCount() > titulosA.getItemCount()) return false;
        if (habilidadesV.getItemCount() > habilidadesA.getItemCount()) return false;
        if (titulosV.get(0) == Titulo.Ninguno && habilidadesV.get(0) == Habilidad.Ninguna) return true;

        for (Titulo titulo : titulosV) {
            boolean okay = false;
            for (Titulo titulo1 : titulosA) {
                if (titulo == titulo1) {
                    okay = true;
                    aspirante.getTitulos().reset();
                    break;
                }
            }
            if (!okay) {
                vacante.getTitulos().reset();
                return false;
            }
        }

        for (Habilidad habilidad : habilidadesV) {
            boolean okay = false;
            for (Habilidad habilidad1 : habilidadesA) {
                if (habilidad == habilidad1 && habilidad1.getNivel() >= habilidad.getNivel()) {
                    okay = true;
                    aspirante.getHabilidades().reset();
                    break;
                }
            }
            if (!okay) {
                vacante.getHabilidades().reset();
                return false;
            }
        }
        return true;
    }

    public boolean esMejor(int ida1, int ida2) {
        Aspirante a1 = Aspirante.getAspiranteAt(Aspirante.indexOf(ida1));
        Aspirante a2 = Aspirante.getAspiranteAt(Aspirante.indexOf(ida2));

/////////////////////////////////////////////////

        return true;
    }

}
