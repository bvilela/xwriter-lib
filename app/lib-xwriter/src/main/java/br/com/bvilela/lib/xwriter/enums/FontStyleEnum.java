package br.com.bvilela.lib.xwriter.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FontStyleEnum {

    BOLD(1, true, false, false, false),
    ITALIC(2, false, true, false, false),
    UNDERLINE(4, false, false, true, false),
    STRIKETHROUGH(8, false, false, false, true);

    private final int code;
    private final boolean bold;
    private final boolean italic;
    private final boolean underline;
    private final boolean strikeThrough;
}
