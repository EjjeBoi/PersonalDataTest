import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // Создаем список объектов Person, считанных из файла "data"
        String separator = File.separator;
        List<Person> persons = PersonReader.read(separator + "Users" + separator + "RedmiBook" + separator + "IdeaProjects" + separator + "PersonalDataTest" + separator + "src" + separator + "data");
        // Фильтруем список, оставляя только объекты с waterCount > 100
        List<Person> filteredPersons = PersonProcessor.filterByElectroCount1(persons, 300);
        // Выводим отфильтрованный список на экран
        filteredPersons.forEach(System.out::println);
    }


    public static class PersonReader {

        public static List<Person> read(String data) throws FileNotFoundException {
            Scanner scanner = new Scanner(new File("C:\\Users\\RedmiBook\\IdeaProjects\\PersonalDataTest\\src\\data"));
            List<Person> persons = new ArrayList<>();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\\|");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                int waterCount = Integer.parseInt(fields[2]);
                int gasCount1 = Integer.parseInt(fields[3]);
                int gasCount2 = Integer.parseInt(fields[4]);
                int electroCount1 = Integer.parseInt(fields[5]);
                int electroCount2 = Integer.parseInt(fields[6]);
                // Создаем новый объект Person и добавляем его в список persons
                persons.add(new Person(id, name, waterCount, gasCount1, gasCount2, electroCount1, electroCount2));
            }
            scanner.close();
            return persons;
        }
    }

    public static class PersonProcessor {

        public static List<Person> filterByWaterCount(List<Person> persons, int waterCount) {
            return persons.stream()
                    .filter(person -> person.getWaterCount() > waterCount)
                    .collect(Collectors.toList());
        }

        public static List<Person> filterByGasCount1(List<Person> persons, int gasCount1) {
            return persons.stream()
                    .filter(person -> person.getGasCount1() > gasCount1)
                    .collect(Collectors.toList());
        }

        public static List<Person> filterByGasCount2(List<Person> persons, int gasCount2) {
            return persons.stream()
                    .filter(person -> person.getGasCount2() > gasCount2)
                    .collect(Collectors.toList());
        }

        public static List<Person> filterByElectroCount1(List<Person> persons, int electroCount1) {
            return persons.stream()
                    .filter(person -> person.getElectroCount1() > electroCount1)
                    .collect(Collectors.toList());
        }

        public static List<Person> filterByElectroCount2(List<Person> persons, int electroCount2) {
            return persons.stream()
                    .filter(person -> person.getElectroCount2() > electroCount2)
                    .collect(Collectors.toList());
        }
    }
    @Data
    @AllArgsConstructor
    public static class Person {
        private int id;
        private String name;
        private int waterCount;
        private int gasCount1;
        private int gasCount2;
        private int electroCount1;
        private int electroCount2;
    }
}