package com.company;

/**
 * Clase Programacion academica
 *
 * clase encargada de almacenar la informacion de la Programación academica
 *
 * @author Esteban Gonzalez
 * @author Esteban Sierra
 * @version 06/06/2020
 */

public class Pacademica
{

    private String materia;
    private int nrogrupo;
    private int idProfesor;
    private int Dia;
    private String Hini;
    private String Hfin;
    private String idAula;

    /**
     * Constructor de la clase Pacademica
     * @param materia
     * @param nrogrupo
     * @param idProfesor
     * @param idAula
     * @param dia
     * @param hini
     * @param hfin
     */

    public Pacademica(String materia, int nrogrupo, int idProfesor, int dia, String hini, String hfin, String idAula) {
        this.materia = materia;
        this.nrogrupo = nrogrupo;
        this.idProfesor = idProfesor;
        Dia = dia;
        Hini = hini;
        Hfin = hfin;
        this.idAula = idAula;
    }

    /**
     * Get y set de la clase Pacademica
     */

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getNrogrupo() {
        return nrogrupo;
    }

    public void setNrogrupo(int nrogrupo) {
        this.nrogrupo = nrogrupo;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getDia() {
        return Dia;
    }

    public void setDia(int dia) {
        Dia = dia;
    }

    public String getHini() {
        return Hini;
    }

    public void setHini(String hini) {
        Hini = hini;
    }

    public String getHfin() {
        return Hfin;
    }

    public void setHfin(String hfin) {
        Hfin = hfin;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }
}
