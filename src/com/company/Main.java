package com.company;

/**
 * Clase Main
 *
 * @author Esteban Gonzalez
 * @author Esteban Sierra
 * @version 06/06/2020
 */

//Librerias
import java.io.IOException;

public class Main
{

    public static void main(String[] args)  throws IOException
    {
        Folderchoose folder = new Folderchoose();
        folder.Folder(args);
    }
}
