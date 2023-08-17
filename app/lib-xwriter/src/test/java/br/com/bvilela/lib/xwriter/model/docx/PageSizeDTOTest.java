package br.com.bvilela.lib.xwriter.model.docx;

import br.com.bvilela.lib.xwriter.enums.PageOrientationEnum;
import br.com.bvilela.lib.xwriter.enums.PageSizeEnum;
import br.com.bvilela.lib.xwriter.utils.DocxUtils;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PageSizeDTOTest {


    @Test
    void pageA4Portrait() {
        var pageSize = DocxUtils.getPageSize(PageSizeEnum.A4, PageOrientationEnum.PORTRAIT);
        assertEquals(STPageOrientation.PORTRAIT, pageSize.getPageOrientation());
        assertEquals(11900.0, pageSize.getWidth());
        assertEquals(16840.0, pageSize.getHeight());
    }

    @Test
    void pageA4Landscape() {
        var pageSize = DocxUtils.getPageSize(PageSizeEnum.A4, PageOrientationEnum.LANDSCAPE);
        assertEquals(STPageOrientation.LANDSCAPE, pageSize.getPageOrientation());
        assertEquals(11900.0, pageSize.getHeight());
        assertEquals(16840.0, pageSize.getWidth());
    }

    @Test
    void pageA5Portrait() {
        var pageSize = DocxUtils.getPageSize(PageSizeEnum.A5, PageOrientationEnum.PORTRAIT);
        assertEquals(STPageOrientation.PORTRAIT, pageSize.getPageOrientation());
        assertEquals(8400.0, pageSize.getWidth());
        assertEquals(11900.0, pageSize.getHeight());
    }

    @Test
    void pageA5Landscape() {
        var pageSize = DocxUtils.getPageSize(PageSizeEnum.A5, PageOrientationEnum.LANDSCAPE);
        assertEquals(STPageOrientation.LANDSCAPE, pageSize.getPageOrientation());
        assertEquals(8400.0, pageSize.getHeight());
        assertEquals(11900.0, pageSize.getWidth());
    }

}

