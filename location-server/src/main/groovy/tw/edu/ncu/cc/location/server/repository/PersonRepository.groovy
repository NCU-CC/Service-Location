package tw.edu.ncu.cc.location.server.repository

import org.springframework.data.jpa.repository.JpaRepository
import tw.edu.ncu.cc.location.server.entity.PersonEntity


interface PersonRepository extends JpaRepository< PersonEntity, Integer > {

    List< PersonEntity > findByChineseName( String cname )

    PersonEntity findByPortalId( String portalId )

}