package W2_1_2;

import java.util.LinkedList;
import java.util.Queue;

public class ParkingLot {
    public static void main(String[] args) {
    }
    private int availablePLaces;
    private Queue<Car> queue;

    public ParkingLot(int places) {
        this.availablePLaces = places;
        this.queue = new LinkedList<>();
    }
    public synchronized void enter(Car car) {
        while (availablePLaces == 0) {
            queue.add(car);
            System.out.println(car.getName() + " is waiting in queue");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (!queue.isEmpty()) {
            Car firstCar = queue.peek();
            if (car == firstCar) {
                availablePLaces--;
                queue.remove();
                System.out.println(car.getName() + " has entered");
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            availablePLaces--;
            System.out.println(car.getName() + " has directly entered");
        }
    }

    public synchronized void leave(Car car){
        availablePLaces++;
        System.out.println(car.getName() + " left");
        notifyAll();
    }
}
