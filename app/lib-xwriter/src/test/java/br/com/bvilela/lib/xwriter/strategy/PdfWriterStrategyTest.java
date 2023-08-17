package br.com.bvilela.lib.xwriter.strategy;

import br.com.bvilela.lib.xwriter.enums.PageOrientationEnum;
import br.com.bvilela.lib.xwriter.enums.PageSizeEnum;
import br.com.bvilela.lib.xwriter.model.PageMargin;
import com.itextpdf.text.PageSize;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PdfWriterStrategyTest {

    private final PdfWriterStrategy service = new PdfWriterStrategy();

    @SneakyThrows
    @Test
    void createDocumentA4Portrait() {
        var pgMargin = new PageMargin(1);
        service.createDocument(PageSizeEnum.A4, PageOrientationEnum.PORTRAIT, pgMargin, new FileOutputStream("test"));
        assertEquals(PageSize.A4, service.getDocument().getPageSize());
    }

    @SneakyThrows
    @Test
    void createDocumentA4Landscape() {
        var pgMargin = new PageMargin(1);
        service.createDocument(PageSizeEnum.A4, PageOrientationEnum.LANDSCAPE, pgMargin, new FileOutputStream("test"));
        assertEquals(PageSize.A4.rotate(), service.getDocument().getPageSize());
    }

    @SneakyThrows
    @Test
    void createDocumentLetterPortrait() {
        var pgMargin = new PageMargin(1);
        service.createDocument(PageSizeEnum.LETTER, PageOrientationEnum.PORTRAIT, pgMargin, new FileOutputStream("test"));
        assertEquals(PageSize.LETTER, service.getDocument().getPageSize());
    }

    @SneakyThrows
    @Test
    void createDocumentLetterLandscape() {
        var pgMargin = new PageMargin(1);
        service.createDocument(PageSizeEnum.LETTER, PageOrientationEnum.LANDSCAPE, pgMargin, new FileOutputStream("test"));
        assertEquals(PageSize.LETTER.rotate(), service.getDocument().getPageSize());
    }

}
