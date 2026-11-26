class Armor {
    private final ArmorType type;
    private int durability;

    public Armor(ArmorType type) {
        this.type = type;
        this.durability = type.getMaxDurability();
    }

    public ArmorType getType() { return type; }
    public int getDurability() { return durability; }

    public boolean isBroken() {
        return durability == 0;
    }

    // Поглощает урон, возвращает то, что броня не сдержала
    public int absorb(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Урон не может быть отрицательным");
        }
        if (damage <= durability) {
            durability -= damage;
            return 0;
        }
        int rest = damage - durability;
        durability = 0;
        return rest;
    }
}