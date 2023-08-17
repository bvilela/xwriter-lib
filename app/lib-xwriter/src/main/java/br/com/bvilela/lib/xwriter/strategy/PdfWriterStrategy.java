package br.com.bvilela.lib.xwriter.strategy;

import br.com.bvilela.lib.xwriter.config.DefaultConfig;
import br.com.bvilela.lib.xwriter.enums.*;
import br.com.bvilela.lib.xwriter.model.ChunkParagraph;
import br.com.bvilela.lib.xwriter.model.PageMargin;
import br.com.bvilela.lib.xwriter.utils.AppUtils;
import br.com.bvilela.lib.xwriter.utils.PdfUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.util.Arrays;

public class PdfWriterStrategy implements WriterStrategy<Document> {

    private Document document;

    @Override
    public Document getDocument() {
        return this.document;
    }

    @Override
    public String getFileExtension() {
        return DocumentTypeEnum.PDF.getExtension();
    }

    @Override
    public Font.FontFamily getFontFamily(FontFamilyEnum fontFamilyEnum) {
        return Font.FontFamily.valueOf(fontFamilyEnum.toString());
    }

    @SneakyThrows
    @Override
    public void createDocument(
            PageSizeEnum pgSizeEnum,
            PageOrientationEnum pgOrientationEnum,
            PageMargin pgMargin,
            FileOutputStream fileOutputStream) {

        document = new Document();

        Rectangle pageSize = PdfUtils.getPageSize(pgSizeEnum, pgOrientationEnum);
        document.setPageSize(pageSize);
        setMargins(document, pgMargin);

        PdfWriter.getInstance(document, fileOutputStream);
        document.open();
    }

    @Override
    public void closeDocument(FileOutputStream fileOutputStream) {
        document.close();
    }

    @SneakyThrows
    @Override
    public void addEmptyParagraph() {
        document.add(new Paragraph(" "));
    }

    @Override
    public void addParagraph(String text, FontStyleEnum... fontStyleEnum) {
        addParagraph(text, DefaultConfig.FONT_SIZE, DefaultConfig.FONT_FAMILY, fontStyleEnum);
    }

    @Override
    public void addParagraph(String text, int fontSize, FontStyleEnum... fontStyleEnum) {
        addParagraph(text, fontSize, DefaultConfig.FONT_FAMILY, fontStyleEnum);
    }

    @SneakyThrows
    @Override
    public void addParagraph(String text, int fontSize, FontFamilyEnum fontFamily, FontStyleEnum... fontStyleEnum) {
        var font = getFont(fontFamily, fontSize, fontStyleEnum);
        document.add(new Paragraph(text, font));
    }

    @SneakyThrows
    @Override
    public void addImage(String imagePath, int widthMM, int heightMM, ImageAlignmentEnum alignment) {
        Image image = Image.getInstance(imagePath);
        var widthInPoints = AppUtils.convertMillimeterToPoint(widthMM);
        var heightInPoints = AppUtils.convertMillimeterToPoint(heightMM);
        image.scaleAbsolute(widthInPoints, heightInPoints);
        image.setAlignment(getImageAlignment(alignment));
        //TODO: remover se de fato não for usar
//        image.setSpacingBefore(0);
//        image.setSpacingAfter(0);
//        image.setBorder(Rectangle.NO_BORDER);
//        image.setPaddingTop(0);
        document.add(image);
    }

    @SneakyThrows
    @Override
    public void addChunkParagraph(ChunkParagraph... chunkParagraph) {
        var paragraph = new Paragraph();
        var phrase = new Phrase();

        Arrays.stream(chunkParagraph).forEach(e -> {
            var font = getFont(e.getFontFamilyEnum(), e.getFontSize(), e.getFontStyleEnum());
            var chunk = new Chunk(e.getText(), font);
            phrase.add(chunk);
        });

        paragraph.add(phrase);
        document.add(paragraph);
    }


    private int getImageAlignment(ImageAlignmentEnum alignment) {
        if (ImageAlignmentEnum.LEFT.equals(alignment)) {
            return Image.LEFT;
        }
        if (ImageAlignmentEnum.RIGHT.equals(alignment)) {
            return Image.RIGHT;
        }
        return Image.MIDDLE;
    }

    private void setMargins(Document doc, PageMargin pgMargin) {
        doc.setMargins(
                pgMargin.getLeftInPoints(),
                pgMargin.getRightInPoints(),
                pgMargin.getTopInPoints(),
                pgMargin.getBottomInPoints());
    }

    private int getFontStyle(FontStyleEnum... fontStyle) {
        return Arrays.stream(fontStyle)
                .map(FontStyleEnum::getCode)
                .reduce(0, Integer::sum);
    }

    private Font getFont(FontFamilyEnum fontFamily, int fontSize, FontStyleEnum... fontStyleEnum) {
        var fontStyle = getFontStyle(fontStyleEnum);
        return new Font(getFontFamily(fontFamily), fontSize, fontStyle);
    }

}
