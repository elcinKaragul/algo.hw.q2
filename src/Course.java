//
// Title: Course Class
// Author: [Elçin Karagül-Kayra Arı]
// ID: [10885319050-10001507]
// Section: [04]
// Assignment: 4
// Description: This class represents a course in the system. It stores the
//              course number, the LoH values of enrolled students, and provides
//              the necessary operations to add a student and calculate the z value
//              of the course.
//

public class Course {
    int courseNumber;
    int[] lohArray;
    int studentCount;
    int arrayCapacity;

    //
    // Summary: Creates a Course object with the given course number.
    // Precondition: courseNumber is a positive integer.
    // Postcondition: An empty course is created with an initial array capacity.
    //
    Course(int courseNumber) {
        this.courseNumber = courseNumber;
        this.arrayCapacity = 10;
        this.lohArray = new int[arrayCapacity];
        this.studentCount = 0;
    }

    //
    // Summary: Calculates and returns the z value of the course.
    //          The formula is z = x * c, where c is the number of students
    //          currently enrolled in the course, and x is determined as follows:
    //          if there are no students, x = 0;
    //          if there is one student, x is that student's LoH value;
    //          if there are at least two students, x is the sum of the last
    //          two students' LoH values.
    // Precondition: The course object has already been initialized.
    // Postcondition: The z value of the course is returned as a long value.
    //
    long calculateZ() {
        if (studentCount == 0) {
            return 0;
        }

        long x;

        if (studentCount == 1) {
            x = lohArray[0];
        } else {
            long lastStudent = lohArray[studentCount - 1];
            long secondLastStudent = lohArray[studentCount - 2];
            x = lastStudent + secondLastStudent;
        }

        long c = studentCount;
        return x * c;
    }

    //
    // Summary: Adds a new student with the given LoH value to the course.
    // Precondition: loh is a valid integer LoH value.
    // Postcondition: The student is enrolled in the course and the student count
    //                is increased by one. If necessary, the internal array is enlarged.
    //
    void enrollStudent(int loh) {
        if (studentCount == arrayCapacity) {
            growArray();
        }

        lohArray[studentCount] = loh;
        studentCount++;
    }

    //
    // Summary: Increases the capacity of the LoH array when it becomes full.
    // Precondition: The current array is full.
    // Postcondition: A new array with double capacity is created and all existing
    //                LoH values are copied into it.
    //
    private void growArray() {
        arrayCapacity *= 2;
        int[] newArray = new int[arrayCapacity];

        for (int i = 0; i < studentCount; i++) {
            newArray[i] = lohArray[i];
        }

        lohArray = newArray;
    }
}
