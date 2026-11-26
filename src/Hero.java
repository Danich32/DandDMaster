import java.util.Objects;

class Hero {
    public static final int START_HEALTH = 100;

    private String name;
    private int maxHealth;
    private int health;
    private int level;
    private int money;
    private Armor armor;          // null, если брони нет

    public Hero(String name) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("имя героя не может быть пустым");
        }
        this.name = name;
        this.maxHealth = START_HEALTH;
        this.health = maxHealth;
        level = 1;
        money = 0;
        armor = null;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getLevel() { return level; }
    public int getMoney() { return money; }
    public Armor getArmor() { return armor; }
/*
получить урон - сначала удар на броню, остаток в здоровье
сломанная броня исчезает
если здоровье закончилось - игрок умирает, здоровье 100, деньги и уровень остаются
 */

    public DamageResult takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("урон не может быть отрицательным");
        }

        int rest = damage;
        int absorbed = 0;
        boolean armorBroken = false;

        if (armor != null){
            rest = armor.absorb(damage); //делегируем броне
            absorbed = damage - rest;
            if (armor.isBroken()) {
                armor = null;
                armorBroken = true;
            }
        }
        if (health <= rest) {
            int healthLost = health;
            die();
            return new DamageResult(absorbed, healthLost, armorBroken, true);
        }

        health -= rest;
        return new DamageResult(absorbed, rest, armorBroken, false);


        }


    public int heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("лечение не может быть отрицательным");
        }
        int healed = Math.min(amount, maxHealth - health);
        health += healed;
        return healed;
    }

    public void earnMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        money += amount;
    }

    public boolean spendMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        if (money < amount) {
            return false;
        }
        money -= amount;
        return true;
    }

    public void equipArmor(Armor newArmor) {
        this.armor = Objects.requireNonNull(newArmor, "броня не может быть null");
    }



    private void die() {
        health = maxHealth;
        armor = null;                      // броня теряется, деньги и уровень остаются
    }
}