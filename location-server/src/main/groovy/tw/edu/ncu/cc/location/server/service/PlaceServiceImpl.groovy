package tw.edu.ncu.cc.location.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.entity.PlaceEntity
import tw.edu.ncu.cc.location.server.repository.PlaceRepository
import tw.edu.ncu.cc.location.server.repository.PlaceUnitRepository

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    def PlaceRepository placeRepository

    @Autowired
    def PlaceUnitRepository placeUnitRepository

    @Override
    public List< PlaceEntity > getPlaces( String chineseName ) {
        placeRepository.findShowablePlaceByChineseName( chineseName )
    }

    @Override
    public List< PlaceEntity > getPlaces( PlaceType type ) {
        placeRepository.findByTypeOrderByChineseNameAsc( type )
    }

}
