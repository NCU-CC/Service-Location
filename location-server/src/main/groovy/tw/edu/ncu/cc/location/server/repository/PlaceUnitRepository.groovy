package tw.edu.ncu.cc.location.server.repository

import org.springframework.data.jpa.repository.JpaRepository
import tw.edu.ncu.cc.location.server.entity.PlaceUnitEntity


interface PlaceUnitRepository extends JpaRepository< PlaceUnitEntity, Integer > {

    List< PlaceUnitEntity > findByPlaceChineseName( String chineseName )
}
