package ru.tower.purchase.entity;

/**
 * Created with IntelliJ IDEA.
 * User: AnDrunkYeti
 * Date: 16.01.18
 * Time: 20:21
 */
public enum NMCKInstructions {

    /**
     * INFORMATION - Сведения о начальной (максимальной) цене договора
     FORMULA - Формула цены и максимальное значение цены договора
     PRICE - Цена товара, работы, услуги и максимальное значение цены договора
     */
    INFORMATION("INFORMATION", "Сведения о начальной максимальной цене договора"),
    FORMULA("FORMULA", "Формула цены и максимальное значение цены договора"),
    PRICE("PRICE", "Цена товара, работы, услуги и максимальное значение цены договора");

    private final String name;
    private final String rusName;

    NMCKInstructions(String name, String rusName) {
        this.name = name;
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }

    public String getName() {
        return name;
    }

    public static NMCKInstructions fromValue(String v) {
        return valueOf(v);
    }

    public static NMCKInstructions lookup(String name) {
        NMCKInstructions[] nMCKInstructions = NMCKInstructions.values();
        for (int i = 0; i < nMCKInstructions.length; i++) {
            if (nMCKInstructions[i].getName().equals(name)) {
                return nMCKInstructions[i];
            }
        }
        throw new IllegalArgumentException();
    }
}
