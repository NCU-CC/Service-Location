package tw.edu.ncu.cc.location.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tw.edu.ncu.cc.location.server.entity.PlaceUnitEntity
import tw.edu.ncu.cc.location.server.repository.PlaceUnitRepository

@Service
class PlaceUnitServiceImpl implements PlaceUnitService {

    @Autowired
    def PlaceUnitRepository placeUnitRepository

    @Override
    List< PlaceUnitEntity > getAllPlaceUnits() {
        placeUnitRepository.findAll()
    }

}
