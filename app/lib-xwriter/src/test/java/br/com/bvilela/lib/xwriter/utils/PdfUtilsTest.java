package br.com.bvilela.lib.xwriter.utils;

import br.com.bvilela.lib.xwriter.enums.PageOrientationEnum;
import br.com.bvilela.lib.xwriter.enums.PageSizeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PdfUtilsTest {

    @Test
    void pageSizeA4Portrait() {
        var pgSize = PdfUtils.getPageSize(PageSizeEnum.A4, PageOrientationEnum.PORTRAIT);
        assertEquals(PageSizeEnum.A4.getHeight(), pgSize.getHeight());
        assertEquals(PageSizeEnum.A4.getWidth(), pgSize.getWidth());
    }

    @Test
    void pageSizeA4Landscape() {
        var pgSize = PdfUtils.getPageSize(PageSizeEnum.A4, PageOrientationEnum.LANDSCAPE);
        assertEquals(PageSizeEnum.A4.getHeight(), pgSize.getWidth());
        assertEquals(PageSizeEnum.A4.getWidth(), pgSize.getHeight());
    }
}
