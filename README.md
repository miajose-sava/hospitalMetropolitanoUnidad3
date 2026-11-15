# Sistema de Gestión de Citas Médicas y Diagnósticos - Hospital Metropolitano (Unidad 3)

Proyecto académico desarrollado en **Java (Swing)** aplicando persistencia mediante **Jakarta Persistence (JPA)**, el motor ORM **Hibernate** y base de datos relacional **PostgreSQL**. Esta entrega de la Unidad 3 implementa un modelo de **5 entidades relacionadas** y consultas avanzadas en **JPQL** con filtros parametrizados, ordenamientos dinámicos y paginación.

---

## 📌 Modelo de Datos (5 Entidades Relacionadas)

El dominio clínico del **Hospital Metropolitano** está estructurado mediante relaciones bidireccionales `@OneToMany` y `@ManyToOne`:

* **Departamento:** Especialidades clínicas del hospital (`1:N` con `Medico`).
* **Medico:** Profesional tratante adscrito a un departamento (`1:N` con `CitaMedica`).
* **Paciente:** Usuario registrado con cédula, nombres y contacto (`1:N` con `CitaMedica`).
* **CitaMedica:** Entidad central que gestiona fecha, hora y estado del turno (`N:1` con `Paciente`, `N:1` con `Medico` y `1:N` con `Diagnostico`).
* **Diagnostico:** Dictamen clínico y prescripción médica asociada a una cita (`N:1` con `CitaMedica`).

---

## 🔍 Consultas JPQL Implementadas (Unidad 3)

En la clase `CitaMedicaDAO` se implementaron tres consultas JPQL que combinan **filtros (`WHERE`)**, **ordenamientos (`ORDER BY`)**, optimización con **`JOIN FETCH`** y **paginación (`setFirstResult` / `setMaxResults`)**:

1. **Citas por Estado Paginadas (`consultarCitasPorEstadoPaginado`):**
   * Filtra citas por estado (`WHERE c.estado = :estado`), ordena por fecha descendente y hora ascendente (`ORDER BY c.fecha DESC, c.hora ASC`) y pagina los resultados.
2. **Citas por Departamento Médico Paginadas (`consultarCitasPorDepartamentoPaginado`):**
   * Navega hacia la especialidad del médico (`WHERE m.departamento.nombre = :depto`), ordena alfabéticamente por apellido y nombre del paciente (`ORDER BY p.apellido ASC, p.nombre ASC`) y pagina.
3. **Diagnósticos por Cédula de Paciente Paginados (`consultarDiagnosticosPorPacientePaginado`):**
   * Filtra diagnósticos por cédula del paciente (`WHERE p.cedula = :cedula`), ordena por identificador descendente (`ORDER BY d.id DESC`) y pagina.

---

## 🛠️ Tecnologías y Dependencias

* **Lenguaje:** Java 17 LTS
* **Entorno / IDE:** Apache NetBeans
* **Gestor de Dependencias:** Apache Maven (`pom.xml`)
* **ORM / Especificación:** Hibernate ORM Core & Jakarta Persistence API 3.1.0
* **Base de Datos:** PostgreSQL (`hospital_db`)
* **Modelado Conceptual:** StarUML

---

## 🏛️ Estructura del Proyecto

```text
hospitalMetropolitanoUnidad3/
├── src/main/
│   ├── java/com/mycompany/hospitalmetropolitanocrud/
│   │   ├── modelo/                        # 5 Entidades JPA (Departamento, Medico, Paciente, CitaMedica, Diagnostico)
│   │   ├── dao/                           # Operaciones CRUD y Consultas JPQL paginadas (CitaMedicaDAO.java)
│   │   ├── vista/                         # Interfaz gráfica Swing (VentanaHospital.java)
│   │   ├── HospitalMetropolitanoCrud.java # Clase principal (Interfaz gráfica)
│   │   └── PruebaConsultasU3.java         # Clase de prueba de consultas JPQL con paginación
│   └── resources/
│       └── META-INF/
│           └── persistence.xml            # Unidad de persistencia (HospitalMetropolitanoPU)
├── Diagrama UML 2.jpg                     # Diagrama de clases UML de las 5 entidades
└── pom.xml                                # Configuración y dependencias Maven
```

---
## ⚙️ Configuración y Ejecución

1. **Base de Datos:**
   * Crear la base de datos `hospital_db` en PostgreSQL.
   * Configurar las credenciales de acceso en `src/main/resources/META-INF/persistence.xml`.

2. **Ejecución en NetBeans:**
   * Ejecutar `HospitalMetropolitanoCrud.java` (`F6`) para abrir la interfaz gráfica Swing.
   * Ejecutar `PruebaConsultasU3.java` (`Shift + F6`) para verificar en consola las 3 consultas JPQL con filtros, ordenamientos y paginación.
