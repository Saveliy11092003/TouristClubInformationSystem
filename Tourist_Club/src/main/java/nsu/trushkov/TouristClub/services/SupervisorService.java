package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import nsu.trushkov.TouristClub.repositories.SupervisorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupervisorService {
    private final SupervisorRepository supervisorRepository;



}
