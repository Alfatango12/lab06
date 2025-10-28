package it.unibo.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        final int ELEMS = 100_000;
        final int nReads = 1000;
        final String writeOperation = "Write";
        final String readOperation = "Read";
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        List<Integer> array = new ArrayList<>();
        for (int i = 1000; i < 2000; i++) {
            array.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        List<Integer> list = new LinkedList<>(array);
     
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int temp = array.getFirst();
        array.set(0, array.getLast());
        array.set((array.size() - 1), temp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        Iterator<Integer> it = array.iterator();
        while(it.hasNext()) {
            System.out.print("| " + it.next() + " | ");
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */

        /* ArrayList */
        long arrayListTime = System.nanoTime();

        for (int i = 0; i < ELEMS; i++) {
            array.addFirst(i);
        }

        arrayListTime = System.nanoTime() - arrayListTime;
        long arrayMillis = TimeUnit.NANOSECONDS.toMillis(arrayListTime);

        /* Linked List */

        long linkedListTime = System.nanoTime();

        for (int i = 0; i < ELEMS; i++) {
            list.addFirst(i);
        }

        linkedListTime = System.nanoTime() - linkedListTime;
        long listMillis = TimeUnit.NANOSECONDS.toMillis(linkedListTime);

        printBenchmarkTime(arrayMillis, "ArrayList", ELEMS, writeOperation);
        printBenchmarkTime(listMillis, "LinkedList", ELEMS, writeOperation);

        
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */

        /* ArrayList */

        arrayListTime = System.nanoTime();
        int arraySize = array.size();
        for (int i = 0; i < nReads; i++) {
            array.get(arraySize / 2);
        }

        arrayListTime = System.nanoTime() - arrayListTime;
        arrayMillis = TimeUnit.NANOSECONDS.toMillis(arrayListTime);

        linkedListTime = System.nanoTime();
        int listSize = list.size();
        for (int i = 0; i < nReads; i++) {
            list.get(listSize / 2);
        }

        linkedListTime = System.nanoTime() - linkedListTime;
        listMillis = TimeUnit.NANOSECONDS.toMillis(linkedListTime);

        printBenchmarkTime(arrayMillis, "ArrayList", ELEMS, readOperation);
        printBenchmarkTime(listMillis, "LinkedList", ELEMS, readOperation);


        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        /*
         * 8) Compute the population of the world
         */
    }

    public static void printBenchmarkTime(long millis, final String varName, final int nElements, String operation) {
        System.out.println();
        System.out.println("Time needed to " + operation + " " + nElements + " elements in a " + varName + " took " + millis + "ms");
    }
}
