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
            new Commune("1-1", "Arica"),
            new Commune("1-2", "Camarones"),
            new Commune("1-3", "General Lagos"),
            new Commune("1-4", "Putre")
        )),
        new Region("2", "Tarapacá Region", Arrays.asList(
            new Commune("2-1", "Iquique"),
            new Commune("2-2", "Alto Hospicio"),
            new Commune("2-3", "Pozo Almonte"),
            new Commune("2-4", "Colchane")
        )),
        new Region("3", "Antofagasta Region", Arrays.asList(
            new Commune("3-1", "Antofagasta"),
            new Commune("3-2", "Calama"),
            new Commune("3-3", "Tocopilla"),
            new Commune("3-4", "Mejillones")
        )),
        new Region("4", "Atacama Region", Arrays.asList(
            new Commune("4-1", "Copiapó"),
            new Commune("4-2", "Vallenar"),
            new Commune("4-3", "Caldera"),
            new Commune("4-4", "Chañaral")
        )),
        new Region("5", "Coquimbo Region", Arrays.asList(
            new Commune("5-1", "La Serena"),
            new Commune("5-2", "Coquimbo"),
            new Commune("5-3", "Ovalle"),
            new Commune("5-4", "Illapel")
        )),
        new Region("6", "Valparaíso Region", Arrays.asList(
            new Commune("6-1", "Valparaíso"),
            new Commune("6-2", "Viña del Mar"),
            new Commune("6-3", "Quilpué"),
            new Commune("6-4", "Villa Alemana")
        )),
        new Region("7", "Santiago Metropolitan Region", Arrays.asList(
            new Commune("7-1", "Santiago"),
            new Commune("7-2", "Providencia"),
            new Commune("7-3", "Las Condes"),
            new Commune("7-4", "Vitacura")
        )),
        new Region("8", "O'Higgins Region", Arrays.asList(
            new Commune("8-1", "Rancagua"),
            new Commune("8-2", "San Fernando"),
            new Commune("8-3", "Pichilemu"),
            new Commune("8-4", "Santa Cruz")
        )),
        new Region("9", "Maule Region", Arrays.asList(
            new Commune("9-1", "Talca"),
            new Commune("9-2", "Curicó"),
            new Commune("9-3", "Linares"),
            new Commune("9-4", "Constitución")
        )),
        new Region("10", "Ñuble Region", Arrays.asList(
            new Commune("10-1", "Chillán"),
            new Commune("10-2", "Bulnes"),
            new Commune("10-3", "San Carlos"),
            new Commune("10-4", "Yungay")
        )),
        new Region("11", "Biobío Region", Arrays.asList(
            new Commune("11-1", "Concepción"),
            new Commune("11-2", "Talcahuano"),
            new Commune("11-3", "Los Ángeles"),
            new Commune("11-4", "Chillán")
        )),
        new Region("12", "Araucanía Region", Arrays.asList(
            new Commune("12-1", "Temuco"),
            new Commune("12-2", "Angol"),
            new Commune("12-3", "Villarrica"),
            new Commune("12-4", "Pucón")
        )),
        new Region("13", "Los Ríos Region", Arrays.asList(
            new Commune("13-1", "Valdivia"),
            new Commune("13-2", "La Unión"),
            new Commune("13-3", "Río Bueno"),
            new Commune("13-4", "Panguipulli")
        )),
        new Region("14", "Los Lagos Region", Arrays.asList(
            new Commune("14-1", "Puerto Montt"),
            new Commune("14-2", "Osorno"),
            new Commune("14-3", "Castro"),
            new Commune("14-4", "Ancud")
        )),
        new Region("15", "Aysén Region", Arrays.asList(
            new Commune("15-1", "Coyhaique"),
            new Commune("15-2", "Puerto Aysén"),
            new Commune("15-3", "Chile Chico"),
            new Commune("15-4", "Cochrane")
        )),
        new Region("16", "Magallanes Region", Arrays.asList(
            new Commune("16-1", "Punta Arenas"),
            new Commune("16-2", "Puerto Natales"),
            new Commune("16-3", "Porvenir"),
            new Commune("16-4", "Cabo de Hornos")
        ))
    );

    private static final Map<String, Region> REGIONS_MAP = REGIONS.stream()
            .collect(Collectors.toMap(Region::getId, r -> r));

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

        return region.getCommunes().stream()
                .anyMatch(c -> c.getId().equals(communeId));
    }
}