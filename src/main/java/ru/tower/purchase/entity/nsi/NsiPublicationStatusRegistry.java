package ru.tower.purchase.entity.nsi;

/**
 * Реестр статусов размещения информации
 */
public enum NsiPublicationStatusRegistry {
    PREPARATION(1, "Подготовка для размещения"),
    PUBLICATION(2, "Размещение"),
    SUCCESS(3, "Информация размещена"),
    ERROR(4, "Ошибка размещения");

    private final Long id;
    private final String name;

    private NsiPublicationStatusRegistry(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return идентификатор статуса размещения информации
     */
    public Long getId() {
        return id;
    }

    /**
     * @return наименование по умолчанию для статуса размещения информации
     */
    public String getName() {
        return name;
    }
}
