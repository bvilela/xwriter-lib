package br.com.bvilela.lib.xwriter.strategy;

import br.com.bvilela.lib.xwriter.enums.FontFamilyEnum;
import br.com.bvilela.lib.xwriter.enums.FontStyleEnum;
import br.com.bvilela.lib.xwriter.enums.ImageAlignmentEnum;
import br.com.bvilela.lib.xwriter.enums.PageOrientationEnum;
import br.com.bvilela.lib.xwriter.enums.PageSizeEnum;
import br.com.bvilela.lib.xwriter.model.ChunkParagraph;
import br.com.bvilela.lib.xwriter.model.PageMargin;

import java.io.FileOutputStream;

public interface WriterStrategy<T> {

    T getDocument();

    String getFileExtension();

    Object getFontFamily(FontFamilyEnum fontFamilyEnum);

    void createDocument(
            PageSizeEnum pgSizeEnum,
            PageOrientationEnum pgOrientationEnum,
            PageMargin pgMargin,
            FileOutputStream fileOutputStream);

    void closeDocument(FileOutputStream fileOutputStream);

    void addEmptyParagraph();

    void addParagraph(String text, FontStyleEnum... fontStyleEnum);

    void addParagraph(String text, int fontSize, FontStyleEnum... fontStyleEnum);

    void addParagraph(String text, int fontSize, FontFamilyEnum fontFamilyEnum, FontStyleEnum... fontStyleEnum);

    void addImage(String imagePath, int widthMM, int heightMM, ImageAlignmentEnum alignment);

    void addChunkParagraph(ChunkParagraph... chunkParagraph);

    default ChunkParagraph createChunk(String text, FontStyleEnum... fontStyleEnum) {
        return new ChunkParagraph(text, fontStyleEnum);
    }

    default ChunkParagraph createChunk(String text, int fontSize, FontStyleEnum... fontStyleEnum) {
        return new ChunkParagraph(text, fontSize, fontStyleEnum);
    }

    default ChunkParagraph createChunk(String text, int fontSize, FontFamilyEnum fontFamilyEnum, FontStyleEnum... fontStyleEnum) {
        return new ChunkParagraph(text, fontSize, fontFamilyEnum, fontStyleEnum);
    }

}
