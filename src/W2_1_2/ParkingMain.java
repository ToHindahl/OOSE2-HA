package W2_1_2;

public class ParkingMain {

    /**
     * Noch nicht funktionsfähig
     */


    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(1);
        Car c1 = new Car("c1", parkingLot);
        Car c2 = new Car("c2", parkingLot);
        Car c3 = new Car("c3", parkingLot);

        c1.start();
        c2.start();
        c3.start();
    }

}
