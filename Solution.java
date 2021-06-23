
public class Solution {

    /*
    By the problem design on binarysearch.com, we have to work
    around the given method 'public int solve(int[] customers, int[] mood, int k)' 
    so that the code can be run on the website. Even though the name 'solve' 
    does not make a lot of sense, it is left as it is, so that the code can 
    be run directly on the website, without any modifications.
     */
    public int solve(int[] customers, int[] mood, int k) {
        return calculate_maximumHappyPeople(customers, mood, k);
    }

    public int calculate_maximumHappyPeople(int[] customers, int[] mood, int k) {

        int initialSum = sumAllPeople_withInitialValueOf_one_inArrayMood(customers, mood);
        int slidingWindow = slidingWindow_withLength_k(customers, mood, k);

        int maximum = initialSum + slidingWindow;
        int currentValue = maximum;

        int startOfSlidingWindow = 0;
        int endOfSlidingWindow = k;

        while (endOfSlidingWindow < mood.length) {
            /*
            Element that goes out of the sliding window.
            - If the initial value of the element in array 'mood' is '1', do nothing.
            - Otherwise, decrease the current value with the number of people
              in array 'customers'.
             */
            if (mood[startOfSlidingWindow] == 0) {
                currentValue -= customers[startOfSlidingWindow];
            }
            /*
            Element that goes into of the sliding window.
            - If the initial value of the element in array 'mood' is '1', do nothing.
            - Otherwise, increase the current value with the number of people
              in array 'customers'.
             */
            if (mood[endOfSlidingWindow] == 0) {
                currentValue += customers[endOfSlidingWindow];
            }
            maximum = Math.max(maximum, currentValue);
            startOfSlidingWindow++;
            endOfSlidingWindow++;
        }
        return maximum;
    }

    //Sum of all initially happy people.
    public int sumAllPeople_withInitialValueOf_one_inArrayMood(int[] customers, int[] mood) {

        int sum = 0;

        for (int i = 0; i < customers.length; i++) {
            if (mood[i] == 1) {
                sum += customers[i];
            }
        }
        return sum;
    }

    //Sum of all people that can additionally be made happy in the first window of length k.
    public int slidingWindow_withLength_k(int[] customers, int[] mood, int k) {

        k = k > mood.length ? mood.length : k;
        int sum = 0;

        for (int i = 0; i < k; i++) {
            if (mood[i] == 0) {
                sum += customers[i];
            }
        }
        return sum;
    }

}
