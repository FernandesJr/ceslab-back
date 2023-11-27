package br.com.ceslab.ceslab.enums;

import java.time.DateTimeException;

public enum MonthsPtBr {
    /**
     * This has the numeric value of {@code 1}.
     */
    JANEIRO,
    /**
     * This has the numeric value of {@code 2}.
     */
    FEVEREIRO,
    /**
     * This has the numeric value of {@code 3}.
     */
    MARÇO,
    /**
     * This has the numeric value of {@code 4}.
     */
    ABRIL,
    /**
     * This has the numeric value of {@code 5}.
     */
    MAIO,
    /**
     * This has the numeric value of {@code 6}.
     */
    JUNHO,
    /**
     * This has the numeric value of {@code 7}.
     */
    JULHO,
    /**
     * This has the numeric value of {@code 8}.
     */
    AGOSTO,
    /**
     * This has the numeric value of {@code 9}.
     */
    SETEMBRO,
    /**
     * This has the numeric value of {@code 10}.
     */
    OUTUBRO,
    /**
     * This has the numeric value of {@code 11}.
     */
    NOVEMBRO,
    /**
     * This has the numeric value of {@code 12}.
     */
    DEZEMBRO;

    private static final MonthsPtBr[] ENUMS = MonthsPtBr.values();

    public static MonthsPtBr of(int month) {
        if (month < 1 || month > 12) {
            throw new DateTimeException("Invalid value for MonthOfYear: " + month);
        }
        return ENUMS[month - 1];
    }
}
