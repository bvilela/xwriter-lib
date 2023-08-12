package br.com.bvilela.lib.xwriter.utils;

import br.com.bvilela.lib.xwriter.enums.PageOrientationEnum;
import br.com.bvilela.lib.xwriter.enums.PageSizeEnum;
import br.com.bvilela.lib.xwriter.model.docx.PageSizeDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocxUtils {

    public static float getDocxSizeFromPoints(float points) {
        final float SIZE_SCALE = 20.0f;
        return points * SIZE_SCALE;
    }

    public static PageSizeDTO getPageSize(PageSizeEnum pgSizeEnum, PageOrientationEnum pgOrientationEnum) {
        if (PageOrientationEnum.LANDSCAPE.equals(pgOrientationEnum)) {
            return PageSizeDTO.builder()
                    .pageOrientation(STPageOrientation.LANDSCAPE)
                    .height(pgSizeEnum.getWidth())
                    .width(pgSizeEnum.getHeight())
                    .build();
        }
        return PageSizeDTO.builder()
                .pageOrientation(STPageOrientation.PORTRAIT)
                .height(pgSizeEnum.getHeight())
                .width(pgSizeEnum.getWidth())
                .build();
    }

}
