package com.company;

/**
 * Clase Aulas
 *
 * clase encargada de almacenar la informacion de las aulas
 *
 * @author Esteban Gonzalez
 * @author Esteban Sierra
 * @version 06/06/2020
 */

//Librerias
import java.util.Arrays;

public class Aulas
{
    private String idAula;
    private String Tipo;
    private int Capacidad;
    private int Aceso;
    private Boolean Horario[][]= new Boolean[7][36];

    public Aulas(String idAula, String tipo, int capacidad, int aceso)
    {
        this.idAula = idAula;
        Tipo = tipo;
        Capacidad = capacidad;
        Aceso = aceso;
        for(Boolean[] row:Horario)
        {
            Arrays.fill(row,true);
        }
    }

    /**
     * Get y set de la clase Pacademica
     */

    public Boolean[][] getHorario() {
        return Horario;
    }

    public void setHorario(Boolean[][] horario) {
        Horario = horario;
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int capacidad) {
        Capacidad = capacidad;
    }

    public int getAceso() {
        return Aceso;
    }

    public void setAceso(int aceso) {
        Aceso = aceso;
    }
}
