package com.javarush.task.task23.task2305;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solution = {new Solution(), new Solution()};
        solution[0].innerClasses[0] = solution[1].innerClasses[0] = new Solution().new InnerClass();
        solution[0].innerClasses[1] = solution[1].innerClasses[1] = new Solution().new InnerClass();
        return solution;
    }

    public static void main(String[] args) {

    }
}
