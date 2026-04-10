public class Main {

    public static void main(String[] args) {
        ComputerCase theCase = new ComputerCase("2208", "Dell", "240");
        Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, "2540 x 1440");
        Motherboard theMotherboard = new Motherboard("BJ-200", "Asus", 4, 6, "v2.44");
        PersonalComputer thePC = new PersonalComputer("2208", "Dell", theCase, theMonitor, theMotherboard);

        /*
         * thePC.getMonitor().drawPixelAt(10, 10, "red");
         * thePC.getMotherboard().loadProgram("Windows 1.0");
         * thePC.getComputerCase().pressPowerButton();
         */
        thePC.powerUp();

        KitchenDevices kitchen = new KitchenDevices();
        /*
         * kitchen.getDishWasher().setHasWorkToDo(true);
         * kitchen.getIceBox().setHasWorkToDo(true);
         * kitchen.getBrewMaster().setHasWorkToDo(true);
         * kitchen.getDishWasher().doDishes();
         * kitchen.getIceBox().orderFood();
         * kitchen.getBrewMaster().brewCoffee();
         */

        kitchen.setKitchenState(true, false, true);
        kitchen.doKitchenWork();
    }
}