package org.mcnz.jpa.methods;

import jakarta.persistence.*;
import org.mcnz.jpa.methods.validadorEntradas.ValidadorEntradas;
import org.mcnz.jpa.models.Client;
import org.mcnz.jpa.models.Incident;
import org.mcnz.jpa.models.OfferedService;
import org.mcnz.jpa.models.users.RrhhUser;
import org.mcnz.jpa.models.users.SpecialistUser;

import java.util.List;
import java.util.Scanner;

public class ModuloMesaDeAyuda {

    public static void moduloMesaDeAyuda() {
        System.out.println("Elija una opción");
        System.out.println("1. Ver incidentes");
        System.out.println("2. Alta incidente");
        System.out.println("3. Reporte diario de tecnicos");
        System.out.println("4. Volver");
        System.out.println("5. Salir");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1" -> ModuloMesaDeAyuda.verIncidentes();
            case "2" -> ModuloMesaDeAyuda.altaIncidente();
            case "3" -> ModuloMesaDeAyuda.reporteDiarioDeTecnicos();
            case "4" -> MenuMethods.menu();
            case "5" -> {
            }
            default -> {
                System.out.println("Opción no válida");
                moduloMesaDeAyuda();
            }
        }
    }

    public static void reporteDiarioDeTecnicos() {
        System.out.println("Este es el reporte diario");
        System.out.println("1. Volver");
        System.out.println("2. Salir");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();
        switch (opcion) {
            case "1":
                ModuloMesaDeAyuda.moduloMesaDeAyuda();
                break;
            case "2":
                break;
            default:
                System.out.println("Opción no válida");
        }

    }

    public static void altaIncidente() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Ingrese el cuit del cliente o presione 1 para cancelar");
            String cuit = sc.nextLine();

            while (!cuit.equals("1")) {
                entityManager.getTransaction().begin();

                // Consultar el cliente con los servicios contratados
                String jpqlCliente = "SELECT c FROM Client c LEFT JOIN FETCH c.contractedOfferedServices cs WHERE c.cuit = :cuit";
                TypedQuery<Client> queryCliente = entityManager.createQuery(jpqlCliente, Client.class);
                queryCliente.setParameter("cuit", cuit);
                Client cliente = queryCliente.getSingleResult();

                System.out.println("--------------------");
                System.out.println("Id: " + cliente.getIdClient());
                System.out.println("Razon social: " + cliente.getBusinessName());
                System.out.println("Email: " + cliente.getMail());
                System.out.println("Cuit: " + cliente.getCuit());
                System.out.println("Servicios contratados: ");

                List<OfferedService> contractedServices = cliente.getContractedOfferedServices();
                if (contractedServices.isEmpty()) {
                    System.out.println("No hay servicios contratados.");
                    ModuloMesaDeAyuda.moduloMesaDeAyuda();
                }
                System.out.println("--------------------");

                // Imprimir los servicios contratados
                System.out.println("Ingrese el nombre del servicio por el que desea iniciar un incidente");
                for (OfferedService ofServ : contractedServices) {
                    System.out.println(ofServ.getOfferedServiceName());
                }

                String nombreServicio = sc.nextLine();

                // Consultar el servicio seleccionado
                String jpqlServicio = "SELECT u FROM OfferedService u WHERE offeredServiceName = :servicio";
                TypedQuery<OfferedService> queryServicio = entityManager.createQuery(jpqlServicio, OfferedService.class);
                queryServicio.setParameter("servicio", nombreServicio);
                OfferedService servicio = queryServicio.getSingleResult();


                System.out.println("Ingrese el tipo de problema");
                String tipoProblema = sc.nextLine();
                System.out.println("Ingrese la descripción del problema");
                String descrProblema = sc.nextLine();
                System.out.println("Seleccione el id del especialista disponible");



                // Buscar entre los especialistas que puedan resolver el problema

                String jpqlSpecialist = "SELECT c FROM SpecialistUser c LEFT JOIN FETCH c.offeredServices cs WHERE cs.offeredServiceName = :nombreServicio AND c.alta";
                TypedQuery<SpecialistUser> querySpecialist = entityManager.createQuery(jpqlSpecialist, SpecialistUser.class);
                querySpecialist.setParameter("nombreServicio", nombreServicio);
                List<SpecialistUser> specialistUsers = querySpecialist.getResultList();

                for (SpecialistUser empleado : specialistUsers){
                    System.out.println("--------------------");
                    System.out.println("Id: " + empleado.getIdUser());
                    System.out.println("Nombre: " + empleado.getName());
                    System.out.println("Apellido: " + empleado.getUserName());
                    System.out.println("Nombre de usuario: " + empleado.getUserName());
                    System.out.println("email: " + empleado.getEmail());
                    System.out.println("Estado: " + empleado.getAlta());
                    System.out.println("--------------------");
                }

                String especialistaDisponible = sc.nextLine();

                String jpqlSpecialist2 = "SELECT c FROM SpecialistUser c WHERE c.idUser = :especialistaDisponible";
                TypedQuery<SpecialistUser> querySpecialist2 = entityManager.createQuery(jpqlSpecialist2, SpecialistUser.class);
                querySpecialist2.setParameter("especialistaDisponible", especialistaDisponible);
                SpecialistUser especialista = querySpecialist2.getSingleResult();


                // Crear e insertar el incidente
                entityManager.getTransaction().begin();
                Incident incident = new Incident();
                incident.setTypeProblem(tipoProblema);
                incident.setProblemDescription(descrProblema);
                incident.setIncidentState("RECIBIDO");
                incident.setClient(cliente);
                incident.setOfferedService(servicio);
                incident.setSpecialistAssigned(especialista);
                entityManager.persist(incident);
                entityManager.getTransaction().commit();

                System.out.println("Alta exitosa");
            }

        } catch (Exception e) {
            System.out.println(e);
            altaIncidente();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
            ModuloMesaDeAyuda.moduloMesaDeAyuda();
        }
    }




    public static void verIncidentes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            System.out.println("**************");
            System.out.println("Incidentes");
            System.out.println("**************");

            String jpql = "SELECT i FROM Incident i";
            TypedQuery<Incident> query = entityManager.createQuery(jpql, Incident.class);
            List<Incident> incidents = query.getResultList();

            // Mostrar la información
            for (Incident incidente : incidents) {
                System.out.println("--------------------");
                System.out.println("Id: " + incidente.getIdIncident());

                // Consulta JPQL para obtener el Cliente asociado al Incidente
                String jpql2 = "SELECT c FROM Client c WHERE c.idClient = :clientId";
                TypedQuery<Client> query2 = entityManager.createQuery(jpql2, Client.class);
                query2.setParameter("clientId", incidente.getClient().getIdClient()); // Utiliza el ID del Cliente

                // Obtener el Cliente asociado al Incidente
                Client cliente = query2.getSingleResult();

                System.out.println("Id Cliente: " + cliente.getIdClient() + " -- Razon social: " + cliente.getBusinessName());

                // Consulta JPQL para obtener el Servicio asociado al Incidente
                String jpql3 = "SELECT i FROM OfferedService i WHERE i.idOfferedService = :offeredServiceId";
                TypedQuery<OfferedService> query3 = entityManager.createQuery(jpql3, OfferedService.class);
                query3.setParameter("offeredServiceId", incidente.getOfferedService().getIdOfferedService());

                // Obtener el Servicio  asociado al Incidente
                OfferedService servicio = query3.getSingleResult();

                System.out.println("Servicio: " + servicio.getOfferedServiceName());
                System.out.println("Tipo de problema: " + incidente.getTypeProblem());
                System.out.println("Descripción del problema: " + incidente.getProblemDescription());
                System.out.println("Horas de resolución: " + incidente.getHoursToResolveIssue());
                System.out.println("Estado: " + incidente.getIncidentState());

                // Consulta JPQL para obtener el Servicio asociado al Incidente
                String jpql4 = "SELECT j FROM SpecialistUser j WHERE j.idUser = :userId";
                TypedQuery<SpecialistUser> query4 = entityManager.createQuery(jpql4, SpecialistUser.class);
                query4.setParameter("userId", incidente.getSpecialistAssigned().getIdUser());

                // Obtener el Servicio  asociado al Incidente
                SpecialistUser specialistUser = query4.getSingleResult();
                System.out.println("Especialista asignado: " + specialistUser.getUserName());
                System.out.println("--------------------");
            }

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

        ModuloMesaDeAyuda.moduloMesaDeAyuda();
    }


    public static void validarUserMesaDeAyuda() {
        ModuloMesaDeAyuda.moduloMesaDeAyuda();
    }
}
