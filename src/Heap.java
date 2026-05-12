//
// Title: Heap Class
// Author: [Elçin Karagül-Kayra Arı]
// ID: [10885319050-10001507]
// Section: [04]
// Assignment: 4
// Description: This class implements a min-heap structure for Course objects.
//              Courses are ordered primarily according to their z values.
//              If two courses have the same z value, the course with the
//              smaller course number has higher priority.
//

public class Heap {
    Course[] heap;
    int size;

    //
    // Summary: Creates an empty heap with the given capacity.
    // Precondition: capacity is a positive integer.
    // Postcondition: An empty heap object is created.
    //
    Heap(int capacity) {
        heap = new Course[capacity];
        size = 0;
    }

    //
    // Summary: Inserts the given course into the heap while preserving
    //          the min-heap property.
    // Precondition: course is a valid Course object.
    // Postcondition: The course is inserted into the heap in its correct position.
    //
    void insert(Course course) {
        heap[size] = course;
        int current = size;
        size++;

        while (current > 0) {
            int parent = (current - 1) / 2;

            if (compare(heap[parent], heap[current]) <= 0) {
                break;
            }

            swap(current, parent);
            current = parent;
        }
    }

    //
    // Summary: Removes and returns the minimum element of the heap.
    // Precondition: The heap is not empty.
    // Postcondition: The minimum course is removed from the heap, the heap
    //                property is restored, and the removed course is returned.
    //
    Course deleteMin() {
        if (size == 0) {
            return null;
        }

        Course min = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapifyDown(0);
        return min;
    }

    //
    // Summary: Compares two Course objects according to heap priority.
    //          First, their z values are compared. If the z values are equal,
    //          their course numbers are compared.
    // Precondition: c1 and c2 are valid Course objects.
    // Postcondition: A negative value is returned if c1 has higher priority,
    //                a positive value is returned if c2 has higher priority,
    //                and zero is returned if they are equal.
    //
    int compare(Course c1, Course c2) {
        long z1 = c1.calculateZ();
        long z2 = c2.calculateZ();

        if (z1 != z2) {
            return Long.compare(z1, z2);
        }

        return Integer.compare(c1.courseNumber, c2.courseNumber);
    }

    //
    // Summary: Restores the min-heap property starting from the given index
    //          and moving downward.
    // Precondition: i is a valid index in the heap.
    // Postcondition: The subtree rooted at index i satisfies the min-heap property.
    //
    void heapifyDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && compare(heap[left], heap[smallest]) < 0) {
                smallest = left;
            }

            if (right < size && compare(heap[right], heap[smallest]) < 0) {
                smallest = right;
            }

            if (smallest == i) {
                break;
            }

            swap(i, smallest);
            i = smallest;
        }
    }

    //
    // Summary: Swaps the elements at the given two indices in the heap.
    // Precondition: a and b are valid heap indices.
    // Postcondition: The elements at indices a and b are exchanged.
    //
    void swap(int a, int b) {
        Course temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
}
