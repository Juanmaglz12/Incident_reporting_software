package org.mcnz.jpa.methods;

import jakarta.persistence.*;
import org.mcnz.jpa.models.Client;
import org.mcnz.jpa.models.Incident;
import org.mcnz.jpa.models.OfferedService;
import org.mcnz.jpa.models.ProblemType;
import org.mcnz.jpa.models.users.SpecialistUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ModuloMesaDeAyuda {

    public static void validarUserMesaDeAyuda() {
        ModuloMesaDeAyuda.moduloMesaDeAyuda();
    }

    public static void moduloMesaDeAyuda() {
        System.out.println("Elija una opción");
        System.out.println("1. Ver incidentes");
        System.out.println("2. Alta incidente");
        System.out.println("3. Volver");
        System.out.println("4. Salir");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1" -> verIncidentes();
            case "2" -> altaIncidente();
            case "3" -> MenuMethods.menu();
            case "4" -> {
            }
            default -> {
                System.out.println("Opción no válida");
                moduloMesaDeAyuda();
            }
        }
    }

    private static void verIncidentes(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese una opción");
        System.out.println("1. Ver todos los incidentes");
        System.out.println("2. Ver incidentes recibidos");
        System.out.println("3. Ver incidentes resueltos");
        System.out.println("4. Volver");

        String opcion = sc.nextLine();
        switch (opcion) {
            case "1" -> verTodosLosIncidentes();
            case "2" -> verIncidentesRecibidos();
            case "3" -> verIncidentesSolucionados();
            case "4" -> moduloMesaDeAyuda();
            default -> {
                System.out.println("Opción no válida");
                moduloMesaDeAyuda();
            }
        }
        moduloMesaDeAyuda();
    }

    private static void verTodosLosIncidentes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Incidentes");
        System.out.println("**************");

        try{
            String jpql = "SELECT i FROM Incident i";
            TypedQuery<Incident> query = entityManager.createQuery(jpql, Incident.class);
            List<Incident> incidents = query.getResultList();

            for (Incident incident : incidents) {
                System.out.println("--------------------");
                System.out.println("Id: " + incident.getClient().getIdClient());
                System.out.println("Cliente : " + incident.getClient().getBusinessName());
                System.out.println("Id Cliente: " + incident.getIdIncident());
                System.out.println("Tipo de problema: " + incident.getTypeProblem());
                System.out.println("Descripcion del problema: " + incident.getDescriptionProblem());
                System.out.println("Servicio: " + incident.getOfferedService().getOfferedServiceName());
                System.out.println("Estado del incidente: " + incident.getIncidentState());
                System.out.println("Fecha del incidente: " + incident.getIncidentDate());
                System.out.println("Fecha de resolución: " + incident.getResolutionDate());
                System.out.println("Especialista asignado: " + incident.getSpecialistAssigned().getUserName());
                System.out.println(incident.getSpecialistAssigned().getName());
                System.out.println(incident.getSpecialistAssigned().getLastName());
                System.out.println("--------------------");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            emf.close();
        }
    }

    private static void verIncidentesSolucionados() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Incidentes solucionados");
        System.out.println("**************");

        try {
            String jpql = "SELECT i FROM Incident i WEHERE incidentState = SOLUCIONADOS";
            TypedQuery<Incident> query = entityManager.createQuery(jpql, Incident.class);
            List<Incident> incidents = query.getResultList();

            for (Incident incident : incidents) {
                System.out.println("--------------------");
                System.out.println("Id: " + incident.getClient().getIdClient());
                System.out.println("Cliente : " + incident.getClient().getBusinessName());
                System.out.println("Id Cliente: " + incident.getIdIncident());
                System.out.println("Tipo de problema: " + incident.getTypeProblem());
                System.out.println("Descripcion del problema: " + incident.getDescriptionProblem());
                System.out.println("Servicio: " + incident.getOfferedService().getOfferedServiceName());
                System.out.println("Estado del incidente: " + incident.getIncidentState());
                System.out.println("Fecha del incidente: " + incident.getIncidentDate());
                System.out.println("Fecha de resolución: " + incident.getResolutionDate());
                System.out.println("Especialista asignado: " + incident.getSpecialistAssigned().getUserName());
                System.out.println(incident.getSpecialistAssigned().getName());
                System.out.println(incident.getSpecialistAssigned().getLastName());
                System.out.println("--------------------");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            emf.close();
        }
        moduloMesaDeAyuda();
    }



    public static void verIncidentesRecibidos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Incidentes recibidos");
        System.out.println("**************");

        try {
            String jpql = "SELECT i FROM Incident i WEHERE incidentState = RECIBIDO";
            TypedQuery<Incident> query = entityManager.createQuery(jpql, Incident.class);
            List<Incident> incidents = query.getResultList();

            for (Incident incident : incidents) {
                System.out.println("--------------------");
                System.out.println("Id: " + incident.getClient().getIdClient());
                System.out.println("Cliente : " + incident.getClient().getBusinessName());
                System.out.println("Id Cliente: " + incident.getIdIncident());
                System.out.println("Tipo de problema: " + incident.getTypeProblem());
                System.out.println("Descripcion del problema: " + incident.getDescriptionProblem());
                System.out.println("Servicio: " + incident.getOfferedService().getOfferedServiceName());
                System.out.println("Estado del incidente: " + incident.getIncidentState());
                System.out.println("Fecha del incidente: " + incident.getIncidentDate());
                System.out.println("Fecha de resolución: " + incident.getResolutionDate());
                System.out.println("Especialista asignado: " + incident.getSpecialistAssigned().getUserName());
                System.out.println(incident.getSpecialistAssigned().getName());
                System.out.println(incident.getSpecialistAssigned().getLastName());
                System.out.println("--------------------");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            emf.close();
        }
        moduloMesaDeAyuda();
    }

    public static void altaIncidente() {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

       //CLIENTE
        Client clienteIncidente = new Client();
        clienteIncidente = buscarClientePorId();

        //SERVICIO
        OfferedService servicioIncidente = new OfferedService();
        servicioIncidente = elegirServicioDelCliente(clienteIncidente);

        //DESCRIPCION PROBLEMA
        System.out.println("Ingrese la descripcion del problema");
        String descrProb = sc.nextLine();

        //TIPO PROBLEMA
        ProblemType tipoProblema = new ProblemType();
        tipoProblema = elegirTipoProblema(servicioIncidente);

        // ESPECIALISTA
        SpecialistUser especialista = new SpecialistUser();
        especialista = asignarEspecialista(servicioIncidente);

        Incident incident = new Incident();
        incident.setClient(clienteIncidente);
        incident.setOfferedService(servicioIncidente);
        incident.setDescriptionProblem(descrProb);
        incident.setTypeProblem(tipoProblema);
        incident.setSpecialistAssigned(especialista);
        incident.setIncidentDate(LocalDate.now());
        incident.setIncidentState("RECIBIDO");
        incident.setResolutionDate(LocalDate.now().plusDays(tipoProblema.getDaysToResolution()));
        entityManager.persist(incident);
        entityManager.getTransaction().commit();

        System.out.println("Alta exitosa bajo el id "+incident.getIdIncident());

        ModuloMesaDeAyuda.moduloMesaDeAyuda();


/*
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

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
            ModuloMesaDeAyuda.moduloMesaDeAyuda();
            sc.close();


        }*/

        //entityManager.close();
        //emf.close();
        //sc.close();

        //validarServicioContratadoPorCliente();


        //NO ANDA EL ALTA DE INCIDDENTE

    }

    private static ProblemType elegirTipoProblema(OfferedService servicioIncidente) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        ProblemType problemType = null;
        Scanner sc = new Scanner(System.in);
        List<ProblemType> problemasPorServicio = new ArrayList<>();

        try {
            String jpql = "SELECT p FROM ProblemType p WHERE service = :servicioIncidente";
            TypedQuery<ProblemType> query = entityManager.createQuery(jpql, ProblemType.class);
            query.setParameter("servicioIncidente", servicioIncidente);
            List<ProblemType> problemTypes = query.getResultList();
            int contador = 1;
            for (ProblemType problemaTipo : problemTypes) {
                System.out.println("---------"+contador+"-----------");
                System.out.println(problemaTipo.getProblemTypeName());
                System.out.println(" ");
                contador ++;
            }
            problemasPorServicio = problemTypes;
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
        }


        System.out.print("Seleccione un tipo de problema (ingrese el número): ");
        int opcion = sc.nextInt();


        if (opcion > 0 && opcion < problemasPorServicio.size()) {
            problemType = problemasPorServicio.get(opcion - 1);
        } else {
            System.out.println("Opción fuera de rango. Por favor, ingrese un número válido.");
        }
       System.out.println("Elegiste " + problemType.getProblemTypeName());

        return problemType;
    }

    public static Client buscarClientePorId(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        Client cliente = null;
        String cuit = null;

        try {
            entityManager.getTransaction().begin();
            System.out.println("Ingrese el cuit del cliente o presione 1 para cancelar");
            cuit = sc.nextLine();
            // QUERY BUSQUEDA POR CUIT CLIENTE

            if(!cuit.equals("1")) {
                String jpqlCliente = "SELECT c FROM Client c LEFT JOIN FETCH c.contractedOfferedServices cs WHERE c.cuit = :cuit";
                TypedQuery<Client> queryCliente = entityManager.createQuery(jpqlCliente, Client.class);
                queryCliente.setParameter("cuit", cuit);

                cliente = queryCliente.getSingleResult();

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
            }else ModuloMesaDeAyuda.moduloMesaDeAyuda();

        } catch (Exception e) {
            System.out.println("No se encontró el cliente bajo el cuit "+cuit);
            ModuloMesaDeAyuda.altaIncidente();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return cliente;
    }

    private static OfferedService elegirServicioDelCliente(Client clienteIncidente) {
        Scanner sc = new Scanner(System.in);
        List<OfferedService> contractedServices = clienteIncidente.getContractedOfferedServices();
        int contador = 1;
        for (OfferedService ofServ : contractedServices) {
            System.out.println(contador + ". " +ofServ.getOfferedServiceName());
            contador ++;

        }
        OfferedService servicioElegido = new OfferedService();

        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        try {
            System.out.print("Seleccione un servicio (ingrese el número): ");
            opcion = scanner.nextInt();

            // Verificar si el número está en el rango válido
            if (opcion >= 1 && opcion <= clienteIncidente.getContractedOfferedServices().size()) {
                servicioElegido = clienteIncidente.getContractedOfferedServices().get(opcion - 1);
            } else {
                System.out.println("Opción fuera de rango. Por favor, ingrese un número válido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ingrese un número válido.");
            scanner.next(); // Consumir la entrada no válida para evitar un bucle infinito
        }

        servicioElegido = clienteIncidente.getContractedOfferedServices().get(opcion-1);

        System.out.println("Elegiste " + servicioElegido.getOfferedServiceName());

        return servicioElegido;
    }

    private static SpecialistUser asignarEspecialista(OfferedService servicioDeIncidente){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);
        SpecialistUser especialistaElegido = new SpecialistUser();
        List<SpecialistUser> especialistasPorEspecialidad = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();
            System.out.println("Buscando especialistas que sepan " + servicioDeIncidente.getOfferedServiceName() + "....");
            String jpql = "SELECT DISTINCT s FROM SpecialistUser s JOIN s.offeredServices o WHERE o.offeredServiceName = :nombreEspecialidad";
            TypedQuery<SpecialistUser> query = entityManager.createQuery(jpql, SpecialistUser.class);
            query.setParameter("nombreEspecialidad", servicioDeIncidente.getOfferedServiceName());

            especialistasPorEspecialidad = query.getResultList();
            int contador = 1;
            for (SpecialistUser especialista : especialistasPorEspecialidad) {
                System.out.println("--------- Especialista "+ contador +"-----------");
                System.out.println("Id: " + especialista.getIdUser());
                System.out.println("Nombre: " + especialista.getName());
                System.out.println("Apellido: " + especialista.getLastName());
                System.out.println("UserName: " + especialista.getUserName());
                System.out.println("Email: " + especialista.getEmail());
                List<OfferedService> especialidades = especialista.getOfferedServices();
                System.out.println("Especialidades");
                for (OfferedService serOf : especialidades){
                    System.out.println(serOf.getOfferedServiceName());
                }
                contador ++;
            }

            System.out.println("Ingresá el número del un especialista de la lista");

            int opcion = sc.nextInt();

            // Verifica si la opción es válida
            if (opcion >= 1 && opcion <= especialistasPorEspecialidad.size()) {
                especialistaElegido = especialistasPorEspecialidad.get(opcion - 1);
                System.out.println("Elegiste a " + especialistaElegido.getLastName() + ", " + especialistaElegido.getName());
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }


        } catch (Exception e) {
            System.out.println(e);

        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            };
        }
        return especialistaElegido;
    }
}
