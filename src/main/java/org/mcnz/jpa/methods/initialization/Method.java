package org.mcnz.jpa.methods.initialization;

import jakarta.persistence.*;
import org.mcnz.jpa.models.Client;
import org.mcnz.jpa.models.Incident;
import org.mcnz.jpa.models.OfferedService;
import org.mcnz.jpa.models.users.ComercialUser;
import org.mcnz.jpa.models.users.HelpDeskUser;
import org.mcnz.jpa.models.users.RrhhUser;
import org.mcnz.jpa.models.users.SpecialistUser;


import javax.swing.plaf.metal.MetalIconFactory;
import java.util.ArrayList;
import java.util.List;


public class Method {


    public static void createOfferedService(String offeredServiceName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        OfferedService offSer = new OfferedService();
        offSer.setOfferedServiceName(offeredServiceName);
        entityManager.persist(offSer);
        entityManager.getTransaction().commit();
    }

    public static void createClient(String bussinessName, String cuit, String mail) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Client client = new Client();
        client.setBusinessName(bussinessName);
        client.setCuit(cuit);
        client.setMail(mail);
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    public static void createComercialUser(String userName, String name, String lastName, String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        ComercialUser user = new ComercialUser();
        user.setUserName(userName);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAlta(true);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public static void createRrhhUser(String userName, String name, String lastName, String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        RrhhUser user = new RrhhUser();
        user.setUserName(userName);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAlta(true);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public static void createSpecialistUser(String userName, String name, String lastName, String email, List<Long> offServId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT os FROM OfferedService os";
            TypedQuery<OfferedService> query = entityManager.createQuery(jpql, OfferedService.class);

            List<OfferedService> offServices = query.getResultList();


            // Verificar si el cliente existe
            if (offServices == null) {
                System.out.println("El cliente con ID " + offServId + " no existe.");
                return; // O manejar según sea necesario
            }


            SpecialistUser user = new SpecialistUser();
            user.setUserName(userName);
            user.setName(name);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setOfferedServices(offServices);


            // Paso 5: Persistir el nuevo incidente en la base de datos
            entityManager.persist(user);

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
    }

    public static void createHelpDeskUser(String userName, String name, String lastName, String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        HelpDeskUser user = new HelpDeskUser();
        user.setUserName(userName);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAlta(true);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public static void createIncident(Long clientId, String typeProblem, String problemDescription, int hoursToResolve, Long offServId, Long specialistUserId, String state) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Paso 1: Obtener el cliente existente por su ID
            Client client = entityManager.find(Client.class, clientId);

            // Verificar si el cliente existe
            if (client == null) {
                System.out.println("El cliente con ID " + clientId + " no existe.");
                return; // O manejar según sea necesario
            }

            // Paso 2: Obtener el servicio existente por su ID
            OfferedService offServ = entityManager.find(OfferedService.class, offServId);

            // Verificar si el servicio existe
            if (offServ == null) {
                System.out.println("El servicio con ID " + offServId + " no existe.");
                return; // O manejar según sea necesario
            }

            // Paso 3: Obtener el usuario especialista existente por su ID
            SpecialistUser specUser = entityManager.find(SpecialistUser.class, specialistUserId);

            // Verificar si el usuario especialista existe
            if (specUser == null) {
                System.out.println("El usuario especialista con ID " + specialistUserId + " no existe.");
                return; // O manejar según sea necesario
            }

            // Paso 4: Crear un nuevo incidente y asignar referencias a entidades existentes
            Incident incidente = new Incident();
            incidente.setClient(client);
            //incidente.setTypeProblem(typeProblem);
            //incidente.setProblemDescription(problemDescription);
            //incidente.setHoursToResolveIssue(hoursToResolve);
            incidente.setOfferedService(offServ);
            incidente.setSpecialistAssigned(specUser);
            incidente.setIncidentState(state);

            // Paso 5: Persistir el nuevo incidente en la base de datos
            entityManager.persist(incidente);

            entityManager.getTransaction().commit();

            System.out.println("Incidente creado exitosamente con ID: " + incidente.getIdIncident());
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
    }








    /*
    public static UserRol getUserRolById(Long userRolId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(UserRol.class, userRolId);
    }

    public static SpecialistState getStateSpecialistById(Long specialistStateId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(SpecialistState.class, specialistStateId);
    }

    public static List<OfferedService> getOfferedServiceById(Long offeredServiceId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        OfferedService offeredService = entityManager.find(OfferedService.class, offeredServiceId);

        List<OfferedService> offeredServices = new ArrayList<>();
        if (offeredServices != null) {
            offeredServices.add(offeredService);
        }

        return offeredServices;
    }
    public static void findRolById(Long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        UserRol userRol = entityManager.find(UserRol.class, id);
        System.out.println(userRol.getUserRolName());
        entityManager.persist(userRol);
        entityManager.getTransaction().commit();
    }

    public static void createRol(String rolName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        UserRol rol =  new UserRol();
        rol.setUserRolName(rolName);
        entityManager.persist(rol);
        entityManager.getTransaction().commit();
    }



    public static void createIncidentState (String incidentStateName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        IncidentState inc =  new IncidentState();
        inc.setIncidentStateName(incidentStateName);
        entityManager.persist(inc);
        entityManager.getTransaction().commit();
    }



    public static void createRrhhUser(RrhhUser rrhhUser) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(rrhhUser);
        entityManager.getTransaction().commit();
    }

    public static void createComercialUser(ComercialUser comercialUser) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(comercialUser);
        entityManager.getTransaction().commit();
    }

    public static void createHelpDesklUser(HelpDeskUser helpDeskUser) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(helpDeskUser);
        entityManager.getTransaction().commit();
    }

    public static void createSpecialistUser(SpecialistUser specialistUser) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(specialistUser);
        entityManager.getTransaction().commit();
    }

    public static void createClient(Client client) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    public static void createSpecialistState (String specialistState) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        SpecialistState specState =  new SpecialistState();
        specState.setSpecialistStateName(specialistState);
        specState.setIdSpecialistUser(null);
        entityManager.persist(specState);
        entityManager.getTransaction().commit();
    }


    public static void findComercialUserByUserName(String userName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        // Utilizar una consulta JPQL para buscar UserRol por nombre
        String jpql = "SELECT cu FROM ComercialUser cu WHERE cu.userName = :userName";
        TypedQuery<ComercialUser> query = entityManager.createQuery(jpql, ComercialUser.class);
        query.setParameter("userName", userName);

        try {
            ComercialUser comercialUser = query.getSingleResult();
            System.out.println(comercialUser.getIdUserRol());
        } catch (NoResultException e) {
            System.out.println("**********************************************************");
            System.out.println("No se encontró ningún UserRol con el nombre proporcionado.");
            System.out.println("**********************************************************");
        }

        entityManager.getTransaction().commit();
    }

    public static void validateUserPass(String usuario,String contraseña){
        if(usuario == usuario && contraseña == contraseña){
            System.out.println("Coloque el número de la opción deseada");
            System.out.println("1. Crear/modificar clientes");
            System.out.println("2. Crear incidente");
            System.out.println("3. Dar de alta/modificar personal de la empresa");
            System.out.println("4. Ver mis incidentes");
        }

    };

    public static void menuByUserRol(String userRol) {

        switch (userRol) {
            case "RRHH":
                System.out.println("Bienvenido al modulo de RRHH");
                break;
            case "Comercial":
                System.out.println("Bienvenido al modulo Comercial");
                break;
            case "HelpDesk":
                System.out.println("SBienvenido al modulo de Mesa de Ayuda");
                break;
            case "Specialist":
                System.out.println("Bienvenido al modulo de Especialistas");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public static RrhhUser getRrhhUserRoleByCredentials(String username, String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        try {
            TypedQuery<RrhhUser> query = entityManager.createQuery(
                    "SELECT u FROM RrhhUser u WHERE u.userName = :username AND u.password = :password",
                    RrhhUser.class);

            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.getSingleResult();
        } catch (NoResultException e) {
            // Usuario o contraseña incorrectos
            return null;
        }
    }



    private static void moduloRRHH(String opcion) {
        System.out.println("Seleccione una opcion");

        switch (opcion) {
            case "1":
                System.out.println("1. Ver personal de la empresa");
                Method.findAllUser();
                break;
            case "2":
                System.out.println("2. Dar de alta nuevo personal");

                break;
        }
    }

    private static void moduloEspecialista(String opcion) {
        System.out.println("Seleccione una opcion");

        switch (opcion) {
            case "1":
                System.out.println("1. Ver mis incidencias asignadas");
                break;
            case "2":
                System.out.println("2. Ver mis incidencias resueltas");
                break;

        }
    }

    private static void moduloMesaDeAyuda() {
        System.out.println("Seleccione una opcion");
        System.out.println("1. Ver incidencias");
        System.out.println("2. Cargar nueva incidencia");
    };

    private static void moduloComercial() {
        System.out.println("Seleccione una opcion");
        System.out.println("1. Ver clientes");
        System.out.println("2. Dar de alta nuevo cliente");
    };
    private static void findAllUser() {
        System.out.println("Estos son todos los usuarios");
    }

     */








}




