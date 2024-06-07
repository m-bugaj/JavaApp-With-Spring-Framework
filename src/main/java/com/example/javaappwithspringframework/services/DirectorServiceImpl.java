package com.example.javaappwithspringframework.services;

import com.example.javaappwithspringframework.api.mapper.DirectorMapper;
import com.example.javaappwithspringframework.api.model.DirectorDTO;
import com.example.javaappwithspringframework.model.Director;
import com.example.javaappwithspringframework.repositories.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DirectorServiceImpl implements DirectorService {

    DirectorRepository directorRepository;
    DirectorMapper directorMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @Override
    public List<DirectorDTO> getAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(directorMapper::directorToDirectorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDTO getDirectorById(Long id) {
        Optional<Director> directorOptional = directorRepository.getFirstById(id);

        if (directorOptional.isPresent()) return directorMapper.directorToDirectorDTO(directorOptional.get());

        return null;
    }

    @Override
    public DirectorDTO getDirectorByFirstNameAndLastName(String firstName, String lastName) {
        Optional<Director> directorOptional = directorRepository.getFirstByFirstNameAndLastName(firstName, lastName);

        if (directorOptional.isPresent()) return directorMapper.directorToDirectorDTO(directorOptional.get());

        return null;
    }

    @Override
    public DirectorDTO createNewDirector(DirectorDTO directorDTO) {

        Director director = directorMapper.directorDTOToDirector(directorDTO);
        Director savedDirector = directorRepository.save(director);

        return directorMapper.directorToDirectorDTO(savedDirector);
    }

    @Override
    public DirectorDTO updateDirector(Long id, DirectorDTO directorDTO) {

        Director director = directorMapper.directorDTOToDirector(directorDTO);
        director.setId(id);

        Director savedDirector = directorRepository.save(director);

        return directorMapper.directorToDirectorDTO(savedDirector);
    }

    @Override
    public void deleteDirectorById(Long id) {
//        directorRepository.deleteById(id);

        Optional<Director> directorOptional = directorRepository.findById(id);

        if (directorOptional.isPresent()) {
            Director directorToRemove = directorOptional.get();
            // Usuń powiązania z filmami
            directorToRemove.getMovies().forEach(movie -> movie.getDirectors().remove(directorToRemove));
            directorToRemove.getMovies().clear();  // Wyczyść listę filmów
            directorRepository.save(directorToRemove);  // Zaktualizuj filmy przed usunięciem reżysera
            directorRepository.delete(directorToRemove);
        }
    }


}
