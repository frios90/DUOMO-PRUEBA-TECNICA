package com.doumo.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return """
            <html>
            <body>
            <pre>
            ============================================
            PROYECTO ACTIVO
            ============================================

            Prueba Técnica - Duomo
            Desarrollador: Francisco Ríos Castillo

            Tecnologías:
               • Backend:  Spring Boot 4.1
               • Frontend: Angular 20
               • Java:     17

            Endpoints disponibles:
               GET    /api/people
               GET    /api/people/{id}
               POST   /api/people
               DELETE /api/people/{id}
               GET    /api/people/paginate
               GET    /api/regions
               GET    /api/regions/{id}/communes

            Almacenamiento: En memoria (RAM)
            Estado: Activo

            ============================================
            </pre>
            </body>
            </html>
            """;
    }
}