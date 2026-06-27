# Prueba Técnica – Personas CRUD (Spring Boot + Angular)

Aplicación full-stack para administración de personas, desarrollada como prueba técnica para **Duomo**. Incluye un backend en **Spring Boot 4.1** con almacenamiento en memoria, y un frontend en **Angular 20** con validaciones en tiempo real y diseño basado en la identidad visual de Duomo.

---
### Requisitos Previos

- **Java 17+** (Backend)
- **Node.js 18+** y **npm** (Frontend)
- **Maven** (incluido en el proyecto con `mvnw`)
- **Angular CLI 20** (opcional, se puede usar `npx`)

---

---
### Tecnologías
| Capa               | Tecnología                                            |
|--------------------|------------                                           |
| **Backend**        | Spring Boot 4.1, Java 17, Maven                       |
| **Frontend**       | Angular 20, TypeScript, Bootstrap 5                   |
| **Estilos**        | CSS con paleta de colores Duomo (`#0f2b3d`, `#f5a623`)|
| **Almacenamiento** | En memoria (`ConcurrentHashMap`)                      |
| **Notificaciones** | SweetAlert2 para modales y alertas                    |

---

---
### Estructura del Proyecto

    DUOMO-PRUEBA-TECNICA/
    ├── Backend/
    │ └── app/
    │ ├── src/
    │ │ ├── main/
    │ │ │ ├── java/com/doumo/app/
    │ │ │ │ ├── config/CorsConfig.java
    │ │ │ │ ├── controller/
    │ │ │ │ ├── dto/
    │ │ │ │ ├── model/
    │ │ │ │ ├── repository/
    │ │ │ │ ├── service/
    │ │ │ │ └── util/RegionDataUtil.java
    │ │ │ └── resources/application.properties
    │ │ └── test/
    │ └── pom.xml
    └── Frontend/
    └── frontend/
    ├── src/
    │ ├── app/
    │ │ ├── components/
    │ │ │ ├── person-form/
    │ │ │ └── person-list/
    │ │ ├── models/
    │ │ ├── services/
    │ │ ├── app.component.*
    │ │ └── app.config.ts
    │ ├── index.html
    │ ├── main.ts
    │ └── styles.css
    ├── angular.json
    └── package.json
---

---

## ⚙️ Instalación y Ejecución Local

### 1. Clonar el repositorio
    ```bash
        git clone https://github.com/frios90/DUOMO-PRUEBA-TECNICA.git
        cd DUOMO-PRUEBA-TECNICA

    ```
### 2. Backend (Spring Boot)
    ```bash
        cd Backend/app
        # Ejecutar en puerto 8080
        ./mvnw spring-boot:run

    ```


### 3. Frontend (Angular)
    ```bash
        # Instalar dependencias
        npm install
        # Ejecutar en puerto 4200
        npx ng serve --host 0.0.0.0 --port 4200

    ```

### 4. Probar la aplicación
    ```bash
        Frontend: http://localhost:4200
        Backend API: http://localhost:9090/api/regions

    ```


