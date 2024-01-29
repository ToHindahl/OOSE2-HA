package W2_1_1;

public class ParkingLot {

    private int availablePlaces;

    public ParkingLot(int places){
        this.availablePlaces = places;
    }

    public synchronized void enter(Car car){
        while(availablePlaces == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(car.getCarName() + " entered parking lot");
        availablePlaces--;
    }



    public synchronized void leave(Car car){
        availablePlaces++;
        System.out.println(car.getCarName() + " left parking lot");
        notify();
    }
}