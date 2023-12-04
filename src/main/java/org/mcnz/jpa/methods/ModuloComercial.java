package org.mcnz.jpa.methods;

import jakarta.persistence.*;
import org.mcnz.jpa.methods.validadorEntradas.ValidadorEntradas;
import org.mcnz.jpa.models.Client;
import org.mcnz.jpa.models.OfferedService;
import org.mcnz.jpa.models.users.RrhhUser;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        System.out.println("Ingrese la razon social de la empresa");
        String razonSocial = ValidadorEntradas.validarEntrada();
        System.out.println("Ingrese el cuit  de la empresa");
        String cuit = ValidadorEntradas.validarEntrada();
        System.out.println("Ingrese email de la empresa");
        String email = ValidadorEntradas.validarEntrada();
        List<OfferedService> listaServicios = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        String opcion = null;
        entityManager.getTransaction().begin();

        List <OfferedService> servicios = agregarServiciosAlCliente(sc,emf, entityManager);

        System.out.println("Razon social: " + razonSocial);
        System.out.println("Cuit: " + cuit);
        System.out.println("Email: " + email);
        System.out.println("Servicios");
        for(OfferedService of : servicios){
            System.out.println(of.getOfferedServiceName());
        }

        Client cliente =  new Client();
        cliente.setBusinessName(razonSocial);
        cliente.setCuit(cuit);
        cliente.setMail(email);
        cliente.setContractedOfferedServices(servicios);
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        System.out.println("Alta exitosa");
        ModuloComercial.moduloComercial();
    }

    private static List<OfferedService> agregarServiciosAlCliente(Scanner sc, EntityManagerFactory emf, EntityManager entityManager) {
        Scanner scanner = new Scanner(System.in);
        List<OfferedService> serviciosContratados = new ArrayList<>();

        while (true) {
            System.out.println("1. Ingresar servicio");
            System.out.println("2. Finalizar");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    System.out.println("Seleccione el servicio");
                    System.out.println("1. SAP");
                    System.out.println("2. Tango");
                    System.out.println("3. Windows");
                    System.out.println("4. MacOs");
                    System.out.println("5. Linux Ubuntu");
                    String servicioElegido = scanner.nextLine();
                    switch (servicioElegido) {
                        case "1" -> servicioElegido = "SAP";
                        case "2" -> servicioElegido = "Tango";
                        case "3" -> servicioElegido = "Windows";
                        case "4" -> servicioElegido = "MacOs";
                        case "5" -> servicioElegido = "Linux Ubuntu";
                        default ->
                        {
                            System.out.println("Opción inválida");
                            continue;
                        }

                    }
                    try {
                        String jpql = "SELECT os FROM OfferedService os WHERE offeredServiceName = :opcion";
                        TypedQuery<OfferedService> query = entityManager.createQuery(jpql, OfferedService.class);
                        query.setParameter("opcion", servicioElegido);
                        OfferedService result = query.getSingleResult();
                        serviciosContratados.add(result);
                        System.out.println("Servicio agregado: " + servicioElegido);
                    } catch (NoResultException e) {
                        System.out.println("Servicio no encontrado");
                    }
                }
                case "2" -> {
                    return serviciosContratados;
                }
                default -> System.out.println("Opción inválida");
            }
        }
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
                case 1 -> {
                    System.out.println("Ingrese la nueva razón social:");
                    nuevoValor = sc.nextLine();
                    cliente.setBusinessName(nuevoValor);
                }
                case 2 -> {
                    System.out.println("Ingrese el nuevo Cuit:");
                    nuevoValor = sc.nextLine();
                    cliente.setCuit(nuevoValor);
                }
                default -> {
                    System.out.println("Opción no válida");
                    return;
                }
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
