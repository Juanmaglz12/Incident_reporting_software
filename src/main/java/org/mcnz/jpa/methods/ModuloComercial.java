package org.mcnz.jpa.methods;

import jakarta.persistence.*;
import org.mcnz.jpa.methods.validadorEntradas.ValidadorEntradas;
import org.mcnz.jpa.models.Client;
import org.mcnz.jpa.models.OfferedService;
import org.mcnz.jpa.models.users.RrhhUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModuloComercial {

    public static void moduloComercial() {
        System.out.println("Elija una opción");
        System.out.println("1. Ver clientes");
        System.out.println("2. Alta cliente");
        System.out.println("3. Modificar cliente");
        System.out.println("4. Volver");
        System.out.println("5. Salir");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1" -> ModuloComercial.verClientes();
            case "2" -> ModuloComercial.altaCliente();
            case "3" -> ModuloComercial.modificarCliente();
            case "4" -> MenuMethods.menu();
            case "5" -> {
            }
            default -> {
                System.out.println("Opción no válida");
                moduloComercial();
            }
        }
    }

    public static void verClientes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            System.out.println("**************");
            System.out.println("Clientes");
            System.out.println("**************");

            // Consulta JPQL con JOIN FETCH para recuperar servicios contratados de manera anticipada
            String jpql = "SELECT DISTINCT c FROM Client c LEFT JOIN FETCH c.contractedOfferedServices";
            TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
            List<Client> clientes = query.getResultList();

            for (Client cliente : clientes) {
                System.out.println("--------------------");
                System.out.println("Id: " + cliente.getIdClient());
                System.out.println("Razon social: " + cliente.getBusinessName());
                System.out.println("Email: " + cliente.getMail());
                System.out.println("Cuit: " + cliente.getCuit());
                System.out.println("Servicios contratados: ");
                List<OfferedService> contractedServices = cliente.getContractedOfferedServices();

                if (contractedServices != null && !contractedServices.isEmpty()) {
                    for (OfferedService contractedService : contractedServices) {
                        System.out.println(contractedService.getOfferedServiceName());
                    }
                } else {
                    System.out.println("No hay servicios contratados.");
                }
            }
            System.out.println("--------------------");

            // Cerrar transacción después del bucle
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            // Manejar la excepción
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
        }

        ModuloComercial.moduloComercial();
    }


    public static void altaCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la razon social de la empresa o presione 1 para cancelar");
        String razonSocial = ValidadorEntradas.validarEntrada2(40, ModuloComercial::moduloComercial);
        System.out.println("Ingrese el cuit  de la empresa o presione 1 para cancelar");
        String cuit = ValidadorEntradas.validarEntrada2(40, ModuloComercial::moduloComercial);
        System.out.println("Ingrese email de la empresa o presione 1 para cancelar");
        String email = ValidadorEntradas.validarEntrada2(40, ModuloComercial::moduloComercial);
        System.out.println("Agregue los servicios que desea contratar el cliente y presione, luego presione 6 para confirmar");

        System.out.println("1. SAP");
        System.out.println("2. Tango");
        System.out.println("3. Windows");
        System.out.println("4. MacOs");
        System.out.println("5. Linux Ubuntu");
        String idoffserv = sc.nextLine();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();


        String jpql = "SELECT u FROM OfferedService u WHERE idOfferedService = :idoffserv";
        TypedQuery<OfferedService> query = entityManager.createQuery(jpql, OfferedService.class);
        query.setParameter("offeredServiceName", idoffserv);
        List<OfferedService> listaServicios = query.getResultList();

        Client cliente =  new Client();
        cliente.setBusinessName(razonSocial);
        cliente.setCuit(cuit);
        cliente.setMail(email);
        cliente.setContractedOfferedServices(listaServicios);


        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        System.out.println("Alta exitosa");
        ModuloComercial.moduloComercial();

    }

    public static void modificarCliente() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Ingrese el ID del cliente a modificar");
        Scanner sc = new Scanner(System.in);
        String idCliente = sc.next();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT c FROM Client c WHERE c.idClient = :clientId";
            TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
            query.setParameter("clientId", idCliente);

            Client cliente = query.getSingleResult();

            System.out.println("--------------------");
            System.out.println("Id: " + cliente.getIdClient());
            System.out.println("Razon social: " + cliente.getBusinessName());
            System.out.println("Cuit: " + cliente.getCuit());
            System.out.println("--------------------");
            System.out.println("Ingrese el dato que desea modificar");
            System.out.println("1. Razon social");
            System.out.println("2. Cuit");

            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea pendiente

            String nuevoValor;
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la nueva razón social:");
                    nuevoValor = sc.nextLine();
                    cliente.setBusinessName(nuevoValor);
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo Cuit:");
                    nuevoValor = sc.nextLine();
                    cliente.setCuit(nuevoValor);
                    break;
                default:
                    System.out.println("Opción no válida");
                    return;
            }

            // Realizar la actualización
            entityManager.getTransaction().commit();
            System.out.println("Cliente actualizado exitosamente.");
        } catch (Exception e) {
            // Manejar la excepción
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
        }
        ModuloComercial.moduloComercial();
    }


    public static void validarUserComercial() {
        ModuloComercial.moduloComercial();
    }


}
