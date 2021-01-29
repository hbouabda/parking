package com.example.parking.mapper;

/*@Mapper(config = MapStructConfig.class, uses = {ProgramCompanyMapper.class, KeyPointMapper.class,
        PreliminaryAnalysisMapper.class, SubthemeMapper.class, ProgramDefenseMapper.class,
        EnumMapper.class})*/
public interface PreliminaryAnalysisElementMapper {

    /*@Mapping(source = "analysisId", target = "preliminaryAnalysis.id")
    @Mapping(source = "programCompanyId", target = "supervisionProgramCompany", qualifiedByName = "mapProgramCompanyId")
    @Mapping(source = "subthemeId", target = "subthemeV3", qualifiedByName = "mapSubthemeV3Id")
    @Mapping(source = "subthemeId", target = "subthemeV4", qualifiedByName = "mapSubthemeV4Id")
    @Mapping(source = "keyPointId", target = "keyPoint", qualifiedByName = "mapKeyPointId")
    @Mapping(target = "programDefense", ignore = true)
    PreliminaryAnalysisElement toModelLite(PreliminaryAnalysisElementLiteDto preliminaryAnalysisElementLiteDto);

    PreliminaryAnalysisElement toModel(PreliminaryAnalysisElementDto preliminaryAnalysisElementDto);

    @Named("mapProgramCompanyId")
    default ProgramCompany mapProgramCompanyId(Integer programCompanyId) {

        if (programCompanyId == null) {
            return null;
        } else {
            ProgramCompany programCompany = new ProgramCompany();
            programCompany.setId(programCompanyId);
            return programCompany;
        }
    }

    @Named("mapSubthemeV3Id")
    default Subtheme mapSubthemeV3Id(Integer subthemeId) {

        if (subthemeId == null) {
            return null;
        } else {
            Subtheme subtheme = new Subtheme();
            subtheme.setId(subthemeId);
            return subtheme;
        }
    }

    @Named("mapSubthemeV4Id")
    default Subtheme mapSubthemeV4Id(Integer subthemeId) {

        if (subthemeId == null) {
            return null;
        } else {
            Subtheme subtheme = new Subtheme();
            subtheme.setId(subthemeId);
            return subtheme;
        }
    }

    @Named("mapKeyPointId")
    default KeyPoint mapKeyPointId(Integer keyPointId) {

        if (keyPointId == null) {
            return null;
        } else {
            KeyPoint keyPoint = new KeyPoint();
            keyPoint.setId(keyPointId);
            return keyPoint;
        }
    }

    List<PreliminaryAnalysisElementDto> toDtos(Iterable<PreliminaryAnalysisElement> models);

    PreliminaryAnalysisElementDto toDto(PreliminaryAnalysisElement model);*/
}
