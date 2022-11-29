package stream;

/**
 * Дан список студентов с полями:
 * - Имя
 * - Фамилия
 * - Номер курса в университете
 * - Список оценок за учебу
 * Необходимо преобразовать этот список студентов в ассоциативный массив, где ключом является номер курса, а значением:
 * 1) Средняя оценка студентов этого курса, количество оценок у которых больше 3-х;
 * 2) Список студентов данного курса, но только с полями Имя и Фамилия.
 * Список должен быть отсортирован по этим двум полям;
 * 3) Объект с двумя полями:
 * - Отсортированный список студентов с пункта 2;
 * - Средняя оценка этих студентов.
 */

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;



public class StudentsRunner {
    public static void main(String[] args) {
        List<Integer> academicGrades1 = List.of(5,5);
        List<Integer> academicGrades2 = List.of(3,3,5,4);
        List<Integer> academicGrades3 = List.of(4,4,4,4,5,4,5,4);
        List<Integer> academicGrades4 = List.of(3,5,3);
        List<Integer> academicGrades5 = List.of(5,3,4,4);
        List<Integer> academicGrades6 = List.of(5,3,5,3,5,5,4,5);
        List<Integer> academicGrades7 = List.of(5,3,3,5);
        List<Integer> academicGrades8 = List.of(5,4,4,5);
        List<Students> listStudents = List.of(
                new Students("Anna", "Mitenkova", 4, academicGrades1),
                new Students("Sergey", "Polyakov", 3, academicGrades2),
                new Students("Irina", "Sokolova", 4, academicGrades3),
                new Students("Pavel", "Sarkisov", 5, academicGrades4),
                new Students("Alisa", "Tomova", 3,academicGrades5),
                new Students("Anton", "Uvarov", 4,academicGrades6),
                new Students("Elena", "Matveeva", 4,academicGrades7),
                new Students("Olga", "Volodina", 5,academicGrades8));


        /**
         * Преобразовываем в ассоциативный массив, где ключом является номер курса, а значением средняя оценка студентов
         * этого курса, количество оценок у которых больше 3-х */

        Map<Integer,Double> mapAverageGrades =  listStudents.stream()
                .filter(student -> student.getAcademicGrades().size() > 3)
                .collect(groupingBy(Students::getCourseNumber, averagingDouble(student -> student.getAcademicGrades()
                        .stream()
                        .mapToDouble(Integer::doubleValue)
                        .summaryStatistics()
                        .getAverage())));
        System.out.println(mapAverageGrades);


        /**
         * Преобразовываем в ассоциативный массив, где ключом является номер курса, а значением список студентов данного курса,
         * но только с полями Имя и Фамилия (список должен быть отсортирован по этим двум полям) */
        Map<Integer, List<String>> mapFirstNameAndLastName = listStudents.stream()
                .sorted(comparing(Students::getFirstName).thenComparing(Students::getLastName))
                .collect(groupingBy(Students::getCourseNumber, mapping(Students::getFullName, toList())));;
        System.out.println(mapFirstNameAndLastName);


        /**
         * Преобразовываем в ассоциативный массив, где ключом является номер курса, а значением объект с двумя полями:
         *  - отсортированный список студентов с пункта 2;
         *  - средняя оценка этих студентов */
        Map<Integer, Map<Double, List<String>>> mapRes = listStudents.stream()
                .sorted(comparing(Students::getFirstName).thenComparing(Students::getLastName))
                .collect(groupingBy(Students::getCourseNumber, TreeMap::new, groupingBy(s -> s.getAcademicGrades()
                        .stream()
                        .mapToDouble(Integer::doubleValue)
                        .summaryStatistics()
                        .getAverage(), TreeMap::new, mapping(Students::getFullName, toList()))));
        System.out.println(mapRes);
    }
}

