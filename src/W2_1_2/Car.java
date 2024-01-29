package W2_1_2;

public class Car extends Thread{
    private String name;
    private ParkingLot parkingLot;

    public Car(String name, ParkingLot parkingLot){
        this.name = name;
        this.parkingLot = parkingLot;
    }

    public String getCarName() {
        return name;
    }

    @Override
    public void run() {
        for (int i = 0; i <=3; i++){
            parkingLot.enter(this);

            // Zu Demo Zwecken

            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            parkingLot.leave(this);
        }
    }
}