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
    // Summary: Reads the input, initializes the system, performs the course
    //          selection process for Bill and his friends, and prints the results.
    // Precondition: Input is provided in the correct format.
    // Postcondition: The selected course numbers are printed to the screen.
    //
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of courses, total number of people, and
        // number of previously enrolled students.
        int C = scanner.nextInt();
        int P = scanner.nextInt();
        int N = scanner.nextInt();

        // Create course objects from course 1 to course C.
        // The array is created as 1-indexed for easier course access.
        Course[] courses = new Course[C + 1];
        for (int i = 1; i <= C; i++) {
            courses[i] = new Course(i);
        }

        // Enroll the previously registered students.
        // The i-th student is assigned to course (i % C) + 1.
        for (int i = 0; i < N; i++) {
            int loh = scanner.nextInt();
            int courseNum = (i % C) + 1;
            courses[courseNum].enrollStudent(loh);
        }

        // Read the LoH values of Bill and his friends.
        int[] billAndFriends = new int[P];
        for (int i = 0; i < P; i++) {
            billAndFriends[i] = scanner.nextInt();
        }

        // Create a min-heap and insert all courses into it.
        Heap courseHeap = new Heap(C);
        for (int i = 1; i <= C; i++) {
            courseHeap.insert(courses[i]);
        }

        // Store the selected course numbers.
        int[] result = new int[P];

        // Process Bill and his friends one by one.
        // Each time, the course with minimum z value is selected.
        for (int i = 0; i < P; i++) {
            Course selectedCourse = courseHeap.deleteMin();
            result[i] = selectedCourse.courseNumber;
            selectedCourse.enrollStudent(billAndFriends[i]);
            courseHeap.insert(selectedCourse);
        }

        // Print the selected course numbers.
        for (int i = 0; i < P; i++) {
            System.out.print(result[i]);
            if (i < P - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        scanner.close();
    }
}
