public class Device {
    private int id;
    private int price;
    private String ip;

    public Device(int id, int price, String ip) {
        this.id = id;
        this.price = price;
        this.ip = ip;
    }
    
    // ID
    public int getId() {
        return id;
    }
    /*
    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        }
        else {
            System.err.println("Нормальный ID давай");
        }
    }
    */

    // Price
    public int getPrice() {
        return price;
    }
    /*
    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
        else {
            System.err.println("Нормальный PRICE давай");
        }
    }
    */

    // IP
    public String getIp() {
        return ip;
    }
    /*
    public void setIp(String ip) {
        if (ip != null) {
            this.ip = ip;
        }
        else {
            System.err.println("Нормальный IP давай");
        }
    }
    */
   /*
   public String print() {
   }
   */
}