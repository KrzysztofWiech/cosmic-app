package exampleApp.mappers;

import exampleApp.commons.Mapper;
import exampleApp.models.Planet;
import exampleApp.models.Tag;
import exampleApp.models.dtos.PlanetDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//bierze obiekt z bazy danych i przerabia go na co chcemy
//jeśli nie mam dto to tego nie potrzebuje
@Component
public class PlanetMapper implements Mapper<Planet, PlanetDto> {


    @Override
    public PlanetDto map(Planet f) {

        List<String> tags = f
                .getTags()
                .stream()
                .map(tagsToStringsList.INSTANCE)
                .collect(Collectors.toList());


        return PlanetDto
                //przywołuje metody
                .builder()
                .planetName(f.getPlanetName())
                .distanceFromSun(f.getDistanceFromSun())
                .oneWayLightTimeToTheSun(f.getOneWayLightTimeToTheSun())
                .lengthOfYear(f.getLengthOfYear())
                .planetType(f.getPlanetType())
                .planetInfo(f.getPlanetInfo())
                .planetImage(f.getPlanetImage())
                .tags(tags)
                .build();
    }

    @Override
    public Planet revers(PlanetDto to) {
        return Planet
                //przywołuje metody
                .builder()
                .planetName(to.getPlanetName())
                .distanceFromSun(to.getDistanceFromSun())
                .oneWayLightTimeToTheSun(to.getOneWayLightTimeToTheSun())
                .lengthOfYear(to.getLengthOfYear())
                .planetType(to.getPlanetType())
                .planetInfo(to.getPlanetInfo())
                .planetImage(to.getPlanetImage())
                .build();

    }

    private enum tagsToStringsList implements Function<Tag, String> {
        INSTANCE;

        @Override
        public String apply(Tag tag) {
            return tag.getTitle();
        }
    }

}
