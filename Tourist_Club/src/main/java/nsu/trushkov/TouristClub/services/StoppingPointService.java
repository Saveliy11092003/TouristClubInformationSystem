package nsu.trushkov.TouristClub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nsu.trushkov.TouristClub.entity.StoppingPoint;
import nsu.trushkov.TouristClub.repositories.StoppingPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoppingPointService {
    private final StoppingPointRepository stoppingPointRepository;


    public List<StoppingPoint> getStoppingPoints() {
        return stoppingPointRepository.findAll();
    }

    public void createStoppingPoint(String stoppingPointName) {
        StoppingPoint stoppingPoint = new StoppingPoint();
        //String stop = null;
        stoppingPoint.setStoppingPointName(stoppingPointName);
        stoppingPointRepository.save(stoppingPoint);
    }


    public void deleteStoppingPoint(String id) {
        stoppingPointRepository.deleteById(Long.parseLong(id));
    }

    public void updateStoppingPoint(long id, String stoppingPointName) {
        StoppingPoint stoppingPoint = stoppingPointRepository.findById(id).get();
        stoppingPoint.setStoppingPointName(stoppingPointName);
        stoppingPointRepository.save(stoppingPoint);
    }
}
