## **PLANILLA ESCOLAR**


## DESCRIPCION DEL PROBLEMA

- El problema a resolver se centra en la necesidad de un sistema académico que facilite la interacción y gestión de la información entre estudiantes, profesores y administradores. Este sistema debe permitir el registro y autenticación de usuarios, garantizando que cada uno acceda a las funciones pertinentes según su rol, como estudiantes, profesores o administradores. Los estudiantes requieren acceso a sus propias notas para monitorear su rendimiento académico, mientras que los profesores necesitan herramientas para registrar y modificar las calificaciones de sus estudiantes de manera eficiente. Además, es fundamental que los profesores puedan registrar la asistencia diaria, permitiendo un seguimiento preciso de la presencia en clase. El administrador del sistema debe tener un control total sobre los datos académicos y de usuarios, lo que incluye la gestión de notas, asistencia y cursos, asegurando que se mantenga la integridad de la información. Asimismo, se requiere un sistema que registre las notas y asistencia, implemente reglas de negocio como el cálculo de promedios y notificaciones automáticas cuando un estudiante falte más de un número específico de veces. Es esencial que el sistema cumpla con restricciones de seguridad y funcionalidad, como la unicidad de los nombres de usuario y la validación de contraseñas, para proteger la información y asegurar que solo los usuarios autorizados puedan realizar acciones específicas. En conjunto, este problema abarca la creación de un sistema integral que permita un manejo organizado y seguro de la información académica, atendiendo las necesidades de todos los usuarios involucrados.

## Diagrama de casos de uso
![casos de uso](/diagrama_comportamental/casos_de_usos/casos_de_uso.jpg)

## codigo PlantUML:

```sql


@startuml
left to right direction

actor Persona as persona
actor Profesor as profesor
actor Estudiante as estudiante
actor Director as director
persona <|-- profesor
persona <|-- estudiante
persona <|-- director

database "Base de Datos" as BD

rectangle "Planilla Escolar" {

    usecase "Iniciar sesión" as iniciarSesion
    usecase "Registrar usuario" as registrarUsuario
    usecase "Asignar contraseña" as asignarContraseña
    usecase "Actualizar contraseña" as actualizarContraseña
    usecase "Ver notas" as verNotas
    usecase "Gestionar cursos" as gestionarCursos
    usecase "Gestionar materias" as gestionarMaterias
    usecase "Gestionar notas" as gestionarNotas
    usecase "Asistencia" as asistencia
    usecase "Guardar" as guardar
    usecase "Panilla escolar" as panillaEscolar

    iniciarSesion -down-> asignarContraseña : <<include>>
    iniciarSesion -down-> registrarUsuario : <<include>>
    iniciarSesion -up-> actualizarContraseña : <<extend>>
    iniciarSesion -right-> panillaEscolar : <<include>>
   
    panillaEscolar -right-> gestionarNotas : <<include>>
    panillaEscolar -down-> gestionarCursos : <<include>>
    panillaEscolar -down-> gestionarMaterias : <<include>>
    panillaEscolar -down-> verNotas : <<extend>>
    gestionarCursos -down-> asistencia : <<include>>
    gestionarMaterias -right-> guardar : <<include>>
   
    profesor -right-> iniciarSesion
    estudiante -right-> iniciarSesion
    director -right-> iniciarSesion
   
    BD --|> iniciarSesion
}

@enduml


```