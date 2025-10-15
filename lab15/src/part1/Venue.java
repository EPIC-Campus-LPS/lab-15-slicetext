package part1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Venue {
    private Seat[][] seats;

    public Venue(int row, int col, double price) {
        seats = new Seat[row][col];
        for(int i = 0; i < seats.length; i++) {
            for(int j = 0; j < seats[i].length; j++) {
                seats[i][j] = new Seat(false, SeatType.REGULAR, price);
            }
        }
    }

    public boolean buyTicket(int row, int col) {
        Seat seat = seats[row][col];
        if(!seat.getSold()) {
            seats[row][col].setSold(true);
            return true;
        } else {
            return false;
        }
    }

    public boolean isAvailable(int row, int col) {
        return seats[row][col].getSold();
    }

    public void setPremium(int row, double price) {
        Seat[] seat_row = seats[row];
        for(int i = 0; i < seat_row.length; i++) {
            Seat cur = seat_row[i];
            cur.setType(SeatType.PREMIUM);
            cur.setPrice(price);
        }
    }

    public void setPremium(int row, int colStart, int colEnd, double price) {
        Seat[] seat_row = seats[row];
        for(int i = colStart; i <= colEnd; i++) {
            Seat cur = seat_row[i];
            cur.setType(SeatType.PREMIUM);
            cur.setPrice(price);
        }
    }

    public void setGA(int row, double price) {
        Seat[] seat_row = seats[row];
        for(int i = 0; i < seat_row.length; i++) {
            Seat cur = seat_row[i];
            cur.setType(SeatType.GENERAL_ADMISSION);
            cur.setPrice(price);
        }
    }

    public boolean importTickets(String filename) throws IOException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] components = line.split(",");
            int row = Integer.parseInt(components[0]);
            int col = Integer.parseInt(components[1]);
            boolean result = buyTicket(row, col);
            if(!result) {
                return false;
            }
        }
        return true;
    }

    public double totalRevenue() {
        double total = 0;
        for(Seat[] i : seats) {
            for(Seat j : i) {
                if(j.getSold()) {
                    total += j.getPrice();
                }
            }
        }
        return total;
    }

    public double totalRevenue(int col) {
        double total = 0;
        for(Seat[] i : seats) {
            if(i[col].getSold()) {
                total += i[col].getPrice();
            }
        }
        return total;
    }

    public int totalSold() {
        int total = 0;
        for(Seat[] i : seats) {
            for(Seat j : i) {
                if(j.getSold()) {
                    total++;
                }
            }
        }
        return total;
    }

    public int totalSold(int row) {
        int total = 0;
        for(Seat j : seats[row]) {
            if(j.getSold()) {
                total++;
            }
        }
        return total;
    }

    public void printVenue() {
        for(Seat[] i : seats) {
            for(Seat j : i) {
                System.out.print(j.getSold());
            }
            System.out.println();
        }
    }

    public void printVenueType() {
        for(Seat[] i : seats) {
            for(Seat j : i) {
                System.out.print(j.getTypeString());
            }
            System.out.println();
        }
    }

    public void printVenuePrice() {
        for(Seat[] i : seats) {
            for(Seat j : i) {
                System.out.print(j.getPrice());
            }
            System.out.println();
        }
    }

    public double maxPrice(int row, int colStart, int colEnd, double price) {
        Seat[] seat_row = seats[row];
        Seat max = seat_row[colStart];
        for(int i = colStart; i <= colEnd; i++) {
            Seat cur = seat_row[i];
            if(cur.getPrice() > max.getPrice()) {
                max = cur;
            }
        }
        return max.getPrice();
    }

    public boolean containsGA(int row) {
        for(int i = 0; i < seats[row].length; i++) {
            if(seats[row][i].getType() == SeatType.GENERAL_ADMISSION) {
                return true;
            }
        }
        return false;
    }

    public boolean allPremium(int col) {
        for(Seat[] i : seats) {
            if(i[col].getType() != SeatType.PREMIUM) {
                return false;
            }
        }
        return true;
    }
}
