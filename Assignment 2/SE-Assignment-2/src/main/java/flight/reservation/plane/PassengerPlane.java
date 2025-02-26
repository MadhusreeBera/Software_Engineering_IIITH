package flight.reservation.plane;

public class PassengerPlane {

    public String model;
    public int passengerCapacity;
    public int crewCapacity;

    public String toString(){
        return "{ \n Model : " + model + "\n Passenger Capacity : " + passengerCapacity + "\n Crew Capacity : " + crewCapacity + "\n}";
    }
    
    public PassengerPlane(String model) {
        this.model = model;
        switch (model) {
            case "A380":
                passengerCapacity = 500;
                crewCapacity = 42;
                break;
            case "A350":
                passengerCapacity = 320;
                crewCapacity = 40;
                break;
            case "Embraer 190":
                passengerCapacity = 25;
                crewCapacity = 5;
                break;
            case "Antonov AN2":
                passengerCapacity = 15;
                crewCapacity = 3;
                break;
            default:
                throw new IllegalArgumentException(String.format("Model type '%s' is not recognized", model));
        }
    }

}
