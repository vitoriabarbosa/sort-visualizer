package sav;

public class CharacterSorter {
    public static int sort(Character[] array) {
        int iterations = 0;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                iterations++;
                if (array[i] > array[i + 1]) {
                    char temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
        return iterations;
    }
}
