public class Main {
    public static void main(String[] args) {
        Car[] cars = {
            new Car(1, "Toyota", "Corolla", 2020, "White", 20000, "AB1234"),
            new Car(2, "Ford", "Focus", 2018, "Blue", 15000, "CD5678"),
            new Car(3, "Toyota", "Camry", 2022, "Black", 30000, "EF9012")
        };

        Car[] toyotas1 = getCarByBrend(cars, "Toyota");
        System.out.println("Найдено Toyota: " + toyotas1.length);

        Car[] toyotas2 = getCarByBrendAndYearOperational(cars, "Toyota", 4);
        System.out.println("Найдено Toyota более 4 лет: " + toyotas2.length);
    }

    public static Car[] getCarByBrend(Car[] cars, String mark) {
        int count = 0;

        for (int i = 0; i <= cars.length-1; i++) {
            if (cars[i].getMark().equals(mark)) {
                count++;
            }
        }

        if (count==0) {
            return new Car[0];  // Если под условие, то [0]
        }

        // Новый массив для результата
        Car[] res = new Car[count];
        int index = 0;

        for (int i = 0; i <= cars.length-1; i++) {
            if (cars[i].getMark().equals(mark)) {
                res[index++] = cars[i];
            }
        }

        return res;
    }

    public static Car[] getCarByBrendAndYearOperational(Car[] cars, String mark, int years) {
        int count = 0;

        for (int i = 0; i <= cars.length-1; i++) {
            if (cars[i].getMark().equals(mark) && 2025-cars[i].getYear() >= years) {
                count++;
            }
        }

        if (count==0) {
            return new Car[0];
        }

        Car[] res = new Car[count];
        int index = 0;

        for (int i = 0; i <= cars.length-1; i++) {
            if (cars[i].getMark().equals(mark) && 2025-cars[i].getYear() >= years) {
                res[index++] = cars[i];
            }
        }

        return res;
    }
}