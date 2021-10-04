package ru.tower.json1c.db;

import ru.tower.purchase.entity.nsi.NsiOkei;

import static java.lang.String.format;
import static ru.tower.json1c.db.QueryParam.param;

public class NsiOkeiFacade extends AbstractFacade <NsiOkei, Long> {

    public NsiOkeiFacade() {
        super(NsiOkei.class);
    }

    public NsiOkei findByCode(String code) {
        return selectNamed("NsiOkei.findByCode", param("code", code))
                .stream().findFirst().orElseThrow(() -> new RuntimeException(format("Не найдено значение 'NSI_OKEI' по коду '%s'", code)));
    }
}
