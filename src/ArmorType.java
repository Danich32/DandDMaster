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