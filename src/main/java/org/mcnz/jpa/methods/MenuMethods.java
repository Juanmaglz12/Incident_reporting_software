package org.mcnz.jpa.methods;

import org.mcnz.jpa.methods.validadorEntradas.ValidadorEntradas;
import org.mcnz.jpa.methods.validadorEntradas.ValidarUsuario;
import org.mcnz.jpa.models.users.SpecialistUser;

import java.util.Scanner;
public class MenuMethods {


    //NIVEL 1
    public static void menu() {
        System.out.println("Coloque el número de la opción deseada");
        System.out.println("1. Módulo de RRHH");
        System.out.println("2. Módulo de Comercial");
        System.out.println("3. Módulo de Mesa de ayuda");
        System.out.println("4. Módulo de Especialistas");
        System.out.println("5. Salir");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();
        switch (opcion) {
            case "1":
                ValidarUsuario.validarUsuarioRrhh();
                System.out.println("Bienvenido al modulo de RRHH");
                ModuloRRHH.validarUserRRHH();
                break;
            case "2":
                ValidarUsuario.validarUsuarioComercial();
                System.out.println("Bienvenido al modulo Comercial");
                ModuloComercial.validarUserComercial();
                break;
            case "3":
                ValidarUsuario.validarUsuarioMesaDeAyuda();
                System.out.println("Bienvenido al modulo de Mesa de Ayuda");
                ModuloMesaDeAyuda.validarUserMesaDeAyuda();
                break;
            case "4":
                SpecialistUser user = ValidarUsuario.validarUsuarioEspecialista();
                System.out.println("Bienvenido al modulo de Especialistas");
                ModuloEspecialista.moduloEspecialista(user);
                break;
            case "5":
                break;
            default:
                System.out.println("El codigo ingresado es incorrecto");
                MenuMethods.menu();
        }

    }

    public static void validarUsuario() {
        System.out.println("Por favor, ingrese su usuario");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();



        MenuMethods.menu();
    }
}
