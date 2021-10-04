package ru.tower.json1c.db;

import ru.tower.purchase.entity.nsi.NsiOkpd2;

import static java.lang.String.format;
import static ru.tower.json1c.db.QueryParam.param;

public class NsiOkpd2Facade extends AbstractFacade <NsiOkpd2, Long> {

    public NsiOkpd2Facade() {
        super(NsiOkpd2.class);
    }

    public NsiOkpd2 findByCode(String code) {
        return selectNamed("NsiOkpd2.findByCode", param("code", code))
                .stream().findFirst().orElseThrow(() -> new RuntimeException(format("Не найдено значение 'NSI_OKPD2' по коду '%s'")));
    }
}
