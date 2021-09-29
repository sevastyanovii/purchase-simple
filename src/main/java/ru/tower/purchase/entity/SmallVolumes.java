/*
 * Copyright (c) "Tower Networks and Technology" LTD 2018. All Rights Reserved. No part of this code may be reproduced without "Tower Networks and Technology" LTD's express consent.
 */

package ru.tower.purchase.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AnDrunkYeti
 * Date: 16.01.18
 * Time: 20:21
 */
public enum SmallVolumes {

    NONE("Нет"),
    UP_TO_100("Закупки до 100 т.р."),
    UP_TO_500("Закупки до 500 т.р.");

    private final String name;

    private SmallVolumes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SmallVolumes lookupByCode(String code) {
        if(code != null) {
            SmallVolumes[] smallVolumes = SmallVolumes.values();
            for (int i = 0; i < smallVolumes.length; i++) {
                if (smallVolumes[i].toString().equals(code)) {
                    return smallVolumes[i];
                }
            }
        }
        return null;
    }

    public static List<SmallVolumes> getListByJsonString(String smallVolumesValue) {
        List<SmallVolumes> smallVolumes = null;
        if(smallVolumesValue != null && !smallVolumesValue.equals("")) {
            smallVolumes = new ArrayList<>();
            String[] smallVolumesValues = smallVolumesValue.split(",");
            for(int i=0; i<smallVolumesValues.length; i++){
                smallVolumes.add(SmallVolumes.lookupByCode(smallVolumesValues[i]));
            }
        }
        return smallVolumes;
    }

}
