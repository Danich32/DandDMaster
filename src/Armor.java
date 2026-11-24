enum ArmorType {
    LEATHER(1, 100, 50),
    CHAINMAIL(2, 250, 100),
    BRONZE(3, 500, 150),
    IRON(4, 900, 220),
    STEEL(5, 1500, 300),
    MITHRIL(6, 2500, 450);

    private final int level;
    private final int price;
    private final int maxDurability;

    ArmorType(int level, int price, int maxDurability) {
        this.level = level;
        this.price = price;
        this.maxDurability = maxDurability;
    }

    public int getLevel() { return level; }
    public int getPrice() { return price; }
    public int getMaxDurability() { return maxDurability; }
}

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