package academy.devdojo.springboot.service;

import academy.devdojo.springboot.domain.Anime;
import academy.devdojo.springboot.repository.AnimeRepository;
import academy.devdojo.springboot.requests.AnimePostRequestBody;
import academy.devdojo.springboot.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    public final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found!"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(Anime.builder().name(animePostRequestBody.name()).build());
    }

    public void delete(Long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.id());
        animeRepository.save(Anime.builder().id(savedAnime.getId()).name(animePutRequestBody.name()).build());
    }
}
