package tw.edu.ncu.cc.location.server.service

import tw.edu.ncu.cc.location.server.entity.UnitEntity

public interface UnitService {
    public List< UnitEntity > getUnitsByFullName( String fullName );
    public List< UnitEntity > getUnitsByPlaceChineseName( String chineseName );
}
