package io.moonspirit.hybrid.bukkit.impl.attribute;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;

import java.util.Collection;
import java.util.Collections;

public class CraftAttributeInstance implements AttributeInstance {

    private final Attribute attribute;
    private double baseValue;

    public CraftAttributeInstance(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public Attribute getAttribute() {
        return attribute;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public void setBaseValue(double value) {
        this.baseValue = value;
    }

    @Override
    public Collection<AttributeModifier> getModifiers() {
        return Collections.emptyList();
    }

    @Override
    public void addModifier(AttributeModifier modifier) {
    }

    @Override
    public void removeModifier(AttributeModifier modifier) {
    }

    @Override
    public double getValue() {
        return baseValue;
    }

    @Override
    public double getDefaultValue() {
        return 0;
    }
}
