package br.com.bvilela.lib.xwriter;

import br.com.bvilela.lib.xwriter.enums.*;
import br.com.bvilela.lib.xwriter.model.PageMargin;
import br.com.bvilela.lib.xwriter.strategy.DocxWriterStrategy;
import br.com.bvilela.lib.xwriter.strategy.PdfWriterStrategy;
import br.com.bvilela.lib.xwriter.strategy.WriterStrategy;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.time.LocalDateTime;

class GenerateFileTest {

    @Test
    void generateDocument() {
        writerDocument(new PdfWriterStrategy());
        writerDocument(new DocxWriterStrategy());
    }

    private void writerDocument(WriterStrategy<?> writer) {
        try (var outputStream = new FileOutputStream("test".concat(writer.getFileExtension()))) {

            var pgMargin = new PageMargin(20);
            writer.createDocument(PageSizeEnum.A4, PageOrientationEnum.PORTRAIT, pgMargin, outputStream);

            writer.addParagraph("Hello World - " + LocalDateTime.now());
            writer.addParagraph("I'm bold text", FontStyleEnum.BOLD);
            writer.addParagraph("I'm Italic text", FontStyleEnum.ITALIC);
            writer.addParagraph("I'm Italic and Bold text", FontStyleEnum.ITALIC, FontStyleEnum.BOLD);
            writer.addParagraph("I'm underline text", FontStyleEnum.UNDERLINE);
            writer.addParagraph("I'm strikethru text", FontStyleEnum.STRIKETHROUGH);
            writer.addParagraph("I'm underline, bold and italic text", FontStyleEnum.UNDERLINE, FontStyleEnum.BOLD, FontStyleEnum.ITALIC);
            writer.addParagraph("I'm underline, strikethru, bold and italic text",
                    FontStyleEnum.UNDERLINE, FontStyleEnum.BOLD, FontStyleEnum.ITALIC, FontStyleEnum.STRIKETHROUGH);

            var path = this.getClass().getClassLoader().getResource("sample-image.png").getPath();
            writer.addImage(path, 50, 40, ImageAlignmentEnum.LEFT);
            writer.addImage(path, 50, 40, ImageAlignmentEnum.MIDDLE);
            writer.addImage(path, 50, 40, ImageAlignmentEnum.RIGHT);

            writer.addParagraph("Font Courier, 10", 10, FontFamilyEnum.COURIER, FontStyleEnum.ITALIC);
            writer.addParagraph("Font Helvetica, 12", 12,FontFamilyEnum.HELVETICA, FontStyleEnum.UNDERLINE);
            writer.addParagraph("Font Times, 14", 14, FontFamilyEnum.TIMES_ROMAN, FontStyleEnum.BOLD);
            writer.addParagraph("Font Default, 8", 8);

            var chunk1 = writer.createChunk("Text Normal ");
            var chunk2 = writer.createChunk("Text Normal, 18 ", 18);
            var chunk3 = writer.createChunk("Text Bold, 14, Helvetiva", 14, FontFamilyEnum.HELVETICA, FontStyleEnum.BOLD);
            writer.addEmptyParagraph();
            writer.addChunkParagraph(chunk1, chunk2, chunk3);

            writer.addEmptyParagraph();
            var chunkA = writer.createChunk("Exemplo que texto com uma palavra em ");
            var chunkB = writer.createChunk("NEGRITO (14) ", 14, FontStyleEnum.BOLD);
            var chunkC = writer.createChunk("e o restante normal.");
            writer.addChunkParagraph(chunkA, chunkB, chunkC);

            writer.closeDocument(outputStream);

        } catch (Exception e) {
            System.out.println("Falha");
            System.out.println(e.getMessage());
        }
    }

}
