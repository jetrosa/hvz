# Humans versus Zombies backend
- Related frontend repository: https://github.com/jmtuulos/human-vs-zombies-fe  
- Deployed backend: https://hvz-demo.up.railway.app
- Swagger (API documentation and testing): https://hvz-demo.up.railway.app/swagger-ui/index.html


  Technologies: Spring Boot, Spring Security, Hibernate, Keycloak\
  Database: Postgres\
  Build tool: Gradle
---
Human versus Zombies backend includes API server for the game and scheduled functions for game startup. 

Scheduled actions (./scheduled): 
- When a game starts,
one player will be assigned a patient zero status
- After the preset delay, patient zero will be turned into a zombie by the scheduler

Failed authentication attempts are monitored (based on IP) and the IP is blocked for a while after too many tries.\
(rate limit configuration in ./config AuthenticationFailureListener, PreSecurityFilter)
---
Development profile: default (env not set)\
Production profile: prod

Dev environment requires creating a file that includes env variables (env.properties in resources).\
Example (replace the values):\
DB_URL = jdbc:postgresql://localhost:5432/hvz-db\
DB_USERNAME = postgres\
DB_PASSWORD = postgres\
JWT_ISSUER_URI = https://lemur-3.cloud-iam.com/auth/realms/humansvszombies  
JWT_VERIFY_URI = https://lemur-3.cloud-iam.com/auth/realms/humansvszombies/protocol/openid-connect/certs  

---
### Docker examples:
Docker-compose files (with database) included for development (docker-compose.yml) and production (docker-compose-prod.yml).
- docker build -t hvz-backend .
#### Development 
- docker run -p 8080:8080 hvz-backend
- docker compose (backend server and Postgres): docker-compose build up

#### Production
- docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=prod" hvz-backend

Required environment variables:\
PROD_DB_HOST=\
PROD_DB_PORT=\
PROD_DB_NAME=\
PROD_DB_USERNAME=\
PROD_DB_PASSWORD=\
JWT_ISSUER_URI=\
JWT_VERIFY_URI=
- docker-compose -f docker-compose-prod.yml build up


## Controllers and API routes
Most API  routes require authentication using a valid bearer token. GET /api/v1/game can be used without auth.
### game-controller
| Method | Path                       | Description                                                                      |
|:-------|:---------------------------|:---------------------------------------------------------------------------------|
| DELETE | /api/v1/game/{gameId}      | Delete (cascading) a game. Admin only                                            |
| GET    | /api/v1/game/{gameId}      | Return a specific game object                                                    |
| GET    | /api/v1/game               | Return a list of games                                                           |  
| GET    | /api/v1/game/{gameId}/chat | Return a list of global and faction-specific chat messages                       |  
| POST   | /api/v1/game               | Create a new game. Admin only                                                    |  
| POST   | /api/v1/game/{gameId}/chat | Create a new chat message                                                        | 
| PUT    | /api/v1/game/{gameId}      | Update a game. Admin only                                                        |  

### squad-controller
| Method | Path                                           | Description                                  |
|:-------|:-----------------------------------------------|:---------------------------------------------|
| DELETE | /api/v1/game/{gameId}/squad/{squadId}          | Delete a squad. Admin only                   | 
| DELETE | /api/v1/game/{gameId}/squad/leave              | Delete a squad member object (leave a squad) |  
| GET    | /api/v1/game/{gameId}/squad/{squadId}          | Return a specific squad object               | 
| GET    | /api/v1/game/{gameId}/squad                    | Return a list of squads                      | 
| GET    | /api/v1/game/{gameId}/squad/{squadId}/check-in | Get a list of squad check-in markers         |  
| GET    | /api/v1/game/{gameId}/squad/{squadId}/chat     | Return a list of squad chat messages         |  
| POST   | /api/v1/game/{gameId}/squad                    | Create a squad object                        | 
| POST   | /api/v1/game/{gameId}/squad/{squadId}/join     | Create a squad member object (join a squad)  |
| POST   | /api/v1/game/{gameId}/squad/{squadId}/check-in | Create a squad checkin                       |  
| POST   | /api/v1/game/{gameId}/squad/{squadId}/chat     | Create a new squad chat message              | 
| PUT    | /api/v1/game/{gameId}/squad/{squadId}          | Update a squad object. Admin only            |

### game-player-controller
| Method | Path                                    | Description                             |
|:-------|:----------------------------------------|:----------------------------------------|
| DELETE | /api/v1/game/{gameId}/player/{playerId} | Delete (cascading) a player. Admin only |  
| GET    | /api/v1/game/{gameId}/player/{playerId} | Return a specific player object.        |  
| GET    | /api/v1/game/{gameId}/player            | Get a list of players                   |  
| POST   | /api/v1/game/{gameId}/player            | Create a new player                     |  
| PUT    | /api/v1/characters/{id}                 | Update a character                      |

### mission-controller
| Method | Path                                      | Description                         |
|:-------|:------------------------------------------|:------------------------------------|
| DELETE | /api/v1/game/{gameId}/mission/{missionId} | Delete a mission. Admin only        |  
| GET    | /api/v1/game/{gameId}/mission/{missionId} | Return a specific mission object    |  
| GET    | /api/v1/game/{gameId}/mission             | Return a list of missions           |  
| POST   | /api/v1/game/{gameId}/mission             | Create a mission object             |  
| PUT    | /api/v1/game/{gameId}/mission/{missionId} | Update a mission object. Admin only |

### bite-controller
| Method | Path                                | Description                                                              |
|:-------|:------------------------------------|:-------------------------------------------------------------------------|
| DELETE | /api/v1/game/{gameId}/bite/{biteId} | Delete a bite. Admin only                                                |  
| GET    | /api/v1/game/{gameId}/bite/{biteId} | Return a specific bite object                                            |  
| GET    | /api/v1/game/{gameId}/bite          | Return a list of bites                                                   |  
| POST   | /api/v1/game/{gameId}/bite          | Create a bite object by looking up the victim by the specified bite code |  
| PUT    | /api/v1/game/{gameId}/bite/{biteId} | Update a bite object. Admin only.                                        |

### user-controller
| Method | Path                  | Description                                         |
|:-------|:----------------------|:----------------------------------------------------|
| GET    | /api/v1/auth/players  | Return a list of player objects the user belongs to |  
| POST   | /api/v1/auth/register | Create a user                                       |
