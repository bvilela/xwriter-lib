package br.com.bvilela.lib.xwriter.utils;

import br.com.bvilela.lib.xwriter.enums.PageOrientationEnum;
import br.com.bvilela.lib.xwriter.enums.PageSizeEnum;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PdfUtils {

    public static Rectangle getPageSize(PageSizeEnum pgSizeEnum, PageOrientationEnum pgOrientationEnum) {
        var rectangle = (Rectangle) new RectangleReadOnly(pgSizeEnum.getWidth(), pgSizeEnum.getHeight());
        if (PageOrientationEnum.LANDSCAPE.equals(pgOrientationEnum)) {
            return rectangle.rotate();
        }
        return rectangle;
    }

}
