public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero("Жорик");
        System.out.println("Герой: " + hero.getName() + ", HP " + hero.getHealth() + "/" + hero.getMaxHealth());


        hero.earnMoney(150);
        ArmorType type = ArmorType.LEATHER;
        if (hero.spendMoney(type.getPrice())) {
            hero.equipArmor(new Armor(type));
            System.out.println("Куплена броня " + type + ", денег осталось: " + hero.getMoney());
        } else {
            System.out.println("Не хватает денег на " + type);
        }


        printHit(hero, 30);
        printHit(hero, 80);


        System.out.println("Вылечено: " + hero.heal(100) + ", HP " + hero.getHealth() + "/" + hero.getMaxHealth());
    }

    private static void printHit(Hero hero, int damage) {
        DamageResult r = hero.takeDamage(damage);
        System.out.println("Удар " + damage + ": броня поглотила " + r.absorbed()
                + ", потеряно HP " + r.healthLost()
                + (r.armorBroken() ? ", броня сломалась" : "")
                + (r.died() ? ", герой погиб и воскрес" : "")
                + ". Сейчас HP " + hero.getHealth());
    }
}
