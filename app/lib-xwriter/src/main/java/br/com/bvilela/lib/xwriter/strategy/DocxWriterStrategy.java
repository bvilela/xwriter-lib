package br.com.bvilela.lib.xwriter.strategy;

import br.com.bvilela.lib.xwriter.config.DefaultConfig;
import br.com.bvilela.lib.xwriter.enums.*;
import br.com.bvilela.lib.xwriter.model.ChunkParagraph;
import br.com.bvilela.lib.xwriter.model.PageMargin;
import br.com.bvilela.lib.xwriter.utils.AppUtils;
import br.com.bvilela.lib.xwriter.utils.DocxUtils;
import lombok.SneakyThrows;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class DocxWriterStrategy implements WriterStrategy<XWPFDocument> {

    private XWPFDocument document;

    @Override
    public XWPFDocument getDocument() {
        return this.document;
    }

    @Override
    public String getFileExtension() {
        return DocumentTypeEnum.DOCX.getExtension();
    }

    @Override
    public String getFontFamily(FontFamilyEnum fontFamilyEnum) {
        if (fontFamilyEnum.equals(FontFamilyEnum.TIMES_ROMAN)) {
            return "Times New Roman";
        }
        return fontFamilyEnum.toString();
    }

    @Override
    public void createDocument(
            PageSizeEnum pgSizeEnum,
            PageOrientationEnum pgOrientationEnum,
            PageMargin pgMargin,
            FileOutputStream fileOutputStream) {

        document = new XWPFDocument();

        var pageDetails = DocxUtils.getPageSize(pgSizeEnum, pgOrientationEnum);
        setPageOrientation(pageDetails.getPageOrientation());
        setPageSize(pageDetails.getWidth(), pageDetails.getHeight());
        setPageMargin(pgMargin);
    }

    @SneakyThrows
    @Override
    public void closeDocument(FileOutputStream fileOutputStream) {
        document.write(fileOutputStream);
        document.close();
    }

    @Override
    public void addEmptyParagraph() {
        createParagraph();
    }

    @Override
    public void addParagraph(String text, FontStyleEnum... fontStyleEnum) {
        addParagraph(text, DefaultConfig.FONT_SIZE, DefaultConfig.FONT_FAMILY, fontStyleEnum);
    }

    @Override
    public void addParagraph(String text, int fontSize, FontStyleEnum... fontStyleEnum) {
        addParagraph(text, fontSize, DefaultConfig.FONT_FAMILY, fontStyleEnum);
    }

    @Override
    public void addParagraph(String text, int fontSize, FontFamilyEnum fontFamilyEnum, FontStyleEnum... fontStyleEnum) {
        var run = createParagraph().createRun();
        setRun(run, text, fontFamilyEnum, fontSize, fontStyleEnum);
    }

    @SneakyThrows
    @Override
    public void addImage(String imagePath, int widthMM, int heightMM, ImageAlignmentEnum alignment) {
        var paragraph = createParagraph();
        paragraph.setAlignment(getParagraphAlignment(alignment));
        try (FileInputStream is = new FileInputStream(imagePath);) {
            var widthInPoints = AppUtils.convertMillimeterToPoint(widthMM);
            var heightInPoints = AppUtils.convertMillimeterToPoint(heightMM);
            var run = paragraph.createRun();
            run.addPicture(
                    is,
                    Document.PICTURE_TYPE_JPEG,
                    imagePath,
                    Units.toEMU(widthInPoints),
                    Units.toEMU(heightInPoints));
        }
    }

    @Override
    public void addChunkParagraph(ChunkParagraph... chunkParagraph) {
        var paragraph = createParagraph();
        Arrays.stream(chunkParagraph).forEach(e -> {
            var run = paragraph.createRun();
            setRun(run, e.getText(), e.getFontFamilyEnum(), e.getFontSize(), e.getFontStyleEnum());
        });
    }

    private XWPFParagraph createParagraph() {
        var p = document.createParagraph();
        p.setSpacingBefore(0);
        p.setSpacingAfter(0);
        p.setIndentationLeft(0);
        p.setIndentationRight(0);
        return p;
    }

    private ParagraphAlignment getParagraphAlignment(ImageAlignmentEnum alignment) {
        if (ImageAlignmentEnum.LEFT.equals(alignment)) {
            return ParagraphAlignment.LEFT;
        }
        if (ImageAlignmentEnum.RIGHT.equals(alignment)) {
            return ParagraphAlignment.RIGHT;
        }
        return ParagraphAlignment.CENTER;
    }

    private void setPageOrientation(STPageOrientation.Enum pageOrientation) {
        document.getDocument().getBody().addNewSectPr().addNewPgSz().setOrient(pageOrientation);
    }

    private void setPageSize(float width, float height) {
        CTPageSz pageSize = document.getDocument().getBody().addNewSectPr().addNewPgSz();
        pageSize.setW(width);
        pageSize.setH(height);
    }

    private void setPageMargin(PageMargin pgMargin) {
        CTPageMar pageMar = document.getDocument().getBody().addNewSectPr().addNewPgMar();
        pageMar.setLeft(DocxUtils.getDocxSizeFromPoints(pgMargin.getLeftInPoints()));
        pageMar.setTop(DocxUtils.getDocxSizeFromPoints(pgMargin.getTopInPoints()));
        pageMar.setRight(DocxUtils.getDocxSizeFromPoints(pgMargin.getRightInPoints()));
        pageMar.setBottom(DocxUtils.getDocxSizeFromPoints(pgMargin.getLeftInPoints()));
    }

    private void setRun(XWPFRun run, String text, FontFamilyEnum fontFamilyEnum, int fontSize, FontStyleEnum[] fontStyleEnum) {
        run.setText(text);
        run.setFontFamily(getFontFamily(fontFamilyEnum));
        run.setFontSize(fontSize);
        Arrays.stream(fontStyleEnum).forEach(e -> {
            var isBold = run.isBold() || e.isBold();
            var isItalic = run.isItalic() || e.isItalic();
            var isUnderline = !run.getUnderline().equals(UnderlinePatterns.NONE) || e.isUnderline();
            var isStrikeThrough = run.isStrikeThrough() || e.isStrikeThrough();
            run.setBold(isBold);
            run.setItalic(isItalic);
            run.setUnderline(isUnderline ? UnderlinePatterns.SINGLE : UnderlinePatterns.NONE);
            run.setStrikeThrough(isStrikeThrough);
        });
    }

}
