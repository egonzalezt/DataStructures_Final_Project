package com.company;

/**
 * Clase Registro
 *
 * Clase encargada de leer los archivos csv
 *
 * @author Esteban Gonzalez
 * @author Esteban Sierra
 * @version 06/06/2020
 */

//Librerias
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.HashMap;


public class Registro
{
    private String Ruta;

    /**
     * Contructor
     *
     * encargado de inicializar la clase Registro y obtener la ruta donde se contienen los csv
     * @param ruta
     *
     */

    public Registro(String ruta)
    {
        this.Ruta = ruta;
    }

    /**
     * Metodo Registromat.
     *
     * Metodo encargado de leer el csv que contiene la informacion las matriculas
     * @return LinkedList
     */

    public LinkedList Registromat() throws IOException
    {

        LinkedList<Matricula> mat20192 = new LinkedList<Matricula>();

        String csvFile = Ruta+"\\mat20192.csv";
        BufferedReader br = null;
        String line;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while (line != null)
            {

                String[] attributes = line.split(",");
                int codEst=Integer.parseInt(attributes[0]);
                String codMat = attributes[1];
                String Grupo=attributes[2];
                mat20192.add(new Matricula(codEst,codMat,Grupo));
                line = br.readLine();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return mat20192;
    }

    /**
     * Metodo RegistroPA.
     *
     * Metodo encargado de leer el csv que contiene la programacion academica
     * @return LinkedList
     */

    public LinkedList RegistroPA() throws IOException
    {

        LinkedList<Pacademica> pa20192 = new LinkedList<Pacademica>();

        String csvFile = Ruta+"\\pa20192.csv";
        BufferedReader br = null;
        String line;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while (line != null)
            {

                String[] attributes = line.split(",");
                String materia=attributes[0];
                int nrogrupo=Integer.parseInt(attributes[1]);
                int idProfesor=Integer.parseInt(attributes[2]);
                String Diastr = attributes[3];
                int Dia=0;
                String Hini=attributes[4];
                String temp=attributes[5];
                String Hfin = "";
                String idAula="";
                if(temp.contains(":"))
                {
                    Hfin = attributes[5];
                    idAula = attributes[6];
                    switch (Diastr)
                    {
                        case "lunes": {
                            Dia = 0;
                            break;
                        }
                        case "martes": {
                            Dia = 1;
                            break;
                        }
                        case "miÃ©rcoles":
                        case "miércoles": {
                            Dia = 2;
                            break;
                        }
                        case "jueves": {
                            Dia = 3;
                            break;
                        }
                        case "viernes": {
                            Dia = 4;
                            break;
                        }
                        case "sÃ¡bado":
                        case "sábado": {
                            Dia = 5;
                            break;
                        }

                        case "domingo": {
                            Dia = 6;
                            break;
                        }

                    }
                    pa20192.add(new Pacademica(materia,nrogrupo,idProfesor,Dia,Hini,Hfin,idAula));
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return pa20192;
    }

    /**
     * Metodo RegistroEST.
     *
     * Metodo encargado de leer el csv que contiene los estudiantes
     * @return HashMap
     */

    public HashMap RegistroEST() throws IOException
    {
        HashMap<Integer, Integer> Estudiante = new HashMap<>();
        String csvFile =Ruta+"\\estudiantes.csv";
        BufferedReader br = null;
        String line;
        try
        {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while (line != null)
            {

                String[] attributes = line.split(",");
                int codigo= Integer.parseInt(attributes[0]);;
                int discapacidad= Integer.parseInt(attributes[1]);;

                Estudiante.put(codigo,discapacidad);
                line = br.readLine();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return Estudiante;
    }

    /**
     * Metodo RegistroAulas.
     *
     * Metodo encargado de leer el csv que contiene las aulas
     * @return HashMap
     */

    public HashMap RegistroAulas() throws IOException
    {

        HashMap<String,Aulas> aulas = new HashMap<>();

        String csvFile = Ruta+"\\aulas.csv";
        BufferedReader br = null;
        String line;
        try
        {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while (line != null)
            {

                String[] attributes = line.split(",");
                String idAula= attributes[0];
                String Tipo= attributes[1];
                String Capacida = attributes[2];
                int Capacidad=0;
                if(!Capacida.equals("N/A"))
                {
                    Capacidad= Integer.parseInt(attributes[2]);
                }
                int Aceso= Integer.parseInt(attributes[3]);
                aulas.put(idAula,new Aulas(idAula,Tipo,Capacidad,Aceso));
                line = br.readLine();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return aulas;
    }

    /**
     * Metodo Horas.
     *
     * Metodo encargado de leer el csv que contiene las horas de clase en la universidad para asignarlos en la matriz de aula en la clase MateriasREG
     * @return HashMap
     */

    public HashMap Horas() throws IOException
    {
        HashMap<String,Integer> Diccio= new HashMap<>();

        String csvFile = Ruta+"\\Diccionario.csv";
        BufferedReader br = null;
        String line;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while (line != null)
            {

                String[] attributes = line.split(",");
                String Hora=attributes[0];
                int Posicion = Integer.parseInt(attributes[1]);
                Diccio.put(Hora,Posicion);
                line = br.readLine();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return Diccio;
    }
}


