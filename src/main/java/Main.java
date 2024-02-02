import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите режим сортировки (1 - по алфавиту, 2 - по количеству символов): ");
        int mode = scanner.nextInt();

        Map<String, Integer> linesCount = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                linesCount.put(line, linesCount.getOrDefault(line, 0) + 1);
            }
        }

        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : linesCount.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                lines.add(entry.getKey());
            }
        }

        switch (mode) {
            case 1:
                Collections.sort(lines);
                break;
            case 2:
                lines.sort(Comparator.comparingInt(String::length));
                break;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            for (String line : lines) {
                bw.write(line + " " + linesCount.get(line));
                bw.newLine();
            }
        }
    }
}
