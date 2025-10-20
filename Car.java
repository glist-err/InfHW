public class Car {
    private int id;
    private String mark;
    private String model;
    private int year;
    private String Color;
    private int price;
    private int reg_numder;

    // Constructor
    public Car(int id, String mark, String model, int year, String Color, int price, int reg_numder) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.Color = Color;
        this.price = price;
        this.reg_numder = reg_numder;
    }

    // ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        }
    }

    // mark
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        if (mark != null) {
            this.mark = mark;
        }
    }

    // model
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        if (model != null) {
            this.model = model;
        }
    }

    // year
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        if (year >= 0) {
            this.year = year;
        }
    }

    // Color
    public String getColor() {
        return Color;
    }
    public void setColor(String Color) {
        if (Color != null) {
            this.Color = Color;
        }   
    }

    // price
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    // reg_numder
    public int getRegNumber() {
        return reg_numder;
    }
    public void setRegNumber(int reg_numder) {
        if (reg_numder >= 0) {
            this.reg_numder = reg_numder;
        }
    }

}