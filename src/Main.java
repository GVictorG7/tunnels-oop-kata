import model.ExtractionPoint;
import model.RockType;
import model.Wagon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Main {
    private static List<ExtractionPoint> points;

    public static void main(String[] args) {
        int cmd;
        do {
            cmd = readCommand();
            switch (cmd) {
                case 1:
                    first();
                    break;
                case 3:
                    third();
                    break;
            }
        } while (cmd != 0);
    }

    private static int readCommand() {
        int cmd = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            cmd = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println("invalid command");
            e.printStackTrace();
        }

        return cmd;
    }

    private static void first() {
        generateExtractionPoints();

        StringBuilder resultBuilder = new StringBuilder();
        points.forEach(System.out::println);
        points.forEach(x -> resultBuilder.append(x.toString()).append("\n"));
        String result = resultBuilder.toString();

        writeExtractionPointsToFile(result);
    }

    private static void writeExtractionPointsToFile(String result) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("extractions.txt"))) {
            out.write(result);
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateExtractionPoints() {
        points = new ArrayList<>();

        points.add(new ExtractionPoint(RockType.GREEN, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.YELLOW, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.MAGENTA, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.BLACK, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.BLUE, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.RED, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.WHITE, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.YELLOW, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.GREEN, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.WHITE, new Random().nextInt(15) + 1));
        points.add(new ExtractionPoint(RockType.MAGENTA, new Random().nextInt(15) + 1));
    }

    private static void third() {
        readExtractionPointList();

//        points.forEach(System.out::println);
//        System.out.println("========================================");

        while (hasLeftContent(points)) {
            Wagon wagon = new Wagon();
            for (ExtractionPoint point : points) {
                wagon.load(point);
            }
            System.out.println(wagon);
//            System.out.println("RESULT: =====================");
//            points.forEach(System.out::println);
//            System.out.println("========================================");
        }
    }

    private static void readExtractionPointList() {
        points = new ArrayList<>();
        Path path = Paths.get("extractions.txt");
        Stream<String> pointsContent;
        try {
            pointsContent = Files.lines(path);
            pointsContent.forEach(ln -> {
                String[] s = ln.split("-");
                int capacity = Integer.parseInt(s[2].trim().split(" ")[0]);

                ExtractionPoint point = new ExtractionPoint(RockType.valueOf(s[1].trim()), capacity);
                points.add(point);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean hasLeftContent(List<ExtractionPoint> points) {
        for (ExtractionPoint point : points) {
            if (point.getCapacity() > 0)
                return true;
        }
        return false;
    }
}
