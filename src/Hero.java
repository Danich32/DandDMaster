class Hero {
    private String name;
    private int health;
    private int level;
    private int money;
    private Armor armor;          // null, если брони нет

    public Hero(String name) {
        this.name = name;
        health = 100;
        level = 1;
        money = 0;
        armor = null;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getLevel() { return level; }
    public int getMoney() { return money; }
    public Armor getArmor() { return armor; }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Урон не может быть отрицательным");
        }
        int rest = damage;
        if (armor != null) {
            rest = armor.absorb(damage);   // делегируем броне
            if (armor.isBroken()) {
                armor = null;              // сломанная броня исчезает
            }
        }
        if (health <= rest) {
            die();
        } else {
            health -= rest;
        }
    }

    public void earnMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        money += amount;
    }

    public void buyArmor(ArmorType type) {
        if (money < type.getPrice()) {
            throw new IllegalStateException("Не хватает денег");
        }
        money -= type.getPrice();
        armor = new Armor(type);           // новая броня заменяет старую
    }

    private void die() {
        health = 100;
        armor = null;                      // броня теряется, деньги и уровень остаются
    }
}