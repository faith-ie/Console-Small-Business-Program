import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        aVoid();
    }

    static void aVoid() {
        try {
            int numOfVideos = 0;
            ArrayList<String> videos = new ArrayList<>();
            boolean cont;
            double salesCharge = 0.0;
            boolean preMem;
            String n;
            out.println("Hello, welcome to Daniel's Video Rental Shop!\nWhat is your name?");
            Scanner option = new Scanner(System.in);
            n = option.nextLine();
            out.println("Are you a premium member? (True / False)");
            preMem = option.nextBoolean();
            VideoStore videoStore = new VideoStore(numOfVideos, salesCharge, preMem, n);
            out.println(n + ", would you like to see our video rentals? (type true or false)");
            cont = option.nextBoolean();
            if (cont) {
                do {
                    out.println("Video Number        ||      Video Title     ||      Price of Rental");
                    out.println("---------------------------------------------------------------------");
                    out.println("1.      ||      Minions: The Rise of Gru        ||      $19.99 (30 days)");
                    out.println("2.      ||      Top Gun: Maverick       ||      $19.99 (30 days)");
                    out.println("3.      ||      Jurassic World Dominion     ||      $19.99 (30 days)");
                    out.println("4.      ||      Spider-Man: No Way Home     ||      $14.99 (30 days)");
                    out.println("5.      ||      Halloween       ||      $9.99 (30 days)");
                    out.println("What video would you like to rent?");
                    int videoChoice = option.nextInt();
                    switch (videoChoice) {
                        case 1 -> {
                            videos.add("Minions: The Rise of Gru");
                            numOfVideos++;
                            salesCharge += salesCharge + 19.99;
                        }
                        case 2 -> {
                            videos.add("Top Gun: Maverick");
                            numOfVideos++;
                            salesCharge += 19.99;
                        }
                        case 3 -> {
                            videos.add("Jurassic World Dominion");
                            numOfVideos++;
                            salesCharge += 19.99;
                        }
                        case 4 -> {
                            videos.add("Spider-Man: No Way Home");
                            numOfVideos++;
                            salesCharge += 14.99;
                        }
                        case 5 -> {
                            videos.add("Halloween");
                            numOfVideos++;
                            salesCharge += 9.99;
                        }
                    }
                    out.println("Do you want to continue? (Type true / false)");
                    cont = option.nextBoolean();
                    if (preMem) {
                        salesCharge *= 0.8;
                    }
                    videoStore.salesCharge = salesCharge;
                    videoStore.numVideos = numOfVideos;
                } while (cont);
            }
            File file = new File("data.txt");
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                fw.flush();
                if (preMem) {
                    fw.write("Your name is " + videoStore.name + ", and you are a premium member.\n");
                } else {
                    fw.write("Your name is " + videoStore.name + ", and you are not a premium member.\n");
                }

                for (String v : videos) {
                    fw.write(v + "\n");
                }
                if (videos.size() == 1) {
                    fw.write("\nYou have rented " + videoStore.numVideos + " video" + "\nTotal sales charge: $" + videoStore.calcCost(videoStore.salesCharge));
                } else {
                    fw.write("\nYou have rented " + videoStore.numVideos + " videos" + "\nTotal sales charge: $" + videoStore.calcCost(videoStore.salesCharge));
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NoSuchElementException ex) {
            out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    static class VideoStore {
        public int numVideos;
        public double salesCharge;
        public boolean storePremiumMember;
        public String name;

        VideoStore(int numVideos, double salesCharge, boolean storePremiumMember, String name) {
            this.numVideos = numVideos;
            this.salesCharge = salesCharge;
            this.storePremiumMember = storePremiumMember;
            this.name = name;
        }

        public double calcCost(double salesCharge) {
            return salesCharge *= 1.07;
        }
    }
}
