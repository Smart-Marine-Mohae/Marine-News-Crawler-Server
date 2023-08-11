package ict.smartmarine.news.model;

import lombok.Getter;

@Getter
public enum MarineSectionCateogry {
    SHIPPING("해운"),
    PORT("항만"),
    CARGO("Cargo Korea");


    private final String category;

    MarineSectionCateogry(String category) {
        this.category = category;
    }
}
