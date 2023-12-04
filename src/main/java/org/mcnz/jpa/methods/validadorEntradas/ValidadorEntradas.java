package org.mcnz.jpa.methods.validadorEntradas;

import org.mcnz.jpa.methods.ModuloRRHH;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidadorEntradas {

    public static void validarNumero(Scanner sc, int opcion) {
        while (!sc.hasNextInt()){
            System.out.println("Opcion inválida, intentelo nuevamente");
        }
        opcion = sc.nextInt();
    }

    public static void validarPalabra(Scanner sc, String texto) {
        while (!sc.hasNext()){
            System.out.println("Opcion inválida, intentelo nuevamente");
        }
        texto = sc.nextLine();
    }

    public static String textoCapital(String texto){
        if (texto != null && !texto.isEmpty()) {
            String[] palabras = texto.split("\\s+");

            for (int i = 0; i < palabras.length; i++) {
                if (!palabras[i].isEmpty()) {
                    palabras[i] = Character.toUpperCase(palabras[i].charAt(0)) + palabras[i].substring(1);
                }
            }
            return String.join(" ", palabras);
        } else {
            return texto;
        }
    }

    public static String validarEntrada() {
        Scanner sc = new Scanner(System.in);
        String mensaje = "";
        do {
            mensaje = sc.nextLine();
            if (mensaje.isEmpty() || mensaje.length() > 40) {
              System.out.println("Formato incorrecto");
            }
        } while (mensaje.isEmpty() || mensaje.length() > 40);

        return mensaje;
    }




}
