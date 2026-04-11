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
    }
}