package tw.edu.ncu.cc.location.server.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.entity.PlaceEntity

interface PlaceRepository extends JpaRepository< PlaceEntity, Integer > {

    @Query( "select p from PlaceEntity p where p.chineseName = ?1 and p.type in ( 'RESTAURANT', 'SPORT_RECREATION', 'ADMINISTRATION', 'RESEARCH', 'DORMITORY', 'OTHER' )" )
    List< PlaceEntity > findShowablePlaceByChineseName( String chineseName )

    @Query( "select p from PlaceEntity p where p.type in ( 'RESTAURANT', 'SPORT_RECREATION', 'ADMINISTRATION', 'RESEARCH', 'DORMITORY', 'OTHER' )" )
    Page< PlaceEntity > findShowablePlace( Pageable pageable )

    List< PlaceEntity > findByTypeOrderByChineseNameAsc( PlaceType type )

}