package com.company;

/**
 * Clase Registro de materias
 *
 * clase encargada de procesar la información obtenida en los csv y asignar los horarios a las diferentes programaciones academicas donde un estudiante haya matriculado.
 *
 * @author Esteban Gonzalez
 * @author Esteban Sierra
 * @version 06/06/2020
 */

//Librerias
import java.io.*;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import java.util.Iterator;


public class MateriasREG
{

    private Map<String,Integer> DiccioHoras= new HashMap<>();
    private Map <String,Vector> DiccionarioGrupo= new Hashtable<>();
    private Map<Integer, Integer> Estudiante = new HashMap<>();
    private LinkedList <Pacademica> Grupos= new LinkedList<>();
    private Map<String,Aulas> DicAulas = new HashMap<>();
    private LinkedList <Matricula> Materias= new LinkedList<>();
    private LinkedList<Pacademica> Registrosfinal = new LinkedList<>();
    private String Ruta;

    /**
     * Constructor de la clase Pacademica
     * @param ruta
     */

    public MateriasREG(String ruta) throws IOException
    {
        try
        {
            Registro reg = new Registro(ruta);
            this.Ruta = ruta;
            DiccioHoras = reg.Horas();
            Estudiante = reg.RegistroEST();
            Materias = reg.Registromat();
            DicAulas = reg.RegistroAulas();
            Grupos = reg.RegistroPA();
        } catch (Exception e)
        {
            System.out.println("error");
        }
    }

    /**
     * Metodo Conteo.
     *
     * Encargado de obtener el total de la estudiantes por grupo
     *
     */

    public void Conteo()
    {
        //pila que contiene las matriculas de los estudiantes
        LinkedList <Matricula> test= Materias;
        //bucle while que recorre la pila retirando elementos
        while(!test.isEmpty())
        {
            //Vector el cual contiene la informacion del la matricula
            Vector data = new Vector();
            //retiro elemento de la lista y se almacena en una variable tipo Matricula
            Matricula mat  = test.pop();
            //String que almacena el codigo de la materia
            String codMat= mat.getCodMat();

            //deteccion de si el codigo de materia no existe, de lo contrario se registra y si esto no ocurre es porque ningun estudiante registro a ese grupo
            if(DiccionarioGrupo.containsKey(codMat))
            {
                //obtencion de los valores de diccionario grupo que contiene todos los grupos de la universidad que matricularon los estudiantes

                Vector temp = DiccionarioGrupo.get(codMat); // codigo del materia+nro grupo
                Boolean condDiscapacidad =(boolean) temp.get(1); // deteccion de si hay almenos un estudiante discapacitado
                int cont = ((int) temp.get(0))+1; // aumento del total de alumnos matriculados al grupo

                if(Estudiante.get((mat.getCodEst()))==1)// deteccion de si el estudiante es discapacitado
                {
                    temp.set(0,cont);
                    temp.set(1,true);
                    DiccionarioGrupo.put(codMat,temp);// actualiza los datos del grupo
                }else // caso contrario de si no lo es
                {
                    temp.set(0,cont);
                    if(!condDiscapacidad)
                    {
                        temp.set(1,false);
                    }
                    DiccionarioGrupo.put(codMat,temp);// actualiza los datos del grupo
                }
            }else //caso contrario de que no se haya registrado aun la materia con el nro de grupo
            {
                Vector temp = new Vector(); // vector con la informacion basica -> #estudiantes, se necesita un aula con capacidad para discapacitados (boolean)

                if(Estudiante.get((mat.getCodEst()))==1) // si el estudiante es discapacidado
                {
                    temp.insertElementAt(1,0);
                    temp.insertElementAt(true,1);
                    DiccionarioGrupo.put(codMat,temp);// si el estudiante no es discapacidado
                }else
                {
                    temp.insertElementAt(1,0);
                    temp.insertElementAt(false,1);
                    DiccionarioGrupo.put(codMat,temp);
                }
            }
        }
    }

    /**
     * Metodo Horario.
     *
     * Encargado de registrar las aulas para las diferentes programaciones academicas
     * el cual hayan registrado estudiantes
     */


    public void Horario()
    {
        int cont=0; // contador para detectar las programaciones academicas que le registraron aulas
        int cont2=0; // contador para detectar las programaciones academicas que no le registraron aulas

        //ciclo que detecta programaciones academicas que no tengan estudiantes matriculados
        for (int i=0;i<Grupos.size();i++)
        {
            Pacademica pa = Grupos.get(i);
            String Pagrupo = pa.getMateria()+pa.getNrogrupo();
            if(!DiccionarioGrupo.containsKey(Pagrupo))
            {
                Grupos.remove(i);
            }
        }

        //ciclo que detecta si hay programaciones academicas con aulas ya asignadas
        for (int i=0;i<Grupos.size();i++)
        {
            Pacademica pa = Grupos.get(i);
            String idAula = pa.getIdAula();
            if(!DicAulas.containsKey(idAula)&&!idAula.equals("00000"))//condicion para detectar si en el diccionario de las aulas que fueron matriculadas en la programacion academica existen
            {
                pa.setIdAula("00000"); // si no existe el aula, la elimina de la programacion academica para asignarle futuramente una nueva aula
                Grupos.set(i,pa); // actualiza cambios
            }
        }

        //Algoritmo voraz que recorre todas la programaciones academicas matriculadas por los estudiantes
        for(int i=0;i<Grupos.size();i++)
        {
            Pacademica seleccion = Grupos.get(i);//obtiene la informacion de la programacion
            String idAula = seleccion.getIdAula();//obtiene el id del aula
            String codmat = seleccion.getMateria() + seleccion.getNrogrupo();// obtiene el codigo de la materia

            boolean cond = true; // boolean para indicar si la programacion academica se le fue registrada un aula

            //Caso 1 la programacion ya tiene aula asignada.

            // informacion basica del aula

            Aulas aula = DicAulas.get(idAula); //obtencion del aula
            int Hini = DiccioHoras.get(seleccion.getHini());
            int Hfin = DiccioHoras.get(seleccion.getHfin());
            int Dia = seleccion.getDia();
            Vector vect = DiccionarioGrupo.get(codmat); //obtencion de informacion basica del aula

            if (vect!=null)
            {
                // informacion basica de la programacion academica
                Boolean Condest =(boolean)vect.get(1);
                int capacidad = (int) vect.get(0);// capacidad requerida

                if (!idAula.equals("00000"))// asignacion de horario al aula
                {
                    Boolean HorarioAula[][] = aula.getHorario();// obtencion de matriz de horario
                    for (int j = Hfin; j > Hini; j--)// ciclo que asigna el horario al aula
                    {
                        HorarioAula[Dia][j] = false;//establecimiento de aula ocupada en ciertas horas
                    }
                    aula.setHorario(HorarioAula);// actualizar horario
                    DicAulas.put(idAula,aula);// actualiza la informacion del aula
                    Registrosfinal.add(seleccion);// añadir grupo con aula asignado
                }else if(Condest)// Caso 2 contrario aun no se ha matriculado aula y el estudiante es discapacitado
                {
                    Iterator iterator = DicAulas.entrySet().iterator();// iterador para recorrer todas las aulas
                    while (iterator.hasNext())// ciclo que recorre todas la aulas
                    {
                        Map.Entry me2 = (Map.Entry) iterator.next();//obtencion del aula
                        Aulas auladis = DicAulas.get(me2.getKey());
                        if(auladis.getAceso()==1&&(auladis.getCapacidad()==capacidad||auladis.getCapacidad()<=capacidad+5))// detenccion de si el aula seleccionada cumple con las condiciones para el grupo con estudiante discapacitado
                        {
                            Boolean HorarioAula[][] = auladis.getHorario();// obtencion de matriz de horario
                            boolean disponible = true;//deteccion de si el aula esta disponible a la hora de la clase
                            for (int j = Hfin; j > Hini; j--)// ciclo que detecta si el aula esta libre en la hora necesitada
                            {
                                if(!HorarioAula[Dia][j])//deteccion de si las horas se cruzarían
                                {
                                    disponible = false;
                                }
                            }
                            if(disponible)// si el aula esta libre asigna el nuevo horario
                            {
                                for (int j = Hfin; j > Hini; j--)// ciclo que asigna el horario al aula
                                {
                                    HorarioAula[Dia][j] = false;//establecimiento de aula ocupada en ciertas horas
                                }
                                seleccion.setIdAula(auladis.getIdAula());// establecer aula para la programacion academica
                                DicAulas.put(idAula,auladis);// actualiza la informacion del aula
                                Registrosfinal.add(seleccion);//ingresa el grupo con aula asinadada
                                cond=true;// aula encontrada
                                break;// detener bucle ya que se asigno una nueva aula
                            }else{cond=false;}// notificar que no se encontro aula
                        }
                    }
                }else// Caso 3 asignacion de aulas donde ningun estudiante es discapacitado
                {
                    Iterator iterator = DicAulas.entrySet().iterator();//iterador para recorrer todas las aulas
                    while (iterator.hasNext())// ciclo que recorre todas la aulas
                    {
                        Map.Entry me2 = (Map.Entry) iterator.next();
                        Aulas aulagen = DicAulas.get(me2.getKey());//obtencion del aula
                        if(aulagen.getCapacidad()==capacidad||aulagen.getCapacidad()<=capacidad+5)// condiciones para registrar el grupo en el aula seleccionada
                        {
                            Boolean HorarioAula[][] = aulagen.getHorario();// obtencion de matriz de horario
                            boolean disponible = true;//deteccion de si el aula esta disponible a la hora de la clase
                            for (int j = Hfin; j > Hini; j--)// ciclo que detecta si el aula esta libre en la hora necesitada
                            {
                                if(!HorarioAula[Dia][j])//deteccion de si las horas se cruzarían
                                {
                                    disponible = false;
                                }
                            }
                            if(disponible)// si el aula esta libre asigna el nuevo horario
                            {
                                for (int j = Hfin; j > Hini; j--)
                                {
                                    HorarioAula[Dia][j] = false;//establecimiento de aula ocupada en ciertas horas
                                }
                                seleccion.setIdAula(aulagen.getIdAula());// establecer aula para la programacion academica
                                DicAulas.put(idAula,aulagen);// actualiza la informacion del aula
                                Registrosfinal.add(seleccion);//ingresa el grupo con aula asinadada
                                cond=true;// aula encontrada
                                break;// detener bucle ya que se asigno una nueva aula
                            }else{cond=false;}// notificar que no se encontro aula
                        }
                    }
                }
            }
            if (cond)//  si el aula se registro aumentar contador de que no se asigno aula para un grupo
            {
                cont++;//aumentar contador
                System.out.println("Se asigno aula para el grupo -> " +codmat);//
            }if (!cond)// si el aula se registro aumentar contador de que no se asigno aula para un grupo
            {
                cont2++;//aumentar contador
                System.out.println("No se pudo asignar aula para el grupo -> " + codmat);
            }
        }
        //impresion de resultados
        System.out.println("Grupos con aulas -> "+cont);
        System.out.println("Grupos sin aulas -> "+cont2);
    }

    /**
     * Metodo File.
     *
     * Metodo encargado de registrar la programacion academica con las aulas asignadas en un nuevo archivo de texto llamado "Pa20192.txt"
     *
     */

    public void File() throws IOException
    {
        LinkedList <Pacademica> Grupo = Registrosfinal;

        File file = new File("Pa20192.txt");

        file.createNewFile();

        FileWriter writer = new FileWriter(file);

        while(!Grupo.isEmpty())
        {
            Pacademica pa = Grupo.pop();
            String materia = pa.getMateria();
            int nrogrupo = pa.getNrogrupo();
            int idProfesor = pa.getIdProfesor();
            int Dia = pa.getDia();
            String Hini = pa.getHini();
            String Hfin = pa.getHfin();
            String idAula = pa.getIdAula();
            String Resultado = materia + "\t" + nrogrupo + "\t" + idProfesor + "\t" + Dia + "\t" + Hini + "\t" + Hfin + "\t" + idAula;
            writer.write(Resultado + "\n");
        }
        writer.flush();
        writer.close();
    }
}
