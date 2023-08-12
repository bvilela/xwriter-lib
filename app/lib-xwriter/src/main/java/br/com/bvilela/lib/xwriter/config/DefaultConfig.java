package br.com.bvilela.lib.xwriter.config;

import br.com.bvilela.lib.xwriter.enums.FontFamilyEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultConfig {

    public static final FontFamilyEnum FONT_FAMILY = FontFamilyEnum.TIME_ROMAN;
    public static final int FONT_SIZE = 12;
}
