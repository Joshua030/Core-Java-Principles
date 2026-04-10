public class SmartKitchen {

    protected Boolean hasWorkToDo;

    public SmartKitchen(Boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void setHasWorkToDo(Boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

}

class Refrigerator extends SmartKitchen {

    public Refrigerator(Boolean hasWorkToDo) {
        super(hasWorkToDo);
    }

    public void orderFood() {
        if (hasWorkToDo) {
            System.out.println("Ordening food");
            hasWorkToDo = false;
        }
    }
}

class DishWasher extends SmartKitchen {

    public DishWasher(Boolean hasWorkToDo) {
        super(hasWorkToDo);
        // TODO Auto-generated constructor stub
    }

    public void doDishes() {
        if (hasWorkToDo) {
            System.out.println("Washing dishes");
            hasWorkToDo = false;
        }
    }
}

class CoffeeMaker extends SmartKitchen {

    public CoffeeMaker(Boolean hasWorkToDo) {
        super(hasWorkToDo);
    }

    public void brewCoffee() {
        if (hasWorkToDo) {
            System.out.println("Brewing Coffe");
            hasWorkToDo = false;
        }
    }
}