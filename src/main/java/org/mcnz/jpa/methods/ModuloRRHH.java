package org.mcnz.jpa.methods;

import jakarta.persistence.*;
import org.mcnz.jpa.methods.validadorEntradas.ValidadorEntradas;
import org.mcnz.jpa.models.Incident;
import org.mcnz.jpa.models.OfferedService;
import org.mcnz.jpa.models.users.*;

import java.time.LocalDate;
import java.util.*;

public class ModuloRRHH {


    public static void moduloRRHH() {
        System.out.println("Elija una opcion");
        System.out.println("1. Ver empleados");
        System.out.println("2. Alta empleado");
        System.out.println("3. Modificar empleado");
        System.out.println("4. Baja empleados");
        System.out.println("5. Reporte de desempeño");
        System.out.println("8. Volver");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1":
                System.out.println("Ver empleados");
                verTodosLosEmpleados();
                break;
            case "2":
                System.out.println("Alta empleado");
                altaUser();
                break;
            case "3":
                seleccionarRolAModificar();
                break;
            case "4":
                System.out.println("Baja empleado");
                bajaEmpleado();
                break;
            case "5":
                System.out.println("Reporte por desempeño");
                reportePorDesempeno();
                break;
            case "8":
                MenuMethods.menu();
                break;
            default:
                System.out.println("Opción no válida");
                ModuloRRHH.moduloRRHH();
        }
    }

    private static void reportePorDesempeno() {
        System.out.println("1. Reporte por especialidad y fecha");
        System.out.println("2. Reporte por fecha");
        System.out.println("3. Volver");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1" -> reportePorFechaServicio();
            case "2" -> reportePorFecha();
            case "3" -> moduloRRHH();
            default -> {
                System.out.println("Opción no válida");
                reportePorDesempeno();
            }
        }
    }
    private static void reportePorFecha(){
        Scanner sc = new Scanner (System.in);

        System.out.println("Ingrese la fecha de inicio");
        System.out.println("Día (dd)");
        int diaInicio = sc.nextInt();
        System.out.println("Mes (mm)");
        int mesInicio = sc.nextInt();
        System.out.println("Año (aaaa)");
        int anoInicio = sc.nextInt();
        System.out.println("Ingrese la fecha de fin");
        System.out.println("Día (dd)");
        int diaFin = sc.nextInt();
        System.out.println("Mes (mm)");
        int mesFin = sc.nextInt();
        System.out.println("Año (aaaa)");
        int anoFin = sc.nextInt();
        Map<String, Integer> frecuenciaEspecialistas = new HashMap<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        LocalDate fechaInicio = LocalDate.of(anoInicio, mesInicio, diaInicio);
        LocalDate fechaFin = LocalDate.of(anoFin, mesFin, diaFin);
        System.out.println(fechaInicio);
        System.out.println(fechaFin);

        try {
            String jpql = "SELECT c FROM Incident c LEFT JOIN FETCH c.offeredService os WHERE c.incidentState = 'RESUELTO' AND c.resolutionDate BETWEEN :fechaInicio AND :fechaFin";
            TypedQuery<Incident> query = entityManager.createQuery(jpql, Incident.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);

            List<Incident> incidentes = query.getResultList();

            for (Incident incidente : incidentes) {
                String nombreEspecialista = incidente.getSpecialistAssigned().getUserName();

                Integer frecuenciaActual = frecuenciaEspecialistas.getOrDefault(nombreEspecialista, 0);

                frecuenciaEspecialistas.put(nombreEspecialista, frecuenciaActual + 1);
            }

            for (Map.Entry<String, Integer> entry : frecuenciaEspecialistas.entrySet()) {
                System.out.println("Especialista: " + entry.getKey() + ", Incidentes resueltos: " + entry.getValue());
            }

        } catch (Exception e) {
            System.out.println(e);
            reportePorDesempeno();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            emf.close();
        }
        moduloRRHH();
    }

    private static void reportePorFechaServicio(){
        Scanner sc = new Scanner (System.in);

        System.out.println("Ingrese una especialidad");
        System.out.println("1. SAP");
        System.out.println("2. Tango");
        System.out.println("3. Windows");
        System.out.println("4. MacOs");
        System.out.println("5. Linux Ubuntu");
        System.out.println("6. Todas");

        String especialidad = sc.nextLine();

        switch (especialidad) {
            case "1" -> especialidad = "SAP";
            case "2" -> especialidad = "Tango";
            case "3" -> especialidad = "Windows";
            case "4" -> especialidad = "MacOs";
            case "5" -> especialidad = "Linux Ubuntu";
            case "6" -> especialidad = "";
            default -> {
                System.out.println("Opción no válida");
                reportePorDesempeno();
            }
        }
        System.out.println("Ingrese la fecha de inicio");
        System.out.println("Día (dd)");
        int diaInicio = sc.nextInt();
        System.out.println("Mes (mm)");
        int mesInicio = sc.nextInt();
        System.out.println("Año (aaaa)");
        int anoInicio = sc.nextInt();
        System.out.println("Ingrese la fecha de fin");
        System.out.println("Día (dd)");
        int diaFin = sc.nextInt();
        System.out.println("Mes (mm)");
        int mesFin = sc.nextInt();
        System.out.println("Año (aaaa)");
        int anoFin = sc.nextInt();
        Map<String, Integer> frecuenciaEspecialistas = new HashMap<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        LocalDate fechaInicio = LocalDate.of(anoInicio, mesInicio, diaInicio);
        LocalDate fechaFin = LocalDate.of(anoFin, mesFin, diaFin);
        System.out.println(fechaInicio);
        System.out.println(fechaFin);
//            String jpqlCliente = "SELECT c FROM Client c LEFT JOIN FETCH c.contractedOfferedServices cs WHERE c.cuit = :cuit";
//            String jpql = "SELECT DISTINCT c FROM Client c LEFT JOIN FETCH c.contractedOfferedServices";
        try {
            String jpql = "SELECT c FROM Incident c LEFT JOIN FETCH c.offeredService os WHERE c.incidentState = 'RESUELTO' AND c.resolutionDate BETWEEN :fechaInicio AND :fechaFin AND os.offeredServiceName = :especialidad";
            TypedQuery<Incident> query = entityManager.createQuery(jpql, Incident.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            query.setParameter("especialidad",especialidad);

            List<Incident> incidentes = query.getResultList();

            for (Incident incidente : incidentes) {
                String nombreEspecialista = incidente.getSpecialistAssigned().getUserName();

                Integer frecuenciaActual = frecuenciaEspecialistas.getOrDefault(nombreEspecialista, 0);

                frecuenciaEspecialistas.put(nombreEspecialista, frecuenciaActual + 1);
            }

            for (Map.Entry<String, Integer> entry : frecuenciaEspecialistas.entrySet()) {
                System.out.println("Especialista: " + entry.getKey() + ", Incidentes resueltos: " + entry.getValue());
            }

        } catch (Exception e) {
            System.out.println(e);
            reportePorDesempeno();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            emf.close();
        }
        moduloRRHH();
    }

    private static void bajaEmpleado() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Modificar empleado");
        System.out.println("Seleccione el rol del empleado");
        System.out.println("1. RRHH");
        System.out.println("2. Comercial");
        System.out.println("3. Mesa de ayuda");
        System.out.println("4. Especialista");
        System.out.println("5. Volver");

        String opcion = sc.next();

        switch (opcion) {
            case "1" -> {
                bajaEmpleadoRRHH();
            }
            case "2" -> {
                bajaEmpleadoComercial();
            }
            case "3" -> {
                bajaEmpleadoMesaDeAyuda();
            }
            case "4" -> {
                bajaEmpleadoEspecialista();
            }
            case "5" -> moduloRRHH();
            default -> {
                System.out.println("Opción no válida");
                seleccionarRolAModificar();
            }
        }

    }

    private static void seleccionarRolAModificar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Modificar empleado");
        System.out.println("Seleccione el rol del empleado");
        System.out.println("1. RRHH");
        System.out.println("2. Comercial");
        System.out.println("3. Mesa de ayuda");
        System.out.println("4. Especialista");
        System.out.println("5. Volver");

        String opcion = sc.next();

        switch (opcion) {
            case "1" -> {
                modificarEmpleadoRrhh();
            }
            case "2" -> {
                modificarEmpleadoComercial();
            }
            case "3" -> {
                modificarEmpleadoMesaDeAyuda();
            }
            case "4" -> {
                modificarEmpleadoEspecialista();
            }
            case "5" -> moduloRRHH();
            default -> {
                System.out.println("Opción no válida");
                seleccionarRolAModificar();
            }
    }

}

    private static void modificarEmpleadoComercial() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Ingrese el username del empleado comercial a modificar");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT c FROM ComercialUser c WHERE c.userName = :userName";
            TypedQuery<ComercialUser> query = entityManager.createQuery(jpql, ComercialUser.class);
            query.setParameter("userName", userName);

            ComercialUser user = query.getSingleResult();

            System.out.println("--------------------");
            System.out.println("Id: " + user.getIdUser());
            System.out.println("Nombre: " + user.getName());
            System.out.println("Apellido: " + user.getUserName());
            System.out.println("Nombre de usuario: " + user.getUserName());
            System.out.println("email: " + user.getEmail());
            System.out.println("Estado: " + user.getAlta());
            System.out.println("--------------------");

            System.out.println("Ingrese el dato a modificar");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. UserName");
            System.out.println("4. email");
            System.out.println("5. Reactivar usuario");
            System.out.println("6. Volver");

            sc.nextLine();

            String opcionDatoAModificar = sc.nextLine();

            switch (opcionDatoAModificar) {
                case "1" -> {
                    System.out.println("Ingrese el nuevo nombre");
                    user.setName(ValidadorEntradas.validarEntrada());
                }
                case "2" -> {
                    System.out.println("Ingrese el nuevo apellido");
                    user.setLastName(ValidadorEntradas.validarEntrada());
                }
                case "3" -> {
                    System.out.println("Ingrese el nuevo userName");
                    user.setUserName(ValidadorEntradas.validarEntrada());
                }
                case "4" -> {
                    System.out.println("Ingrese el nuevo email");
                    user.setEmail(ValidadorEntradas.validarEntrada());
                }
                case "5" -> {
                    if(!user.getAlta()){
                        user.setAlta(true);
                        System.out.println("El empleado se reactivó correctamente");
                    }else System.out.println("El emplado está actualmente activo");
                }
                case "6" -> ModuloRRHH.moduloRRHH();
                default -> {
                    System.out.println("opcion no válida");
                    modificarEmpleadoRrhh();
                }
            }

            // Realizar la actualización
            entityManager.getTransaction().commit();
            System.out.println("Cliente actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println(e);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
        }
        moduloRRHH();
    }

    private static void altaUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del nuevo usuario");
        String nombre = ValidadorEntradas.validarEntrada();

        System.out.println("Ingrese el apellido del nuevo usuario");
        String apellido = ValidadorEntradas.validarEntrada();

        System.out.println("Ingrese el username del nuevo usuario ");
        String userName = ValidadorEntradas.validarEntrada();

        System.out.println("Ingrese el email del nuevo usuario");
        String email = ValidadorEntradas.validarEntrada();

        asignarRol(nombre, apellido, userName, email);

    }

    private static void modificarEmpleadoEspecialista() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Ingrese el username del empleado Especialista a modificar");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT c FROM SpecialistUser c WHERE c.userName = :userName";
            TypedQuery<SpecialistUser> query = entityManager.createQuery(jpql, SpecialistUser.class);
            query.setParameter("userName", userName);

            SpecialistUser user = query.getSingleResult();

            System.out.println("--------------------");
            System.out.println("Id: " + user.getIdUser());
            System.out.println("Nombre: " + user.getName());
            System.out.println("Apellido: " + user.getUserName());
            System.out.println("Nombre de usuario: " + user.getUserName());
            System.out.println("email: " + user.getEmail());
            System.out.println("Estado: " + user.getAlta());
            System.out.println("Especialidades");
            List<OfferedService> ofServices = user.getOfferedServices();
            for(OfferedService ofService : ofServices){
                System.out.println(ofService.getOfferedServiceName());
            }
            System.out.println("--------------------");

            System.out.println("Ingrese el dato a modificar");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. UserName");
            System.out.println("4. email");
            System.out.println("5. Reactivar usuario");
            System.out.println("6. Volver");

            sc.nextLine();

            String opcionDatoAModificar = sc.nextLine();

            switch (opcionDatoAModificar) {
                case "1" -> {
                    System.out.println("Ingrese el nuevo nombre");
                    user.setName(ValidadorEntradas.validarEntrada());
                }
                case "2" -> {
                    System.out.println("Ingrese el nuevo apellido");
                    user.setLastName(ValidadorEntradas.validarEntrada());
                }
                case "3" -> {
                    System.out.println("Ingrese el nuevo userName");
                    user.setUserName(ValidadorEntradas.validarEntrada());
                }
                case "4" -> {
                    System.out.println("Ingrese el nuevo email");
                    user.setEmail(ValidadorEntradas.validarEntrada());
                }
                case "5" -> {
                    if(!user.getAlta()){
                        user.setAlta(true);
                        System.out.println("El empleado se reactivó correctamente");
                    }else System.out.println("El emplado está actualmente activo");
                }
                case "6" -> ModuloRRHH.moduloRRHH();
                default -> {
                    System.out.println("opcion no válida");
                    modificarEmpleadoRrhh();
                }
            }

            // Realizar la actualización
            entityManager.getTransaction().commit();
            System.out.println("Cliente actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println("No se encontró el usuario");
            modificarEmpleadoEspecialista();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
        }
        moduloRRHH();
    }

    private static void modificarEmpleadoMesaDeAyuda() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Ingrese el username del empleado Mesa de ayuda a modificar");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT c FROM HelpDeskUser c WHERE c.userName = :userName";
            TypedQuery<HelpDeskUser> query = entityManager.createQuery(jpql, HelpDeskUser.class);
            query.setParameter("userName", userName);

            HelpDeskUser user = query.getSingleResult();

            System.out.println("--------------------");
            System.out.println("Id: " + user.getIdUser());
            System.out.println("Nombre: " + user.getName());
            System.out.println("Apellido: " + user.getUserName());
            System.out.println("Nombre de usuario: " + user.getUserName());
            System.out.println("email: " + user.getEmail());
            System.out.println("Estado: " + user.getAlta());
            System.out.println("--------------------");

            System.out.println("Ingrese el dato a modificar");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. UserName");
            System.out.println("4. email");
            System.out.println("5. Reactivar usuario");
            System.out.println("6. Volver");

            sc.nextLine();

            String opcionDatoAModificar = sc.nextLine();

            switch (opcionDatoAModificar) {
                case "1" -> {
                    System.out.println("Ingrese el nuevo nombre");
                    user.setName(ValidadorEntradas.validarEntrada());
                }
                case "2" -> {
                    System.out.println("Ingrese el nuevo apellido");
                    user.setLastName(ValidadorEntradas.validarEntrada());
                }
                case "3" -> {
                    System.out.println("Ingrese el nuevo userName");
                    user.setUserName(ValidadorEntradas.validarEntrada());
                }
                case "4" -> {
                    System.out.println("Ingrese el nuevo email");
                    user.setEmail(ValidadorEntradas.validarEntrada());
                }
                case "5" -> {
                    if(!user.getAlta()){
                        user.setAlta(true);
                        System.out.println("El empleado se reactivó correctamente");
                    }else System.out.println("El emplado está actualmente activo");
                }
                case "6" -> ModuloRRHH.moduloRRHH();
                default -> {
                    System.out.println("opcion no válida");
                    modificarEmpleadoRrhh();
                }
            }

            // Realizar la actualización
            entityManager.getTransaction().commit();
            System.out.println("Cliente actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println(e);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
        }
        moduloRRHH();
    }

    private static void asignarRol(String nombre, String apellido, String userName, String email) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Ingrese el numero de rol del nuevo usuario");
        System.out.println("1. RRHH");
        System.out.println("2. Comercial");
        System.out.println("3. Mesa de ayuda");
        System.out.println("4. Especialista");

        String opcion = null;

        opcion = scanner.nextLine();


        switch (opcion) {
            case "1" -> {
                RrhhUser rrhhUser = new RrhhUser();
                rrhhUser.setName(nombre.toUpperCase());
                rrhhUser.setLastName(apellido.toUpperCase());
                rrhhUser.setUserName(userName.toUpperCase());
                rrhhUser.setEmail(email.toUpperCase());
                rrhhUser.setAlta(true);
                entityManager.persist(rrhhUser);
            }
            case "2" -> {
                ComercialUser comercialUser = new ComercialUser();
                comercialUser.setName(nombre.toUpperCase());
                comercialUser.setLastName(apellido.toUpperCase());
                comercialUser.setUserName(userName.toUpperCase());
                comercialUser.setEmail(email.toUpperCase());
                comercialUser.setAlta(true);
                entityManager.persist(comercialUser);
            }
            case "3" -> {
                HelpDeskUser helpDeskUser = new HelpDeskUser();
                helpDeskUser.setName(nombre.toUpperCase());
                helpDeskUser.setLastName(apellido.toUpperCase());
                helpDeskUser.setUserName(userName.toUpperCase());
                helpDeskUser.setEmail(email.toUpperCase());
                helpDeskUser.setAlta(true);
                entityManager.persist(helpDeskUser);
            }
            case "4" -> {
                SpecialistUser specialistUser = new SpecialistUser();
                specialistUser.setName(nombre.toUpperCase());
                specialistUser.setLastName(apellido.toUpperCase());
                specialistUser.setUserName(userName.toUpperCase());
                specialistUser.setEmail(email.toUpperCase());
                specialistUser.setAlta(true);
                specialistUser.setOfferedServices(asignarEspecialidadesANuevoEspecialista());
                entityManager.persist(specialistUser);
            }
            default -> {
                System.out.println("opcion incorrecta");
                asignarRol(nombre, apellido, userName, email);
            }
        }
        entityManager.getTransaction().commit();
        moduloRRHH();
    }

    public static List<OfferedService> asignarEspecialidadesANuevoEspecialista(){
        List<OfferedService> offeredServices = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        Long opcion = null;
        entityManager.getTransaction().begin();

        try {

            do {
                System.out.println("Ingrese las especialidades que conoce y presione 0 para finalizar");

                System.out.println("1. SAP");
                System.out.println("2. Tango");
                System.out.println("3. Windows");
                System.out.println("4. MacOs");
                System.out.println("5. Linux Ubuntu");

                try{opcion = scanner.nextLong() ;

                    for (OfferedService ofSpe : offeredServices) {

                        if (opcion.equals(ofSpe.getIdOfferedService())) {
                            System.out.println("La especialidad ya fue agregada");
                        }
                    }
                    System.out.println(opcion);

                    String jpql = "SELECT os FROM OfferedService os WHERE idOfferedService = :opcion";
                    TypedQuery<OfferedService> query = entityManager.createQuery(jpql, OfferedService.class);
                    query.setParameter("opcion", opcion);
                    OfferedService result = query.getSingleResult();

                    offeredServices.add(result);
                }catch (InputMismatchException e){
                    System.out.println("Código inválido, seleccione una opción");
                    asignarEspecialidadesANuevoEspecialista();
                }
            }while(opcion != 0);

        } catch (NoResultException e) {
            if(opcion != 0){
                System.out.println("No se encontró la especialidad");
                asignarEspecialidadesANuevoEspecialista();
            }
        }

        return offeredServices;

    }

    public static void modificarEmpleadoRrhh() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Ingrese el username del empleado de rrhh a modificar");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT c FROM RrhhUser c WHERE c.userName = :userName";
            TypedQuery<RrhhUser> query = entityManager.createQuery(jpql, RrhhUser.class);
            query.setParameter("userName", userName);

            RrhhUser user = query.getSingleResult();

            System.out.println("--------------------");
            System.out.println("Id: " + user.getIdUser());
            System.out.println("Nombre: " + user.getName());
            System.out.println("Apellido: " + user.getUserName());
            System.out.println("Nombre de usuario: " + user.getUserName());
            System.out.println("email: " + user.getEmail());
            System.out.println("Estado: " + user.getAlta());
            System.out.println("--------------------");

            System.out.println("Ingrese el dato a modificar");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. UserName");
            System.out.println("4. email");
            System.out.println("5. Reactivar usuario");
            System.out.println("6. Volver");

            sc.nextLine();

            String opcionDatoAModificar = sc.nextLine();

            switch (opcionDatoAModificar) {
                case "1" -> {
                    System.out.println("Ingrese el nuevo nombre");
                    user.setName(ValidadorEntradas.validarEntrada());
                }
                case "2" -> {
                    System.out.println("Ingrese el nuevo apellido");
                    user.setLastName(ValidadorEntradas.validarEntrada());
                }
                case "3" -> {
                    System.out.println("Ingrese el nuevo userName");
                    user.setUserName(ValidadorEntradas.validarEntrada());
                }
                case "4" -> {
                    System.out.println("Ingrese el nuevo email");
                    user.setEmail(ValidadorEntradas.validarEntrada());
                }
                case "5" -> {
                    if(!user.getAlta()){
                        user.setAlta(true);
                        System.out.println("El empleado se reactivó correctamente");
                    }else System.out.println("El emplado está actualmente activo");
                }
                case "6" -> ModuloRRHH.moduloRRHH();
                default -> {
                    System.out.println("opcion no válida");
                    modificarEmpleadoRrhh();
                }
            }

            // Realizar la actualización
            entityManager.getTransaction().commit();
            System.out.println("Cliente actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println(e);
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
        }
        moduloRRHH();
    }

      public static void buscarEmpleadoPorUserName(){
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        String opcion = sc.nextLine();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT r.userName FROM RrhhUser r WHERE userName = :opcion UNION SELECT c.userName FROM ComercialUser c WHERE userName = :opcion UNION SELECT h.userName FROM HelpDeskUser h WHERE userName = :opcion UNION SELECT s.userName FROM SpecialistUser s WHERE userName = :opcion";
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("opcion", opcion);
            User user = query.getSingleResult();

            System.out.println("--------------------");
            System.out.println("Id: " + user.getIdUser());
            System.out.println("Nombre: " + user.getName());
            System.out.println("Apellido: " + user.getUserName());
            System.out.println("Nombre de usuario: " + user.getUserName());
            System.out.println("email: " + user.getEmail());
            System.out.println("Estado: " + user.getAlta());
            System.out.println("--------------------");

            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            entityManager.close();
            emf.close();
        }
    }

    public static void bajaEmpleadoRRHH() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Seleccione el userName del empleado a dar de baja o presione 1 para salir.");

        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        if (!opcion.equals("1")) {
            try {
                entityManager.getTransaction().begin();

                String jpql = "SELECT u FROM RrhhUser u WHERE userName = :userName";
                TypedQuery<RrhhUser> query = entityManager.createQuery(jpql, RrhhUser.class);
                query.setParameter("userName", opcion);
                RrhhUser resultado = query.getSingleResult();

                System.out.println("--------------------");
                System.out.println("Id: " + resultado.getIdUser());
                System.out.println("Nombre: " + resultado.getName());
                System.out.println("Apellido: " + resultado.getUserName());
                System.out.println("Nombre de usuario: " + resultado.getUserName());
                System.out.println("email: " + resultado.getEmail());
                System.out.println("Estado: " + resultado.getAlta());
                System.out.println("--------------------");

                if (resultado.getAlta()){
                    System.out.println("¿Está seguro que desea dar de baja este empleado?");
                    System.out.println("1. Si");
                    System.out.println("2. Volver");

                    String opcionDatoAModificar = sc.nextLine();

                    switch (opcionDatoAModificar) {
                        case "1" -> {
                            resultado.setAlta(false);
                            System.out.println("Empleado dado de baja exitosamente!");
                        }
                        case "2" -> {
                            ModuloRRHH.moduloRRHH();
                        }
                        default -> {
                            System.out.println("opcion no válida");
                            bajaEmpleadoRRHH();
                        }
                    }
               }
                entityManager.getTransaction().commit();
                System.out.println("El empleado ya está dado de baja");
            } catch (NoResultException e) {
                System.out.println("No se encontró el usuario");
                bajaEmpleadoRRHH();
            } finally {
                entityManager.close();
                emf.close();
            }
        } else {
            moduloRRHH();
        }
        moduloRRHH();
    }

    public static void bajaEmpleadoComercial() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Seleccione el userName del empleado a dar de baja o presione 1 para salir.");

        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        if (!opcion.equals("1")) {
            try {
                entityManager.getTransaction().begin();

                String jpql = "SELECT u FROM ComercialUser u WHERE userName = :userName";
                TypedQuery<ComercialUser> query = entityManager.createQuery(jpql, ComercialUser.class);
                query.setParameter("userName", opcion);
                ComercialUser resultado = query.getSingleResult();

                System.out.println("--------------------");
                System.out.println("Id: " + resultado.getIdUser());
                System.out.println("Nombre: " + resultado.getName());
                System.out.println("Apellido: " + resultado.getUserName());
                System.out.println("Nombre de usuario: " + resultado.getUserName());
                System.out.println("email: " + resultado.getEmail());
                System.out.println("Estado: " + resultado.getAlta());
                System.out.println("--------------------");

                if (resultado.getAlta()){
                    System.out.println("¿Está seguro que desea dar de baja este empleado?");
                    System.out.println("1. Si");
                    System.out.println("2. Volver");

                    String opcionDatoAModificar = sc.nextLine();

                    switch (opcionDatoAModificar) {
                        case "1" -> {
                            resultado.setAlta(false);
                            System.out.println("Empleado dado de baja exitosamente!");
                        }
                        case "2" -> {
                            moduloRRHH();
                        }
                        default -> {
                            System.out.println("opcion no válida");
                            bajaEmpleadoComercial();
                        }
                    }
                }
                entityManager.getTransaction().commit();
                System.out.println("El empleado ya está dado de baja");
            } catch (NoResultException e) {
                System.out.println("No se encontró el usuario");
                bajaEmpleadoComercial();;
            } finally {
                entityManager.close();
                emf.close();
            }
        } else {
            moduloRRHH();
        }
        moduloRRHH();
    }

    public static void bajaEmpleadoEspecialista() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Seleccione el userName del empleado a dar de baja o presione 1 para salir.");

        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        if (!opcion.equals("1")) {
            try {
                entityManager.getTransaction().begin();

                String jpql = "SELECT u FROM SpecialistUser u WHERE userName = :userName";
                TypedQuery<SpecialistUser> query = entityManager.createQuery(jpql, SpecialistUser.class);
                query.setParameter("userName", opcion);
                SpecialistUser resultado = query.getSingleResult();

                System.out.println("--------------------");
                System.out.println("Id: " + resultado.getIdUser());
                System.out.println("Nombre: " + resultado.getName());
                System.out.println("Apellido: " + resultado.getUserName());
                System.out.println("Nombre de usuario: " + resultado.getUserName());
                System.out.println("email: " + resultado.getEmail());
                System.out.println("Estado: " + resultado.getAlta());
                System.out.println("--------------------");

                if (resultado.getAlta()){
                    System.out.println("¿Está seguro que desea dar de baja este empleado?");
                    System.out.println("1. Si");
                    System.out.println("2. Volver");

                    String opcionDatoAModificar = sc.nextLine();

                    switch (opcionDatoAModificar) {
                        case "1" -> {
                            resultado.setAlta(false);
                            System.out.println("Empleado dado de baja exitosamente!");
                        }
                        case "2" -> {
                            moduloRRHH();
                        }
                        default -> {
                            System.out.println("opcion no válida");
                            bajaEmpleadoEspecialista();
                        }
                    }
                }
                entityManager.getTransaction().commit();
                System.out.println("El empleado ya está dado de baja");
            } catch (NoResultException e) {
                System.out.println("No se encontró el usuario");
                bajaEmpleadoEspecialista();
            } finally {
                entityManager.close();
                emf.close();
            }
        } else {
            moduloRRHH();
        }
        moduloRRHH();
    }

    public static void bajaEmpleadoMesaDeAyuda() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Seleccione el userName del empleado a dar de baja o presione 1 para salir.");

        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        if (!opcion.equals("1")) {
            try {
                entityManager.getTransaction().begin();

                String jpql = "SELECT u FROM HelpDeskUser u WHERE userName = :userName";
                TypedQuery<HelpDeskUser> query = entityManager.createQuery(jpql, HelpDeskUser.class);
                query.setParameter("userName", opcion);
                HelpDeskUser resultado = query.getSingleResult();

                System.out.println("--------------------");
                System.out.println("Id: " + resultado.getIdUser());
                System.out.println("Nombre: " + resultado.getName());
                System.out.println("Apellido: " + resultado.getUserName());
                System.out.println("Nombre de usuario: " + resultado.getUserName());
                System.out.println("email: " + resultado.getEmail());
                System.out.println("Estado: " + resultado.getAlta());
                System.out.println("--------------------");

                if (resultado.getAlta()){
                    System.out.println("¿Está seguro que desea dar de baja este empleado?");
                    System.out.println("1. Si");
                    System.out.println("2. Volver");

                    String opcionDatoAModificar = sc.nextLine();

                    switch (opcionDatoAModificar) {
                        case "1" -> {
                            resultado.setAlta(false);
                            System.out.println("Empleado dado de baja exitosamente!");
                        }
                        case "2" -> {
                            moduloRRHH();
                        }
                        default -> {
                            System.out.println("opcion no válida");
                            bajaEmpleadoComercial();
                        }
                    }
                }
                entityManager.getTransaction().commit();
                System.out.println("El empleado ya está dado de baja");
            } catch (NoResultException e) {
                System.out.println("No se encontró el usuario");
                bajaEmpleadoComercial();;
            } finally {
                entityManager.close();
                emf.close();
            }
        } else {
            moduloRRHH();
        }
        moduloRRHH();
    }

    public static void validarUserRRHH() {
        ModuloRRHH.moduloRRHH();
    }

    public static void verTodosLosEmpleados() {
        ModuloRRHH.verEmpleadosRRHH();
        ModuloRRHH.verEmpleadosComerciales();
        ModuloRRHH.verEmpleadosMesaDeAyuda();
        ModuloRRHH.verEmpleadosEspecialistas();

        ModuloRRHH.moduloRRHH();
    }

    private static void verEmpleadosEspecialistas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Empleados Especialistas");
        System.out.println("**************");

        try{
            String jpql = "SELECT e FROM SpecialistUser e";
            TypedQuery<SpecialistUser> query = entityManager.createQuery(jpql, SpecialistUser.class);
            List<SpecialistUser> user = query.getResultList();

            // Mostrar la información
            for (SpecialistUser empleado : user) {
                System.out.println("--------------------");
                System.out.println("Id: " + empleado.getIdUser());
                System.out.println("Nombre: " + empleado.getName());
                System.out.println("Apellido: " + empleado.getUserName());
                System.out.println("Nombre de usuario: " + empleado.getUserName());
                System.out.println("email: " + empleado.getEmail());
                System.out.println("Estado: " + empleado.getAlta());
                System.out.println("Especialidades");
                for(OfferedService ofSer : empleado.getOfferedServices()){
                    System.out.println(ofSer.getOfferedServiceName());
                }
                System.out.println("--------------------");
            }

            // Commit de la transacción
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
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

    private static void verEmpleadosMesaDeAyuda() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Empleados Mesa de ayuda");
        System.out.println("**************");

        try{
            String jpql = "SELECT e FROM HelpDeskUser e";
            TypedQuery<HelpDeskUser> query = entityManager.createQuery(jpql, HelpDeskUser.class);
            List<HelpDeskUser> user = query.getResultList();

            // Mostrar la información
            for (HelpDeskUser empleado : user) {
                System.out.println("--------------------");
                System.out.println("Id: " + empleado.getIdUser());
                System.out.println("Nombre: " + empleado.getName());
                System.out.println("Apellido: " + empleado.getUserName());
                System.out.println("Nombre de usuario: " + empleado.getUserName());
                System.out.println("email: " + empleado.getEmail());
                System.out.println("Estado: " + empleado.getAlta());
                System.out.println("--------------------");
            }

            // Commit de la transacción
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
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

    private static void verEmpleadosComerciales() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Empleados Comerciales");
        System.out.println("**************");

        try{
            String jpql = "SELECT e FROM ComercialUser e";
            TypedQuery<ComercialUser> query = entityManager.createQuery(jpql, ComercialUser.class);
            List<ComercialUser> user = query.getResultList();

            // Mostrar la información
            for (ComercialUser empleado : user) {
                System.out.println("--------------------");
                System.out.println("Id: " + empleado.getIdUser());
                System.out.println("Nombre: " + empleado.getName());
                System.out.println("Apellido: " + empleado.getUserName());
                System.out.println("Nombre de usuario: " + empleado.getUserName());
                System.out.println("email: " + empleado.getEmail());
                System.out.println("Estado: " + empleado.getAlta());
                System.out.println("--------------------");
            }

            // Commit de la transacción
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
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

    private static void verEmpleadosRRHH() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Empleados RRHH");
        System.out.println("**************");

        try{
            String jpql = "SELECT e FROM RrhhUser e";
            TypedQuery<RrhhUser> query = entityManager.createQuery(jpql, RrhhUser.class);
            List<RrhhUser> user = query.getResultList();

            // Mostrar la información
            for (RrhhUser empleado : user) {
                System.out.println("--------------------");
                System.out.println("Id: " + empleado.getIdUser());
                System.out.println("Nombre: " + empleado.getName());
                System.out.println("Apellido: " + empleado.getUserName());
                System.out.println("Nombre de usuario: " + empleado.getUserName());
                System.out.println("email: " + empleado.getEmail());
                System.out.println("Estado: " + empleado.getAlta());
                System.out.println("--------------------");
            }

            // Commit de la transacción
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
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
    public static void reporteDiarioDeEspecialistas() {
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
}




