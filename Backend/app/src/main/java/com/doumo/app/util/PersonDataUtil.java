package com.doumo.app.util;

import com.doumo.app.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDataUtil {

    public static List<Person> getSampleUsers() {
        List<Person> users = new ArrayList<>();

        String[][] data = {
            {"1", "Ana", "González", "ana.gonzalez@email.com", "25", "7", "7001"},
            {"2", "Carlos", "Rojas", "carlos.rojas@email.com", "32", "6", "6001"},
            {"3", "María", "Pérez", "maria.perez@email.com", "28", "7", "7002"},
            {"4", "José", "Martínez", "jose.martinez@email.com", "45", "8", "8001"},
            {"5", "Francisca", "Soto", "francisca.soto@email.com", "22", "5", "5001"},
            {"6", "Miguel", "Hernández", "miguel.hernandez@email.com", "38", "11", "11001"},
            {"7", "Valentina", "López", "valentina.lopez@email.com", "30", "7", "7003"},
            {"8", "Diego", "Muñoz", "diego.munoz@email.com", "27", "6", "6002"},
            {"9", "Catalina", "Sandoval", "catalina.sandoval@email.com", "35", "9", "9001"},
            {"10", "Felipe", "Torres", "felipe.torres@email.com", "41", "12", "12001"},
            {"11", "Camila", "Ramírez", "camila.ramirez@email.com", "24", "7", "7004"},
            {"12", "Pablo", "Reyes", "pablo.reyes@email.com", "33", "14", "14001"},
            {"13", "Valeria", "Araya", "valeria.araya@email.com", "29", "3", "3001"},
            {"14", "Andrés", "Orellana", "andres.orellana@email.com", "50", "4", "4001"},
            {"15", "Daniela", "Molina", "daniela.molina@email.com", "26", "11", "11002"},
            {"16", "Ignacio", "Alvarez", "ignacio.alvarez@email.com", "37", "6", "6003"},
            {"17", "Javiera", "Espinoza", "javiera.espinoza@email.com", "31", "7", "7002"},
            {"18", "Cristian", "Rivera", "cristian.rivera@email.com", "44", "10", "10001"},
            {"19", "Melisa", "Maldonado", "melisa.maldonado@email.com", "23", "13", "13001"},
            {"20", "Sebastián", "Gómez", "sebastian.gomez@email.com", "36", "5", "5002"}
        };

        for (String[] row : data) {
            Person person = new Person();
            person.setId(row[0]);
            person.setName(row[1]);
            person.setLastName(row[2]);
            person.setEmail(row[3]);
            person.setAge(Integer.parseInt(row[4]));
            person.setRegionId(row[5]);
            person.setCommuneId(row[6]);
            users.add(person);
        }

        return users;
    }
}