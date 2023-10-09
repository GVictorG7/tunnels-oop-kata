package model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class Wagon {
    private static final Random RANDOM = new Random();
    private final Map<RockType, Integer> content = new EnumMap<>(RockType.class);

    public Wagon() {
        for (int i = 0; i < 3; i++) {
            int rand = RANDOM.nextInt(7);
            switch (rand) {
                case 0:
                    content.put(RockType.GREEN, 0);
                    break;
                case 1:
                    content.put(RockType.YELLOW, 0);
                    break;
                case 2:
                    content.put(RockType.MAGENTA, 0);
                    break;
                case 3:
                    content.put(RockType.BLACK, 0);
                    break;
                case 4:
                    content.put(RockType.WHITE, 0);
                    break;
                case 5:
                    content.put(RockType.RED, 0);
                    break;
                case 6:
                    content.put(RockType.BLUE, 0);
                    break;
            }
        }
    }

    private int getCurrentCapacity() {
        return content.values().stream().reduce(0, Integer::sum);
    }

    public void load(ExtractionPoint point) {
        if (!content.containsKey(point.getRockType()) || getCurrentCapacity() >= 20 || point.getCapacity() == 0) {
            return;
        }

        int rand = RANDOM.nextInt(7); // value = 0-6

        // normalizing
        while (rand > point.getCapacity() || rand > 20 - getCurrentCapacity()) {
            rand = RANDOM.nextInt(7);
        }
        // rand <= point capacity & rand <= wagon remaining capacity (20 - current capacity)

        content.put(point.getRockType(), content.get(point.getRockType()) + rand);
        point.setCapacity(point.getCapacity() - rand);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Wagon - ");
        for (Map.Entry<RockType, Integer> entry : content.entrySet()) {
            result.append(entry.getValue()).append(" bags ").append(entry.getKey()).append("; ");
        }
        return result.toString();
    }
}
