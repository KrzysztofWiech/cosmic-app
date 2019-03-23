package exampleApp.services;

import exampleApp.mappers.TagMapper;
import exampleApp.models.dtos.TagDto;
import exampleApp.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private TagRepository tagRepository;
    private TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public List<TagDto> getTags() {
        return tagRepository
                .findAll()//tutaj
                .stream()
                .map(tagMapper::map)
                .collect(Collectors.toList());
    }

}
