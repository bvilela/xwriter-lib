package br.com.bvilela.lib.xwriter.model;

import br.com.bvilela.lib.xwriter.config.DefaultConfig;
import br.com.bvilela.lib.xwriter.enums.FontFamilyEnum;
import br.com.bvilela.lib.xwriter.enums.FontStyleEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChunkParagraph {

    private String text;
    private FontFamilyEnum fontFamilyEnum;
    private int fontSize;
    private FontStyleEnum[] fontStyleEnum;

    public ChunkParagraph(String text, FontStyleEnum... fontStyleEnum) {
        this.text = text;
        this.fontFamilyEnum = DefaultConfig.FONT_FAMILY;
        this.fontSize = DefaultConfig.FONT_SIZE;
        this.fontStyleEnum = fontStyleEnum;
    }

    public ChunkParagraph(String text, int fontSize, FontStyleEnum... fontStyleEnum) {
        this.text = text;
        this.fontFamilyEnum = DefaultConfig.FONT_FAMILY;
        this.fontSize = fontSize;
        this.fontStyleEnum = fontStyleEnum;
    }

    public ChunkParagraph(String text, int fontSize, FontFamilyEnum fontFamilyEnum, FontStyleEnum... fontStyleEnum) {
        this.text = text;
        this.fontFamilyEnum = fontFamilyEnum;
        this.fontSize = fontSize;
        this.fontStyleEnum = fontStyleEnum;
    }
}
