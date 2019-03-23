package exampleApp.controllers;


import exampleApp.models.dtos.TagDto;
import exampleApp.services.TagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class TagController {


    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/dto/tags")
    public List<TagDto> getTags() {
        return tagService.getTags();
    }

}
