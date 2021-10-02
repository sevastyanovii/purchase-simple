package ru.tower.json1c.db;

import ru.tower.purchase.entity.nsi.NsiAstCurrency;

import static java.lang.String.format;
import static ru.tower.json1c.db.QueryParam.param;

public class NsiAstCurrencyFacade extends AbstractFacade <NsiAstCurrency, Long> {

    public NsiAstCurrency findCurrency(String currencyCode) {
        return selectNamed("NsiAstCurrency.findCurrency", param("code", currencyCode)).stream().findFirst()
                .orElseThrow(() -> new RuntimeException(format("Валюта с кодом '%s' не найдена в справочнике 'NSI_AST_CURRENCY'", currencyCode)));
    }
}
