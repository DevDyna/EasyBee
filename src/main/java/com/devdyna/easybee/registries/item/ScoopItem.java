package com.devdyna.easybee.registries.item;

public class ScoopItem extends SimpleTipItem {

    public ScoopItem(String traslationName, Properties prop) {
        super(traslationName, prop);
        prop.durability(128);
        prop.stacksTo(1);
    }

}
