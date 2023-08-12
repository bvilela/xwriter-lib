package br.com.bvilela.lib.xwriter.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.processing.Generated;

@Getter
@RequiredArgsConstructor
public enum DocumentTypeEnum {

    PDF(".pdf"),
    DOCX(".docx");

    private final String extension;
}
