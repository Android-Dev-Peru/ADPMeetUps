# MVP - Project architecture

## Content

- [🌟 Objective](#-objective)
- [📓 Background](#-background)
- [🤝 Key decisions](#-key-decisions)
- [🔍 Detailed Design](#-detailed-design)
  - [🏗️ Architecture](#-architecture)
  - [🖥️ Tech Stack](#-tech-stack)

## 🌟 Objective

This document describes the key architectural decisions that we're taking for the very first version of this app. These decisions will influence most of the implementation details of the upcoming features. However, it's meant to evolve as the app scales - it's not set on stone.

## 📓 Background

The project will contain the following features:
1. Events
  - 1.1 Show list of events (MeetUps and workshops) ordered by dated: from the latest to the oldest. Moreover, user will be able to filter events by type, month, year, etc.
  - 1.2 Event details to show all related information of an event: title, descripcion, speakers, location, if it's online or will take place in a physical address, list of attendees, etc.
  - 1.3 Register assistance to an event: users will be able to register to an event.
2. Community information
  - 2.1 Information about the community: social networks, useful links, history, etc.
  - 2.2 Organizers: list of the current organizers and a little profile description.
3. Authentication
  - 3.1 Login with Google and Apple: required in order to have the app available for AppStore (some day). It is not mandatory to log into the app to navigate through it. However, could be required in order to register attendance to an event.

On the first version (MVP), we are going to prioritize the following features:

- 1.1 Events list (without filtering)
- 1.2 Events details (without attendants list)
- 2.1 Screen with community information


---

## 🤝 Key Decisions

1. Layered & modular architecture within a single module:
    - will allow us to move faster
    - can be refactored into multi-module once the project scales
2. Repository pattern for data layer:
    - allows us to establish proper separation of concerns
    - simplifies testing
    - standard practice across industry
3. Unidirectional data flow in the presentation layer

## 🔍 Detailed Design

### 🏗️ Architecture
We are going to implement a layered and modular architecture. For the first iteration, we are going to keep all the code in one single Gradle module (shared) and there, divide the code into packages.


**Domain layer**

The following data models are going to be related to the domain logic:
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


Other interfaces and repositories will be defined later.

```

_Notice the use of camel case as naming convention._

_*The Event data class won't have the list of attendees as an attribute for the MVP_



**Data layer**

Since we are not going to consume any external data source to begin, we should apply the Repository Pattern properly. The following is the suggested data layer structure:
```
- domain
 - repository

- data
 - repository
   - EventsRepositoryImpl
 - dataSource
   - LocalDataSource (direct implementation)
   - RemoteDataSource (direct implementation, empty)
```
_Notice that the data sources are going to be implementations to be injected into the `EventsRepositoryImpl` since we are going to have (in the MVP) only one data source._



**Presentation Layer**

We are going to use Compose Multiplatform and implement the Unidirectional Data Flow and Model View ViewModel design patterns.
The structure should have:
```
- core -> to define theming and reusable composables (buttons, fields, etc)
- feature-package -> we are going to have only two: events and community
```

#### 🖥️ Tech Stack
The stack is going to be conformed by:
- Kotlin Multiplatform
- Compose Multiplatform
- Kotlin 1.9
- Kamel 0.9.0 for image loading
- Navigation -> TBD


---

### Other details

**Design**

We tried to use GalileoAi but it's still in development and closed to all people. We could just propose a design while developing. It's **important to define de theming: colors, text styles, etc**.
