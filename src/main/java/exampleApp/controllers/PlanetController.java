package exampleApp.controllers;

import exampleApp.models.Planet;
import exampleApp.models.dtos.PlanetDto;
import exampleApp.services.PlanetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//tworzy beana
@CrossOrigin//warstwa łączenia się ze światem zewnętrzym, konsumuje i produkuje Jsona
@RequestMapping("/api/v1")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    /*
     *
     *
     *DAO
     *
     *
     */

    @GetMapping(value = "/dto/planets")
    public List<Planet> getPlanets() {
        return planetService.getPlanets();
    }

    /*
     *
     *
     *DTO
     *
     *
     */

    //sposób 1

//    @GetMapping(value = "/dto/planets")
//    public List<PlanetDto> getPlanetsDto() {
//        return planetService.getPlanetsDto();
//    }
//
//    @GetMapping("/dto/planets/{distance}")
//    public List<PlanetDto> getPlanetByDistance(@PathVariable Long distance) {
//        return planetService.getPlanetsByDistanceFromSun(distance);
//    }

    //sposób 2

    @GetMapping("/dto/planets/")
    public List<PlanetDto> getPlanetByDistance(@RequestParam(required = false) Long distance) {
        if (distance != null && distance > 0) {

            return planetService.getPlanetsByDistanceFromSun(distance);
        }
        return planetService.getPlanetsDto();
    }

    //zapytania z html są w ciele, get jest metoda indepodentną, a set wysyła na serwer i jest depodentny
    @PostMapping("/dto/planets")
    public Planet addPlanet(@RequestBody PlanetDto planetDto) { //@RequestBody oczekuje obiektu Json
        return planetService.addPlanet(planetDto);
    }

    //aktualizowanie, taka różnica pomiędzy postem a putem, post wysyła na serwer put aktualizuje
    @PutMapping("/dto/planets")
    public void updatePlanet(@RequestBody PlanetDto planetDto) {
        planetService.updatePlanet(planetDto);
    }

    @DeleteMapping("/dto/planets/{planetName}") //{}-podajemy imię planety, @PathVariable wyciąga ze ścieżki
    public void deletePlanet(@PathVariable String planetName) {
        planetService.deletePlanet(planetName);
    }


}
