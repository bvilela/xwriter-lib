package br.com.bvilela.lib.xwriter.model.docx;

import br.com.bvilela.lib.xwriter.utils.DocxUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageSizeDTO {

    private STPageOrientation.Enum pageOrientation;
    private float width;
    private float height;

    public static class PageSizeDTOBuilder {
        private float width;
        private float height;

        public PageSizeDTOBuilder width(float width) {
            this.width = DocxUtils.getDocxSizeFromPoints(width);
            return this;
        }

        public PageSizeDTOBuilder height(float height) {
            this.height = DocxUtils.getDocxSizeFromPoints(height);
            return this;
        }
    }

}
