package exampleApp.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//Dto tworzymy po to żeby Json był ładniejszy dla frontendowca, mniej skomplikowany
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanetDto {

//uproszczenie obiektu bazodanowego, skopiowane z Planet i "oczyszczone"


    private String planetName;
    private long distanceFromSun;
    private double oneWayLightTimeToTheSun;
    private long lengthOfYear;
    private String planetType;
    private String planetInfo;
    private String planetImage;
    private List<String> tags = new ArrayList<>();

}
