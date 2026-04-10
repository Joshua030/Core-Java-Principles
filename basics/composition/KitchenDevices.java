public class KitchenDevices {
    private CoffeeMaker brewMaster;
    private DishWasher dishWasher;
    private Refrigerator iceBox;

    public KitchenDevices() {
        brewMaster = new CoffeeMaker(false);
        iceBox = new Refrigerator(false);
        dishWasher = new DishWasher(false);
    }

    public CoffeeMaker getBrewMaster() {
        return brewMaster;
    }

    public DishWasher getDishWasher() {
        return dishWasher;
    }

    public Refrigerator getIceBox() {
        return iceBox;
    }

    public void addWater() {
        brewMaster.hasWorkToDo = true;
    }

    public void porMilk() {
        dishWasher.hasWorkToDo = true;
    }

    public void loadDishwasher() {
        iceBox.hasWorkToDo = true;
    }

    public void setKitchenState(boolean brewMasterhasWorkToDo, boolean dishWasherHasWorkToDo,
            boolean iceBoxHasWorkToDo) {
        brewMaster.hasWorkToDo = brewMasterhasWorkToDo;
        dishWasher.hasWorkToDo = dishWasherHasWorkToDo;
        iceBox.hasWorkToDo = iceBoxHasWorkToDo;
    }

    public void doKitchenWork() {
        brewMaster.brewCoffee();
        iceBox.orderFood();
        dishWasher.doDishes();
    }

}
