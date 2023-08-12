package br.com.bvilela.lib.xwriter.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUtils {

    public static float convertMillimeterToPoint(int millimeter) {
        final float ONE_MM_IN_POINT = 2.83465f;
        return millimeter * ONE_MM_IN_POINT;
    }
}
