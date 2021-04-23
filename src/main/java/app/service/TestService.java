package app.service;

import app.domain.DTO.AutomobileDTO;
import app.mapper.MainMapper;
import app.repository.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final MainMapper mapper;
    private final AutomobileRepository repository;

    @Autowired
    public TestService(MainMapper mapper, AutomobileRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public void addAuto(AutomobileDTO dto){
        repository.save(mapper.autoDtoToAuto(dto));
    }
}
