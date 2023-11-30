package org.mcnz.jpa.methods.validadorEntradas;

import org.mcnz.jpa.methods.ModuloRRHH;

import java.util.Scanner;

public class ValidadorEntradas {

    public static String validarEntrada(Scanner scanner, String mensaje, int longitudMaxima) {
        String entrada;
        do {
            System.out.println(mensaje);
            entrada = scanner.nextLine();

            if (entrada.equals("1")) {
                ModuloRRHH.moduloRRHH();
            } else if (entrada.isEmpty() || entrada.length() > longitudMaxima) {
                System.out.println("Formato incorrecto");
            }
        } while (entrada.isEmpty() || entrada.equals("1") || entrada.length() > longitudMaxima);

        return entrada;
    }

    public static String validarEntrada2(int longitudMaxima, Modulo modulo) {
        Scanner sc = new Scanner(System.in);
        String mensaje = "";
        do {
            mensaje = sc.nextLine();
            if (mensaje.equals("1")) {
                modulo.ejecutar();
            } else if (mensaje.isEmpty() || mensaje.length() > longitudMaxima) {
                System.out.println("Formato incorrecto");
            }
        } while (mensaje.isEmpty() || mensaje.equals("1") || mensaje.length() > longitudMaxima);

        return mensaje;
    }




}
