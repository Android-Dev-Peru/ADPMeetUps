## Android Dev Perú App

### Introducción
Este proyecto involucra las últimas tecnologías relacionadas con el desarrollo de apps Android y Kotlin. Es decir, aquí encontrarás funciones desarrolladas con Jetpack Compose, Kotlin Multiplatform, Gradle, utilizando una arquitectura modular y en capas, testing y todas las habilidades MAD (desarrollo moderno de Android) relacionadas.

Los organizadores de la comunidad comenzarán a darle vida al proyecto para definir la arquitectura base y la hoja de ruta de características y estará abierto a Pull Requests.

### Principales funcionalidades
El proyecto contendrá los siguientes _features_:
1. Eventos
   - 1.1 Mostrar la lista de eventos (MeetUps y talleres/workshops) ordenados por fecha: del más reciente al más antiguo. Además, el usuario podrá filtrar eventos por tipo, mes, año, etc.
   - 1.2 Detalles del evento para mostrar toda la información relacionada de un evento: título, descripción, oradores, ubicación, si será _online_ o se llevará a cabo en una dirección física, lista de asistentes, etc.
   - 1.3 Registrar asistencia a un evento: los usuarios podrán registrarse a un evento.
2. Información de la comunidad
   - 2.1 Información sobre la comunidad: redes sociales, enlaces útiles, historial, etc.
   - 2.2 Organizadores: lista de los organizadores actuales y una pequeña descripción de cada perfil.
3. Autenticación
   - 3.1 Inicio de sesión con Google y Apple: requerido para tener la aplicación disponible en AppStore (algún día). No será obligatorio iniciar sesión en la aplicación para navegar por ella. Sin embargo, podría ser necesario para registrar la asistencia a un evento.

**MVP (Producto Mínimo Viable)**

En la primera versión, vamos a priorizar las siguientes características:

- 1.1 Lista de eventos (sin filtrar)
- 1.2 Detalles de eventos (sin lista de asistentes)
- 2.1 Pantalla con información de la comunidad


---


### Arquitectura
Vamos a implementar una arquitectura modular y en capas. Sin embargo, para la primera iteración, mantendremos todo el código en un solo módulo Gradle (_shared_) y allí dividiremos el código en paquetes.

**Capa de dominio o Lógica de Negocio**

Los siguientes modelos de datos estarán relacionados con la lógica principal de la app:
```
class Event
 - String id
 - String title
 - String description
 - Date createdAt
 - Date lastUpdate
 - Date dateTime
 - * List<Attendee> attendees
 - String eventBannerUrl
 - Speaker speaker
 - EventType eventType
 - EventLocation location
 - String addressInfo

enum EventType: meetup, workshop

enum EventLocation: online, inPerson


interface EventsRepository
 - List<Event> getLastEvents(int offset = 0)
 - Event getEventInfo(String eventId)



--- not part of MVP ---

class Attendee
 - String id
 - String name
 - AttendeeStatus status
 - Date statusDate
 - String profilePic

enum AttendeeStatus: invited, accepted, rejected, unknown

class UserAccount
 - String id
 - String createdAt
 - String email
 - List<Platform> platforms
 - Boolean acceptsNotificationEvents

class UserProfile
 - String accountId
 - String name
 - String lastName
 - String profilePicUrl
 - List<String> attendedEventsIds


Other repository interfaces to be defined later.

```

_Notar el uso de la nomenclatura camel case._

_*La clase Event no tendrá la lista de asistentes como atributo para el MVP_



**Capa de datos**

Para empezar no vamos a consumir ninguna fuente de datos externa, aplicaremos el patrón repositorio. La siguiente es la capa de datos estructurada sugerida:
```
- domain
 - repository

- data
 - repository
   - EventsRepositoryImpl
 - dataSource
   - LocalDataSource (implementación directa)
   - RemoteDataSource (implementación directa, vacía)
```
_Tenga en cuenta que las fuentes de datos serán implementaciones que se inyectarán en `EventsRepositoryImpl` ya que vamos a tener (en el MVP) solo una fuente de datos._



**Capa de Presentación o UI**

Vamos a utilizar Compose Multiplatform e implementar los patrones de diseño Unidirectional Data Flow y Model View ViewModel.
La estructura debe tener:
```
- core -> para definir el tema (fuentes, colores, etc) y vistas reutilizables (botones, campos de texto, etc).
- feature-package -> tendremos solo dos por ahora: events y community
```

### Tech Stack
Como ya imaginarás el tech stack estará conformado por:
- Kotlin Multiplatform
- Compose Multiplatform
- Kotlin 1.9
- Kamel 0.9.0 para la carga de imagenes
- Navigation -> TBD


---

### Otros detalles

**Diseño**

Intentamos utilizar GalileoAi pero aún está en desarrollo y cerrado a todas las personas. Podríamos simplemente proponer un diseño mientras lo desarrollamos. Es **importante definir la temática: colores, estilos de texto, etc**.


**Para contribuir**

1. Por favor, escribe tus Pull Requests brindando una breve descripción, cuáles fueron los principales cambios y cómo o por qué hiciste lo que hiciste. Si se modifica la interfaz de usuario, proporcione una captura de pantalla de cómo se veía antes y cómo se ve ahora. Más adelante definiremos una plantilla para los Pull Requests.

2. El desarrollo se mueve gracias a un Proyecto Github. Por favor, lea atentamente todas las tareas restantes y no dude en proponer nuevas tareas que estén enlazadas a issues de Github.

3. Las etiquetas (labels) siempre las ponen los mantenedores/organizadores.

4. Si no eres parte del equipo de la organización, crea un _fork_ del repositorio y agrega tus contribuciones a través de un Pull Request.

4. Para nuevos _features_, cree una rama `feature/featureName`. Para fixes/correcciones: `bugfix/taskCode` o `bugfix/fixName`.


 



