package exampleApp.controllers;


import exampleApp.models.dtos.PlanetDto;
import exampleApp.services.PlanetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class HomeController {

    private PlanetService planetService;

    public HomeController(PlanetService planetService) {
        this.planetService = planetService;
    }


    @GetMapping("/delete")
    public String deletePlanet(@RequestParam(value = "planet") String planetName) {
        planetService.deletePlanet(planetName);
        return "redirect:/planets";
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("message", "loged in as: " + context.getAuthentication().getName());
        model.addAttribute("planets", planetService.getPlanetsDto()); // binduje, przekazuje planetService.get pod parametrem planets
        return "index";
    }


    //@GetMapping jest nowszą wersją @RequestMapping, "lepszą"
    @PreAuthorize("hasRole('ADMIN')")// deklaruje jaki użytkownik może wejść/
    @GetMapping("/planets")
    public String getPlanetPage(Model model) {
        model.addAttribute("planets", planetService.getPlanetsDto());
        return "planets";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/add")
    public String addPlanet(@ModelAttribute PlanetDto planetDto) {
        planetService.addPlanet(planetDto);
        return "redirect:/planets";
    }

}
