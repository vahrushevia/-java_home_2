
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;
import java.util.stream.Collectors;

public class program {

    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(program.class.getName());
        FileHandler fh = new FileHandler("log.txt");
        logger.addHandler(fh);
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        // INFO, DEBUG, ERROR, WARNING и др.
        // logger.log(Level.WARNING, "Тестовое логирование 1");
        // logger.info("Тестовое логирование 2");
        int a = ReadFile("a");
        int b = ReadFile("b");
        
        WriteFile(a,b);
    }

    public static int ReadFile(String ch) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader("input.txt"));
        String temp, tempA, tempB;
        int a, b;
        while ((temp = file.readLine()) != null) {
            if (temp.contains("a")) {
                tempA = temp.chars()
                        .filter(c -> !Character.isLetter(c))
                        .mapToObj(c -> String.valueOf((char) c))
                        .collect(Collectors.joining())
                        .trim();
                if (ch == "a")
                    return a = Integer.parseInt(tempA);

            } else if (temp.contains("b")) {
                tempB = temp.chars()
                        .filter(c -> !Character.isLetter(c))
                        .mapToObj(c -> String.valueOf((char) c))
                        .collect(Collectors.joining())
                        .trim();
                if (ch == "b")
                    return b = Integer.parseInt(tempB);

            } else {
                System.out.println("Ошибка чтения файла");
                break;
            }

        }
        file.close();
        
        return 0;

    }
    public static void WriteFile(int a, int b) throws Exception {
        try (FileWriter file = new FileWriter("output.txt", false)) {
            if (a == 0 && b == 0) {
                file.write("не определено");
                file.close();
            } else {
                int res= (int) Math.pow(a, b);
                file.write(String.format("а = %d, b = %d, ответ: %d", a, b, res));
                file.close();
            }
        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
}
