//
// Title: Course Selection System Tester Class
// Author: [Elçin Karagül-Kayra Arı]
// ID: [10885319050-10001507]
// Section: [04]
// Assignment: 4
// Description: This class reads the input data for the course selection system,
//              creates the courses, enrolls the previously registered students,
//              processes Bill and his friends one by one, and prints the course
//              numbers selected by each person according to the minimum z value rule.
//

import java.util.Scanner;

public class Main {

    //
    // Summary: Reads the input, initializes the system, performs the course selection process for Bill and his friends, and prints the results.
    // Precondition: Input is provided in the correct format.
    // Postcondition: The selected course numbers are printed to the screen.
    //
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Read the number of courses, total number of people, and number of previously enrolled students.
        int courseCount = input.nextInt();
        int peopleCount = input.nextInt();
        int enrolledCount = input.nextInt();

        // Create course objects from course 1 to course C.
        // The array is created as 1-indexed for easier course access.
        Course[] courses = new Course[courseCount + 1];

        for (int courseNo = 1; courseNo <= courseCount; courseNo++) {
            courses[courseNo] = new Course(courseNo);
        }

        // Enroll the previously registered students.
        // The i-th student is assigned to course (i % C) + 1.
        for (int studentIndex = 0; studentIndex < enrolledCount; studentIndex++) {
            int lohVal = input.nextInt();
            int courseNo = (studentIndex % courseCount) + 1;

            courses[courseNo].enrollStudent(lohVal);
        }

        // Read the LoH values of Bill and his friends.
        int[] groupLoHVals = new int[peopleCount];

        for (int personIndex = 0; personIndex < peopleCount; personIndex++) {
            groupLoHVals[personIndex] = input.nextInt();
        }

        // Create a min-heap and insert all courses into it.
        Heap courseHeap = new Heap(courseCount);

        for (int courseNo = 1; courseNo <= courseCount; courseNo++) {
            courseHeap.insert(courses[courseNo]);
        }

        // Store the selected course numbers.
        int[] selectedCourses = new int[peopleCount];

        // Process Bill and his friends one by one.
        // Each time, the course with minimum z value is selected.
        for (int personIndex = 0; personIndex < peopleCount; personIndex++) {
            Course minCourse = courseHeap.deleteMin();

            selectedCourses[personIndex] = minCourse.courseNumber;
            minCourse.enrollStudent(groupLoHVals[personIndex]);

            courseHeap.insert(minCourse);
        }

        // Print the selected course numbers.
        for (int personIndex = 0; personIndex < peopleCount; personIndex++) {
            System.out.print(selectedCourses[personIndex]);

            if (personIndex < peopleCount - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        input.close();
    }
}