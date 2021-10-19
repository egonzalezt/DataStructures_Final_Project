package com.company;

/**
 * Clase Matricula
 *
 * clase encargada de almacenar la informacion de las materias matriculadas por los distintos estudiantes
 *
 * @author Esteban Gonzalez
 * @author Esteban Sierra
 * @version 06/06/2020
 */

public class Matricula
{
    private int codEst;
    private String codMat;
    private String Grupo;

    public Matricula(int codEst, String codMat, String grupo)
    {
        this.codEst = codEst;
        this.codMat = codMat;
        Grupo = grupo;
    }

    /**
     * Get y set de la clase Pacademica
     */

    public int getCodEst() {
        return codEst;
    }

    public void setCodEst(int codEst) {
        this.codEst = codEst;
    }

    public String getCodMat() {
        return codMat+Grupo;
    }

    public void setCodMat(String codMat) {
        this.codMat = codMat;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String grupo) {
        Grupo = grupo;
    }
}
