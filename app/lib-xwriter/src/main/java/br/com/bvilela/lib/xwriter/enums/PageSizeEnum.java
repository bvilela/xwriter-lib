package br.com.bvilela.lib.xwriter.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PageSizeEnum {

    LETTER(612.0F, 792.0F),
    TABLOID(792.0F, 1224.0F),
    A4(595.0F, 842.0F),
    A5(420.0F, 595.0F);

    private final float width;
    private final float height;
}
