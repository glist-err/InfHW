public class Main {
    public static void main(String[] args) {
        Car[] cars = {
            new Car(1, "Toyota", "Corolla", 2020, "White", 20000, "AB1234"),
            new Car(2, "Ford", "Focus", 2018, "Blue", 15000, "CD5678"),
            new Car(3, "Toyota", "Camry", 2022, "Black", 30000, "EF9012")
        };

        Car[] toyotas = getCarByBrend(cars, "Toyota");

        System.out.println("Найдено машин Toyota: " + toyotas.length);
    }

    public static Car[] getCarByBrend(Car[] cars, String mark) {
        int count = 0;

        for (int i = 0; i <= cars.length-1; i++) {
            if (cars[i].getMark().equals(mark)) {
                count++;
            }
        }

        if (count==0) {
            return new Car[0];
        }

        Car[] res = new Car[count];
        int index = 0;

        for (int i = 0; i <= cars.length-1; i++) {
            if (cars[i].getMark().equals(mark)) {
                res[index++] = cars[i];
            }
        }

        return res;
    }
}