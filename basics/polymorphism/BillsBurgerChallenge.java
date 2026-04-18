// ============================================
// ENUMS
// ============================================

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

enum BurgerType {
    REGULAR("Regular Burger", 5.00),
    CHEESE("Cheese Burger", 6.50),
    DELUXE("Deluxe Burger", 8.00);

    private final String name;
    private final double basePrice;

    BurgerType(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }
}

enum Topping {
    LETTUCE("Lettuce", 0.50),
    TOMATO("Tomato", 0.50),
    CHEESE("Cheese", 1.00),
    BACON("Bacon", 1.50),
    ONION("Onion", 0.30),
    PICKLES("Pickles", 0.30);

    private final String name;
    private final double price;

    Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

enum DrinkSize {
    SMALL("Small", 1.50),
    MEDIUM("Medium", 2.00),
    LARGE("Large", 2.50);

    private final String name;
    private final double price;

    DrinkSize(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

enum DrinkType {
    COKE("Coke"),
    SPRITE("Sprite"),
    FANTA("Fanta"),
    WATER("Water");

    private final String name;

    DrinkType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum SideItemType {
    FRIES("Fries", 2.50),
    ONION_RINGS("Onion Rings", 3.00),
    SALAD("Salad", 3.50);

    private final String name;
    private final double price;

    SideItemType(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// ============================================
// BURGER CLASS
// ============================================
class Burger {
    private final BurgerType type;
    private final double basePrice;
    private final List<Topping> toppings;
    private final int maxToppings;
    private final boolean isDeluxe;

    // Private constructor - only Builder can create
    private Burger(BurgerType type, List<Topping> toppings, boolean isDeluxe) {
        this.type = type;
        this.basePrice = type.getBasePrice();
        this.toppings = new ArrayList<>(toppings);
        this.isDeluxe = isDeluxe;
        this.maxToppings = isDeluxe ? 5 : 3; // Deluxe can have 2 extra
    }

    public double getPrice() {
        if (isDeluxe) {
            return basePrice; // Deluxe: toppings don't add to price
        }
        double total = basePrice;
        for (Topping topping : toppings) {
            total += topping.getPrice();
        }
        return total;
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(type.getName());
        if (isDeluxe) {
            sb.append(" (Deluxe)");
        }
        if (!toppings.isEmpty()) {
            sb.append(" with: ");
            sb.append(toppings.stream()
                    .map(Topping::getName)
                    .collect(Collectors.joining(", ")));
        }
        return sb.toString();
    }

    // ============================================
    // BUILDER INNER CLASS
    // ============================================
    public static class Builder {
        private BurgerType type;
        private List<Topping> toppings = new ArrayList<>();
        private boolean isDeluxe = false;

        public Builder(BurgerType type) {
            this.type = type;
        }

        public Builder addTopping(Topping topping) {
            int maxAllowed = isDeluxe ? 5 : 3;
            if (toppings.size() >= maxAllowed) {
                throw new IllegalStateException(
                        "Cannot add more than " + maxAllowed + " toppings");
            }
            toppings.add(topping);
            return this;
        }

        public Builder makeDeluxe() {
            if (type != BurgerType.DELUXE) {
                throw new IllegalStateException(
                        "Only DELUXE burger type can be made deluxe");
            }
            this.isDeluxe = true;
            return this;
        }

        public Burger build() {
            return new Burger(type, toppings, isDeluxe);
        }
    }
}

// ============================================
// DRINK CLASS
// ============================================
class Drink {
    private final DrinkType type;
    private final DrinkSize size;

    private Drink(DrinkType type, DrinkSize size) {
        this.type = type;
        this.size = size;
    }

    public double getPrice() {
        return size.getPrice();
    }

    public String getDescription() {
        return size.getName() + " " + type.getName();
    }

    // ============================================
    // BUILDER INNER CLASS
    // ============================================
    public static class Builder {
        private DrinkType type;
        private DrinkSize size;

        public Builder(DrinkType type) {
            this.type = type;
        }

        public Builder size(DrinkSize size) {
            this.size = size;
            return this;
        }

        public Drink build() {
            if (size == null) {
                throw new IllegalStateException("Drink size must be specified");
            }
            return new Drink(type, size);
        }
    }
}

// ============================================
// SIDE ITEM CLASS
// ============================================
class SideItem {
    private final SideItemType type;

    private SideItem(SideItemType type) {
        this.type = type;
    }

    public double getPrice() {
        return type.getPrice();
    }

    public String getDescription() {
        return type.getName();
    }

    // ============================================
    // BUILDER INNER CLASS
    // ============================================
    public static class Builder {
        private SideItemType type;

        public Builder(SideItemType type) {
            this.type = type;
        }

        public SideItem build() {
            return new SideItem(type);
        }
    }
}

// ============================================
// MEAL ORDER CLASS (Main Builder)
// ============================================
class MealOrder {
    private final Burger burger;
    private final Drink drink;
    private final SideItem sideItem;

    private MealOrder(Burger burger, Drink drink, SideItem sideItem) {
        this.burger = burger;
        this.drink = drink;
        this.sideItem = sideItem;
    }

    public double getTotalPrice() {
        return burger.getPrice() + drink.getPrice() + sideItem.getPrice();
    }

    public void printOrder() {
        System.out.println("========== MEAL ORDER ==========");
        System.out.println("Burger: " + burger.getDescription());
        System.out.println("  Price: $" + String.format("%.2f", burger.getPrice()));
        System.out.println("Drink: " + drink.getDescription());
        System.out.println("  Price: $" + String.format("%.2f", drink.getPrice()));
        System.out.println("Side: " + sideItem.getDescription());
        System.out.println("  Price: $" + String.format("%.2f", sideItem.getPrice()));
        System.out.println("================================");
        System.out.println("TOTAL: $" + String.format("%.2f", getTotalPrice()));
        System.out.println("================================");
    }

    // ============================================
    // BUILDER INNER CLASS
    // ============================================
    public static class Builder {
        private Burger burger;
        private Drink drink;
        private SideItem sideItem;

        public Builder withBurger(Burger burger) {
            this.burger = burger;
            return this;
        }

        public Builder withDrink(Drink drink) {
            this.drink = drink;
            return this;
        }

        public Builder withSideItem(SideItem sideItem) {
            this.sideItem = sideItem;
            return this;
        }

        public MealOrder build() {
            if (burger == null || drink == null || sideItem == null) {
                throw new IllegalStateException(
                        "Meal order must have burger, drink, and side item");
            }
            return new MealOrder(burger, drink, sideItem);
        }
    }

    // ============================================
    // FACTORY METHOD FOR DEFAULT MEAL
    // ============================================
    public static MealOrder createDefaultMeal() {
        Burger burger = new Burger.Builder(BurgerType.REGULAR).build();
        Drink drink = new Drink.Builder(DrinkType.COKE)
                .size(DrinkSize.SMALL)
                .build();
        SideItem side = new SideItem.Builder(SideItemType.FRIES).build();

        return new MealOrder.Builder()
                .withBurger(burger)
                .withDrink(drink)
                .withSideItem(side)
                .build();
    }
}

// ============================================
// MAIN - EXAMPLES
// ============================================
public class BillsBurgerChallenge {
    public static void main(String[] args) {

        // Example 1: Default meal (no arguments)
        System.out.println("\n--- DEFAULT MEAL ---");
        MealOrder defaultMeal = MealOrder.createDefaultMeal();
        defaultMeal.printOrder();

        // Example 2: Custom regular burger with toppings
        System.out.println("\n--- CUSTOM REGULAR MEAL ---");
        Burger customBurger = new Burger.Builder(BurgerType.REGULAR)
                .addTopping(Topping.CHEESE)
                .addTopping(Topping.LETTUCE)
                .addTopping(Topping.TOMATO)
                .build();

        Drink largeCoke = new Drink.Builder(DrinkType.COKE)
                .size(DrinkSize.LARGE)
                .build();

        SideItem onionRings = new SideItem.Builder(SideItemType.ONION_RINGS)
                .build();

        MealOrder customMeal = new MealOrder.Builder()
                .withBurger(customBurger)
                .withDrink(largeCoke)
                .withSideItem(onionRings)
                .build();

        customMeal.printOrder();

        // Example 3: Deluxe burger (extra toppings, fixed price)
        System.out.println("\n--- DELUXE MEAL ---");
        Burger deluxeBurger = new Burger.Builder(BurgerType.DELUXE)
                .makeDeluxe()
                .addTopping(Topping.CHEESE)
                .addTopping(Topping.BACON)
                .addTopping(Topping.LETTUCE)
                .addTopping(Topping.TOMATO)
                .addTopping(Topping.ONION)
                .build();

        Drink mediumSprite = new Drink.Builder(DrinkType.SPRITE)
                .size(DrinkSize.MEDIUM)
                .build();

        SideItem salad = new SideItem.Builder(SideItemType.SALAD)
                .build();

        MealOrder deluxeMeal = new MealOrder.Builder()
                .withBurger(deluxeBurger)
                .withDrink(mediumSprite)
                .withSideItem(salad)
                .build();

        deluxeMeal.printOrder();
    }
}