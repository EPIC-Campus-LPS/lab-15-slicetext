package part1;

public class Seat {
    private boolean sold;
    private SeatType type;
    private double price;

    public Seat(boolean sold, SeatType type, double price){
        this.sold = sold;
        this.type = type;
        this.price = price;
    }

    public boolean getSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public SeatType getType() {
        return type;
    }

    public String getTypeString() {
        switch (type) {
            case REGULAR:
                return "R";
            case PREMIUM:
                return "P";
            case GENERAL_ADMISSION:
                return "G";
        }
        return "Error";
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
