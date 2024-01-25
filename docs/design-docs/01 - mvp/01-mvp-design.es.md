## Android Dev Perú App

## Contenido

- [🌟 Objetivo](#-objetivo)
- [📓 Contexto](#-contexto)
- [🤝 Decisiones clave](#-decisiones-clave)
- [🔍 Diseño detallado](#-diseño-detallado)
  - [🏗️ Arquitectura](#-arquitectura)
  - [🖥️ Tech Stack](#-tech-stack)

## 🌟 Objetivo

Este documento detalla las decisiones arquitectónicas fundamentales que hemos tomado para la primera versión de esta app. No obstante, la idea es que esta arquitectura se adapte a medida que la app vaya escalando.

## 📓 Contexto

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

En la primera versión (MVP), vamos a priorizar las siguientes características:

- 1.1 Lista de eventos (sin filtrar)
- 1.2 Detalles de eventos (sin lista de asistentes)
- 2.1 Pantalla con información de la comunidad


---

## 🤝 Decisiones clave

1. Arquitectura en capas y modular dentro de un único módulo:
    - Nos permitirá avanzar más rápido
    - Se puede refactorizar a multimódulo una vez que el proyecto escale
2. Patrón de repositorio para la capa de datos:
    - Nos permite establecer una adecuada separación de responsabilidades
    - Simplifica las pruebas
    - Práctica estándar en la industria
3. Flujo de datos unidireccional en la capa de presentación

## 🔍 Diseño detallado

### 🏗️ Arquitectura
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



--- no es parte del MVP ---

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


Otras interfaces de repositorios serán definidas después.

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

### 🖥️ Tech Stack
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
