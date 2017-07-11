package Hard;

import java.util.*;

/**
 * Created by sycai on 7/10/2017.
 * CCI 17.8 A circus is designing a tower routine consisting of people standing atop one another's shoulders. For
 * practical and aesthetic reasons, each person must be both shorter and lighter than the person below him or her.
 * Given the heights and weights of each person in the circus, write a method to compute the largest possible number
 * of people in such a tower.
 *
 * EXAMPLE
 * Input: (ht, wt): (65, 100) (70, 150) (56, 90) (75, 190) (60, 95) (68, 110)
 * Output: The longest tower is length 6 and includes from top to bottom
 *  (56, 90) (60, 95) (65, 100) (68, 110) (70, 150) (75, 190)
 */
public class CircusTower {
    private static class Person {
        int height;
        int weight;
        Person below;

        public Person(int h, int w) {
            height = h;
            weight = w;
            below = null;
        }

        public boolean canHold(Person other) {
            return this.weight > other.weight && this.height > other.height;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", height, weight);
        }
    }

    public static List<Person> longestTower(List<Person> people) {
        ArrayList<Person> sortedPeople = new ArrayList<>(people);
        // People most likely to appear at tower bottom comes first
        Collections.sort(sortedPeople, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return (o2.height * o2.weight) - (o1.height * o1.weight);
            }
        });

        int[] maxCount = new int[sortedPeople.size()];

        // Longest path in a DAG
        for (int i = 0; i < sortedPeople.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (sortedPeople.get(j).canHold(sortedPeople.get(i)) && maxCount[i] < maxCount[j] + 1) {
                    maxCount[i] = maxCount[j] + 1;
                    sortedPeople.get(i).below = sortedPeople.get(j);
                }
            }
        }
        int maxOfAll = 0, maxOfAllIdx = 0;
        for (int i = 0; i < maxCount.length; i++) {
            if (maxCount[i] > maxOfAll) {
                maxOfAll = maxCount[i];
                maxOfAllIdx = i;
            }
        }

        // Build tower.
        List<Person> res = new LinkedList<>();
        for (Person curr = sortedPeople.get(maxOfAllIdx); curr != null; curr = curr.below) {
            res.add(curr);
        }
        return res;
    }

    public static void main(String[] args) {
       List<Person> people = new LinkedList<>();
       people.add(new Person(65, 100));
       people.add(new Person(70, 150));
       people.add(new Person(56, 90));
       people.add(new Person(75, 190));
       people.add(new Person(60, 95));
       people.add(new Person(68, 110));

       System.out.println(longestTower(people));
    }
}
