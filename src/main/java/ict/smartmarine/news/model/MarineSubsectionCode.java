package ict.smartmarine.news.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MarineSubsectionCode {

    OVERSEAS("S2N1", "외항", MarineSectionCateogry.SHIPPING),
    HOME_WATER("S2N2", "내항", MarineSectionCateogry.SHIPPING),
    SHIPPING_POLICY("S2N3", "해운정책", MarineSectionCateogry.SHIPPING),
    CREW("S2N4", "선원", MarineSectionCateogry.SHIPPING),
    LINER("S2N5", "여객선", MarineSectionCateogry.SHIPPING),
    FINANCE_INSURANCE("S2N6", "금융/보험", MarineSectionCateogry.SHIPPING),

    LOADING("S2N7", "하역", MarineSectionCateogry.PORT),
    PORT_POLICY("S2N8", "항만정책", MarineSectionCateogry.PORT),
    PORT_DEVELOPMENT("S2N9", "개발", MarineSectionCateogry.PORT),
    INTERNATIONAL("S2N10", "국제", MarineSectionCateogry.PORT),
    DISTRICT("S2N11", "지방", MarineSectionCateogry.PORT),

    CARGO_POLICY("S2N12", "화물정책", MarineSectionCateogry.CARGO),
    MULTIMODAL_TRANSPORT("S2N13", "복합운송", MarineSectionCateogry.CARGO),
    THIRD_PARTY_LOGISTICS("S2N14", "3PL외주", MarineSectionCateogry.CARGO),
    LAND_TRANSPORT("S2N15", "육상운송", MarineSectionCateogry.CARGO);

    private final String code;
    private final String description;
    private final MarineSectionCateogry category;

    MarineSubsectionCode(String code, String description, MarineSectionCateogry category) {
        this.code = code;
        this.description = description;
        this.category = category;
    }

    public String getCode(){
        return code;
    }

    public static MarineSubsectionCode getValue(String code) {
        return Arrays.stream(MarineSubsectionCode.values())
                .filter(c -> c.getCode().equals(code))
                .findAny()
                .orElse(null);
    }

}
