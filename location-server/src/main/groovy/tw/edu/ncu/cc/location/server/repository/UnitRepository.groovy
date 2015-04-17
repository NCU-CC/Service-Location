package tw.edu.ncu.cc.location.server.repository

import org.springframework.data.jpa.repository.JpaRepository
import tw.edu.ncu.cc.location.server.entity.UnitEntity

interface UnitRepository extends JpaRepository< UnitEntity, Integer > {

    List< UnitEntity > findByFullName( String fullName )
}