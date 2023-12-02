package org.mcnz.jpa;

import org.mcnz.jpa.methods.MenuMethods;
import org.mcnz.jpa.methods.ModuloMesaDeAyuda;
import org.mcnz.jpa.methods.initialization.Initialization;
import org.mcnz.jpa.models.users.HelpDeskUser;

public class Main {
    public static void main(String[] args) {



        /*Metodo para poblar la base de datos.
          Ejecutar una vez y volver a comentar.*/

        //Initialization.initialization();

        System.out.println("********************************");
        System.out.println("Bienvenido al sistema de tickets");
        System.out.println("********************************");

        MenuMethods.menu();








    }
}