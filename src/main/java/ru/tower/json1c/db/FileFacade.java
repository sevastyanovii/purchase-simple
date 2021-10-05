package ru.tower.json1c.db;

import ru.tower.purchase.entity.File;
import ru.tower.purchase.entity.VersionId;

import java.math.BigInteger;

public class FileFacade extends AbstractFacade <File, VersionId> {

    public FileFacade() {
        super(File.class);
    }

    @Override
    public File persist(File file) {
        VersionId versionId = new VersionId(nextId(), 0);
        file.setId(versionId);
        return super.persist(file);
    }

    private Long nextId() {
        return ((BigInteger) em.createNativeQuery("select nextval('seq_parse') id").getResultList().stream().findFirst().get()).longValue();
    }
}
