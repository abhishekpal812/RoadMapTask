package com.example.roadmap.day2;

import java.util.*;
import java.util.stream.Collectors;

public class StreamApiLambdaExample {
    public static void main(String[] args) {
//        Predicate<Integer> p=n -> n % 2 == 0;
//        BiPredicate<Integer,Integer> ui = (u1,u2) -> u1 < u2;
//        List<Integer> l1= new ArrayList<>();
//        Consumer<Integer> c= l1::add;
//        BiConsumer<List<Integer>,Integer> bc=List::add;
//        List<Integer> li = Arrays.asList(1,2,3,4,5,6,7,8);
//        Set<Integer> collect = li.stream().filter(p).collect(Collectors.toSet());
//        boolean test = p.test(10);
//        System.out.println(test);
//        c.accept(1000);
//        c.accept(2000);
//        c.accept(3000);
//        c.accept(4000);
//        bc.accept(l1,90);
//        bc.accept(l1,80);
//        System.out.println(l1);
//        System.out.println(ui.test(10,20));
        List<Integer> list = Arrays.asList(1, 17, 58, 29, 5, 2, 36, 24, 28, 78, 98, 1, 5);
        //        list.stream().reduce(Integer::sum).orElse(0);
//        double v = list.stream().mapToInt(ele -> ele).average().orElse(0);
        double v = list.stream().mapToInt(a -> a * a).filter(a -> a > 100).average().orElse(0);
        //        list.stream().mapToInt(a -> a * a).filter(a -> a > 100).forEach(System.out::println);
        List<String> collect = list.stream().map(a -> a.toString()).filter(e -> e.startsWith("2")).collect(Collectors.toList());
        Set<Integer> collect1 = list.stream().filter(num -> Collections.frequency(list, num) > 1).collect(Collectors.toSet());
        Integer i = list.stream().min(Comparator.comparing(Integer::valueOf)).get();
        List<Integer> collect2 = list.stream().sorted().collect(Collectors.toList());
        List<Integer> collect3 = list.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        //        Integer i1 = list.stream().limit(5).reduce(Integer::sum).orElse(0);
        Integer i1 = list.stream().skip(8).reduce(Integer::sum).orElse(0);
//        System.out.println(i1);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));

        Map<String, Long> collect4 = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        Set<String> collect5 = employeeList.stream().map(Employee::getDepartment).collect(Collectors.toSet());
        //        Map<String, IntSummaryStatistics> collect4 = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.summarizingInt(e -> e.age)));
        Map<String, Double> collect6 = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        //        Employee employee = employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get();
        Optional<Employee> collect7 = employeeList.stream().collect(Collectors.maxBy(Comparator.comparing(Employee::getAge)));
        List<Employee> collect8 = employeeList.stream().filter(e -> e.yearOfJoining >= 2015).collect(Collectors.toList());
        //        Map<String, Long> collect9 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        Map<String, Double> collect9 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        employeeList.stream().filter(employee -> employee.gender.equals("Male")).collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getYearOfJoining))));
        DoubleSummaryStatistics collect10 = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        Map<Boolean, List<Employee>> collect11 = employeeList.stream().collect(Collectors.partitioningBy(employee -> employee.getAge() >= 29));
        System.out.println(collect11);
    }
}