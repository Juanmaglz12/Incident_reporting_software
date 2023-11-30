package org.mcnz.jpa.methods;

import java.util.Scanner;

public class ModuloEspecialista {

    public static void moduloEspecialista() {
        System.out.println("Elija una opción");
        System.out.println("1. Ver mis incidentes");
        System.out.println("2. Cambiar estado de incidente");
        System.out.println("3. Volver");
        System.out.println("4. Salir");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1" -> ModuloEspecialista.verMisIncidentes();
            case "2" -> ModuloEspecialista.cambiarEstadoDeIncidente();
            case "3" -> MenuMethods.menu();
            case "4" -> {
            }
            default -> {
                System.out.println("Opción no válida");
                moduloEspecialista();
            }
        }
    }

    public static void cambiarEstadoDeIncidente() {
        System.out.println("Seleccione el incidente a modificar");
    }

    public static void verMisIncidentes() {
        System.out.println("Estos son mis incidentes");

    }

    public static void validarUserEspecialista() {
    }
}
