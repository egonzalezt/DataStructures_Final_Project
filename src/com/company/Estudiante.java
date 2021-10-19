package com.company;

/**
 * Clase Estudiantes
 *
 * clase encargada de almacenar la informacion de los Estudiantes
 *
 * @author Esteban Gonzalez
 * @author Esteban Sierra
 * @version 06/06/2020
 */

public class Estudiante
{
    private int codigo;
    private int discapacidad;

    public Estudiante(int codigo, int discapacidad) {
        this.codigo = codigo;
        this.discapacidad = discapacidad;
    }

    /**
     * Get y set de la clase Pacademica
     */

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(int discapacidad) {
        this.discapacidad = discapacidad;
    }
}

