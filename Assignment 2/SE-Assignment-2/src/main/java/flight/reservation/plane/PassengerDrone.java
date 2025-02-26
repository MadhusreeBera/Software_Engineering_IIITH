package flight.reservation.plane;

public class PassengerDrone {
    private final String model;

    public String toString(){
        return "{ \n Model : " + model + "\n}";
    }
    public PassengerDrone(String model) {
        if (model.equals("HypaHype")) {
            this.model = model;
        } else {
            throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
        }
    }
}
