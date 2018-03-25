package BaseDeDatos;

import Estructura.*;

public class Emparejador {

    public static boolean cumpleRequisitos(Vacante vacante, Aspirante aspirante) {
        Lista<Titulo> titulosV = vacante.getTitulos();
        Lista<Titulo> titulosA = aspirante.getTitulos();
        Lista<HabilidadNivel> habilidadesV = vacante.getHabilidades();
        Lista<HabilidadNivel> habilidadesA = aspirante.getHabilidades();

        if (aspirante.getSalariomin() > vacante.getMax()) return false;
        if (vacante.getJornada() != aspirante.getJornada() && aspirante.getJornada() != Jornada.Ambas) return false;
        if (titulosV.getItemCount() > titulosA.getItemCount()) return false;
        if (habilidadesV.getItemCount() > habilidadesA.getItemCount()) return false;
        if (titulosV.get(0) == Titulo.Ninguno && habilidadesV.get(0).getText().equals(Habilidad.Ninguna.getText()))
            return true;

        if (titulosV.get(0) != Titulo.Ninguno) {
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
        }

        if (habilidadesV.get(0).habilidad != Habilidad.Ninguna) {
            for (HabilidadNivel habilidad : habilidadesV) {
                boolean okay = false;
                for (HabilidadNivel habilidad1 : habilidadesA) {
                    if (habilidad.habilidad == habilidad1.habilidad && habilidad1.getNivel() >= habilidad.getNivel()) {
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
        }
        return true;
    }

    public static boolean esMejor(Aspirante a1, Aspirante a2, Vacante v) {
        int tot1 = 0, tot2 = 0;
        if (v.getHabilidades().get(0).habilidad != Habilidad.Ninguna) {
            for (HabilidadNivel h : v.getHabilidades()) {
                for (HabilidadNivel h1 : a1.getHabilidades()) {
                    if (h1.habilidad == h.habilidad) {
                        tot1 += h1.getNivel();
                        a1.getHabilidades().reset();
                        break;
                    }
                }
                for (HabilidadNivel h2 : a2.getHabilidades()) {
                    if (h2.habilidad == h.habilidad) {
                        tot2 += h2.getNivel();
                        a2.getHabilidades().reset();
                        break;
                    }
                }
            }
        }
        if (tot1 == tot2) {
            for (HabilidadNivel h1 : a1.getHabilidades()) {
                tot1 += h1.getNivel();
            }
            for (HabilidadNivel h2 : a2.getHabilidades()) {
                tot2 += h2.getNivel();
            }
            tot1 += 10 * a1.getTitulos().getItemCount();
            tot2 += 10 * a2.getTitulos().getItemCount();
        }
        return tot1 >= tot2;
    }

    public static Lista<Aspirante> lista(Vacante v) {
        Lista<Aspirante> l = new Lista<>();
        for (Aspirante a : Aspirante.getAspirantes()) {
            if (cumpleRequisitos(v, a)) {
                Lista.insertarAspiranteOrdenado(l, a, v);
            }
        }
        return l;
    }

    public static Lista<Aspirante> lista(int idV) {
        return lista(Vacante.getVacanteAt(Vacante.indexOf(idV)));
    }
}
