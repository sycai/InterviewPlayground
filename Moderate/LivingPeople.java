package Moderate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sycai on 6/21/2017.
 * CCI 16.10 Given a list of people with their birth and death years, implement a method to compute the year with the
 * most number of people alive. You may assume that all people were born between 1900 and 2000 (inclusive). If a person
 * was alive during any portion of that year, they should be included in that year's count.
 * For Example, Person (birth=1908, death=1909) is included in the counts for both 1908 and 1909.
 */
public class LivingPeople {
    private static final int START_YEAR = 1900;
    private static final int END_YEAR = 2000;


    // Complexity: O(Y + P), where Y is the number of years we need to consider and P is the number of people.
    public static int yearWithMaxPeople(List<Person> people) {
        int[] populationTrend = new int[END_YEAR - START_YEAR + 1];
        for (Person p : people) {
            populationTrend[p.birth - START_YEAR]++;
            if (p.death < END_YEAR) populationTrend[p.death - START_YEAR + 1]--;
        }

        int cumuPop = 0, maxPop = -1;
        int maxYear = START_YEAR;
        for (int year = START_YEAR; year <= END_YEAR; year++) {
            cumuPop += populationTrend[year - START_YEAR];
            if (cumuPop > maxPop) {
                maxPop = cumuPop;
                maxYear = year;
            }
        }
        return maxYear;
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person(1901,1901));
        people.add(new Person(2000,2030));
        people.add(new Person(2000,2030));
        System.out.println(yearWithMaxPeople(people));
    }
}

class Person {
    int birth;
    int death;

    public Person(int b, int d) {
        birth = b;
        death = d;
    }
}
