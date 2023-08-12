package br.com.bvilela.lib.xwriter.model;

import br.com.bvilela.lib.xwriter.utils.AppUtils;
import lombok.Getter;

@Getter
public class PageMargin {

    private float leftInPoints;
    private float rightInPoints;
    private float topInPoints;
    private float bottomInPoints;

    public PageMargin(int marginsInMM) {
        setMargins(marginsInMM, marginsInMM, marginsInMM, marginsInMM);
    }

    public PageMargin(int widthInMM, int heightInMM) {
        setMargins(widthInMM, widthInMM, heightInMM, heightInMM);
    }

    public PageMargin(int leftInMM, int rightInMM, int topInMM, int bottomInMM) {
        setMargins(leftInMM, rightInMM, topInMM, bottomInMM);
    }

    private void setMargins(int leftInMM, int rightInMM, int topInMM, int bottomInMM) {
        this.leftInPoints = AppUtils.convertMillimeterToPoint(leftInMM);
        this.rightInPoints = AppUtils.convertMillimeterToPoint(rightInMM);
        this.topInPoints = AppUtils.convertMillimeterToPoint(topInMM);
        this.bottomInPoints = AppUtils.convertMillimeterToPoint(bottomInMM);
    }

}
