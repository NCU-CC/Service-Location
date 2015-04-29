package tw.edu.ncu.cc.location.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tw.edu.ncu.cc.location.server.entity.PlaceUnitEntity
import tw.edu.ncu.cc.location.server.entity.UnitEntity
import tw.edu.ncu.cc.location.server.repository.PlaceUnitRepository
import tw.edu.ncu.cc.location.server.repository.UnitRepository

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    def UnitRepository unitRepository

    @Autowired
    def PlaceUnitRepository placeUnitRepository

    @Override
    public List< UnitEntity > getUnitsByFullName( String fullName ) {
        unitRepository.findByFullName( fullName )
    }

    @Override
    public List< UnitEntity > getUnitsByPlaceChineseName( String chineseName ) {
        List< PlaceUnitEntity > placeUnits = placeUnitRepository.findByPlaceChineseName( chineseName )
        List< UnitEntity > units = findUnitsByPlaceUnits( placeUnits )

        return inplaceSortUnitsByChineseNameAsc( units )
    }

    private static List< UnitEntity > findUnitsByPlaceUnits( List< PlaceUnitEntity > placeUnits ) {
        List< UnitEntity > units = new ArrayList<>()
        for ( PlaceUnitEntity placeUnit : placeUnits ) {
            if( placeUnit.getUnit() == null ) {
                UnitEntity unitEntity = new UnitEntity()
                unitEntity.setChineseName( placeUnit.getUnitName() )
                units.add( unitEntity )
            } else {
                units.add( placeUnit.getUnit() )
            }
        }
        return units
    }

    private static List< UnitEntity > inplaceSortUnitsByChineseNameAsc( List< UnitEntity > units ) {
        Collections.sort( units, new Comparator< UnitEntity >() {
            @Override
            int compare( UnitEntity o1, UnitEntity o2 ) {
                o1.chineseName.compareTo( o2.chineseName )
            }
            @Override
            boolean equals( Object obj ) {
                return false
            }
        } )
        return units
    }

}
