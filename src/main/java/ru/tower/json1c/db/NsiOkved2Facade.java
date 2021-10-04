package ru.tower.json1c.db;

import ru.tower.purchase.entity.nsi.NsiOkved2;

import static java.lang.String.format;
import static ru.tower.json1c.db.QueryParam.param;

public class NsiOkved2Facade extends AbstractFacade <NsiOkved2, Long > {

    public NsiOkved2Facade() {
        super(NsiOkved2.class);
    }

    public NsiOkved2 findByCode(String code) {
        return selectNamed("NsiOkved2.findByCode", param("code", code)).stream()
                .findFirst().orElseThrow(() -> new RuntimeException(format("Не найдено значение в 'NSI_OKVED2' по коду '%s'", code)));
    }
}
