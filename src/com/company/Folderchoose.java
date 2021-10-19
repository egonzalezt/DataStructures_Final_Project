package com.company;

/**
 * Clase Folderchoose
 *
 * clase que utiliza javaFX para la interfaz
 *
 * @author Esteban Gonzalez
 * @author Esteban Sierra
 * @version 06/06/2020
 */

//Librerias
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.*;
import javafx.stage.DirectoryChooser;
import javafx.scene.text.Text;

public class Folderchoose extends Application
{
    @Override

    public void start(Stage stage)
        {

            final String[] Ruta = {""};
            try
            {
                Text text = new Text();

                //Setting the text to be added.
                text.setText("Integrantes Esteban Gonzalez Tamayo y Esteban Sierra Patiño");
                text.setX(800);
                text.setY(50);
                stage.setTitle("Proyecto Final Estructuras de datos II");
                DirectoryChooser dir_chooser = new DirectoryChooser();

                Label label = new Label("No se ha seleccionado una ruta");

                Button button = new Button("Seleccionar ruta"); // boton para seleccionar la ruta
                Alert a = new Alert(Alert.AlertType.NONE);//notificacion
                Alert b = new Alert(Alert.AlertType.ERROR);//notificacion

                //Accion para obtener la ruta de los archivos csv
                EventHandler<ActionEvent> event =
                        new EventHandler<ActionEvent>()
                        {

                            public void handle(ActionEvent e)
                            {
                                File file = dir_chooser.showDialog(stage);
                                System.out.println(file);
                                Ruta[0] =file.toString();
                                if (file != null)
                                {
                                    label.setText("Ruta de csv -> "+file.getAbsolutePath());
                                }
                            }
                        };

                button.setOnAction(event);//disparador si el boton 1 fue presionado

                Button button2 = new Button("Ejecutar Programa");//boton para ejecutar el programa

                EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>()//evento si el disparador se activa el cual ejecuta todo el programa
                        {

                            public void handle(ActionEvent e)
                            {
                                try
                                {
                                    MateriasREG prueba = new MateriasREG(Ruta[0]);
                                    prueba.Conteo();
                                    prueba.Horario();
                                    prueba.File();
                                    a.setAlertType(Alert.AlertType.INFORMATION); //pop up que notifica que todo fue un exito
                                    a.setTitle("Notificación");
                                    a.setHeaderText("Éxito en la ejecución!!");
                                    a.setContentText("La información se encuentra registrada en el archivo Pa20192");
                                    a.show();
                                } catch (IOException ex)
                                {
                                    b.setAlertType(Alert.AlertType.ERROR);// notificacion que indica si hubo un error en el proceso
                                    ex.printStackTrace();
                                }
                            }
                        };

                button2.setOnAction(event2);//disparador si el boton 2 fue presionado

                VBox vbox = new VBox();//creacion de interfaz

                /*
                *
                * Agrega los botones, texto y informacion requerida a la interfaz
                *
                 */
                vbox.getChildren().add(label);
                vbox.getChildren().add(button);
                vbox.getChildren().add(button2);
                vbox.getChildren().add(text);

                vbox.setAlignment(Pos.CENTER);//asignar posicion a los botones

                Scene scene = new Scene(vbox, 600, 400);//tamaño de la ventana

                stage.setScene(scene);
                stage.show();//mostrar contenido

            }

            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }


        /**
        * Clase Folder encargada de ejecutar javaFX para crear la interfaz con la informacion proporcionada.
        */
        public void Folder(String args[])
        {
            launch(args);//ejecutar aplicacion
        }
    }

