package com.doumo.app.util;

import com.doumo.app.model.Commune;
import com.doumo.app.model.Region;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RegionDataUtil {

    private static final List<Region> REGIONS = Arrays.asList(
        new Region("1", "Arica y Parinacota Region", Arrays.asList(
            new Commune("1001", "Arica"),
            new Commune("1002", "Camarones"),
            new Commune("1003", "General Lagos"),
            new Commune("1004", "Putre")
        )),
        new Region("2", "Tarapacá Region", Arrays.asList(
            new Commune("2001", "Iquique"),
            new Commune("2002", "Alto Hospicio"),
            new Commune("2003", "Pozo Almonte"),
            new Commune("2004", "Colchane")
        )),
        new Region("3", "Antofagasta Region", Arrays.asList(
            new Commune("3001", "Antofagasta"),
            new Commune("3002", "Calama"),
            new Commune("3003", "Tocopilla"),
            new Commune("3004", "Mejillones")
        )),
        new Region("4", "Atacama Region", Arrays.asList(
            new Commune("4001", "Copiapó"),
            new Commune("4002", "Vallenar"),
            new Commune("4003", "Caldera"),
            new Commune("4004", "Chañaral")
        )),
        new Region("5", "Coquimbo Region", Arrays.asList(
            new Commune("5001", "La Serena"),
            new Commune("5002", "Coquimbo"),
            new Commune("5003", "Ovalle"),
            new Commune("5004", "Illapel")
        )),
        new Region("6", "Valparaíso Region", Arrays.asList(
            new Commune("6001", "Valparaíso"),
            new Commune("6002", "Viña del Mar"),
            new Commune("6003", "Quilpué"),
            new Commune("6004", "Villa Alemana")
        )),
        new Region("7", "Santiago Metropolitan Region", Arrays.asList(
            new Commune("7001", "Santiago"),
            new Commune("7002", "Providencia"),
            new Commune("7003", "Las Condes"),
            new Commune("7004", "Vitacura")
        )),
        new Region("8", "O'Higgins Region", Arrays.asList(
            new Commune("8001", "Rancagua"),
            new Commune("8002", "San Fernando"),
            new Commune("8003", "Pichilemu"),
            new Commune("8004", "Santa Cruz")
        )),
        new Region("9", "Maule Region", Arrays.asList(
            new Commune("9001", "Talca"),
            new Commune("9002", "Curicó"),
            new Commune("9003", "Linares"),
            new Commune("9004", "Constitución")
        )),
        new Region("10", "Ñuble Region", Arrays.asList(
            new Commune("10001", "Chillán"),
            new Commune("10002", "Bulnes"),
            new Commune("10003", "San Carlos"),
            new Commune("10004", "Yungay")
        )),
        new Region("11", "Biobío Region", Arrays.asList(
            new Commune("11001", "Concepción"),
            new Commune("11002", "Talcahuano"),
            new Commune("11003", "Los Ángeles"),
            new Commune("11004", "Chillán")
        )),
        new Region("12", "Araucanía Region", Arrays.asList(
            new Commune("12001", "Temuco"),
            new Commune("12002", "Angol"),
            new Commune("12003", "Villarrica"),
            new Commune("12004", "Pucón")
        )),
        new Region("13", "Los Ríos Region", Arrays.asList(
            new Commune("13001", "Valdivia"),
            new Commune("13002", "La Unión"),
            new Commune("13003", "Río Bueno"),
            new Commune("13004", "Panguipulli")
        )),
        new Region("14", "Los Lagos Region", Arrays.asList(
            new Commune("14001", "Puerto Montt"),
            new Commune("14002", "Osorno"),
            new Commune("14003", "Castro"),
            new Commune("14004", "Ancud")
        )),
        new Region("15", "Aysén Region", Arrays.asList(
            new Commune("15001", "Coyhaique"),
            new Commune("15002", "Puerto Aysén"),
            new Commune("15003", "Chile Chico"),
            new Commune("15004", "Cochrane")
        )),
        new Region("16", "Magallanes Region", Arrays.asList(
            new Commune("16001", "Punta Arenas"),
            new Commune("16002", "Puerto Natales"),
            new Commune("16003", "Porvenir"),
            new Commune("16004", "Cabo de Hornos")
        ))
    );

    private static final Map<String, Region> REGIONS_MAP = REGIONS.stream().collect(Collectors.toMap(Region::getId, r -> r));

    public static List<Region> getRegions() {
        return REGIONS;
    }

    public static Region getRegion(String regionId) {
        return REGIONS_MAP.get(regionId);
    }

    public static String getRegionName(String regionId) {
        Region region = REGIONS_MAP.get(regionId);
        return region != null ? region.getName() : null;
    }

    public static String getCommuneName(String regionId, String communeId) {
        Region region = REGIONS_MAP.get(regionId);
        if (region != null) {
            return region.getCommunes().stream()
                .filter(c -> c.getId().equals(communeId))
                .map(Commune::getName)
                .findFirst()
                .orElse(null);
        }
        return null;
    }

    public static boolean validateRegionAndCommune(String regionId, String communeId) {
        Region region = REGIONS_MAP.get(regionId);
        if (region == null) return false;
        return region.getCommunes().stream().anyMatch(c -> c.getId().equals(communeId));
    }
}