public class NextMain {

    public static void main(String[] args) {
        Movie movie = Movie.getMovie("A", "Jaws");
        movie.watchMovie();

        Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
        jaws.watchMovie();

        Object comedy = Movie.getMovie("C", "Airplane");
        Comedy comedyMovie = (Comedy) comedy;
        comedyMovie.watchComedy();

        /* -------------------------------------------------------------------- */
        /*
         * var is a special contextual keyword in java that lets our code take advantage
         * of local Variable Type Inference
         */
        /* -------------------------------------------------------------------- */
        var airplane = Movie.getMovie("C", "Airplane");
        airplane.watchMovie();

        var plane = new Comedy("Airplane");
        plane.watchComedy();

        Object unknowObject = Movie.getMovie("C", "Airplane");
        if (unknowObject.getClass().getSimpleName() == "Comedy") {
            Comedy c = (Comedy) unknowObject;
            c.watchComedy();
        } else if (unknowObject instanceof Adventure) {
            ((Adventure) unknowObject).watchAdventure();
        } else if (unknowObject instanceof ScienceFiction syfy) {
            syfy.watchScienceFiction();
        }

        Car car = new Car("2022 Blue Ferrari 296 GTS");
        runRace(car);

        Car ferrari = new GasPoweredCar("2022 Blue Ferrari 296 GTS", 10.0, 6);
        runRace(ferrari);

        Car tesla = new ElectricCar("2022 Red Tesla Model 3",
                568, 75);
        runRace(tesla);

        Car ferrariHybrid = new HybridCar("2022 Black Ferrari SF90 Strade",
                16, 8, 8);
        runRace(ferrariHybrid);
    }

    public static void runRace(Car car) {

        car.startEngine();
        car.drive();
    }
}