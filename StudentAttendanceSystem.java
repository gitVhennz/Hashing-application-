import java.util.Scanner;

public class StudentAttendanceSystem {
    public static void main(String[] args) {
        HashTable<Integer, Integer> attendanceTable = new HashTable<>(10);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Student Attendance System!");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Check Attendance");
            System.out.println("4. Display All Records");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();
                    attendanceTable.put(studentId, 0);
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextInt();
                    Integer currentAttendance = attendanceTable.get(studentId);
                    if (currentAttendance != null) {
                        attendanceTable.put(studentId, currentAttendance + 1);
                        System.out.println("Attendance marked for Student ID: " + studentId);
                    } else {
                        System.out.println("Student ID not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextInt();
                    Integer attendance = attendanceTable.get(studentId);
                    if (attendance != null) {
                        System.out.println("Student ID: " + studentId + " | Attendance: " + attendance);
                    } else {
                        System.out.println("Student ID not found!");
                    }
                    break;
                case 4:
                    attendanceTable.display();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the Student Attendance System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}

class HashTable<K, V> {
    private Entry<K, V>[] table;
    private int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value;
            } else {
                current.next = newEntry;
            }
        }
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            Entry<K, V> current = table[i];
            while (current != null) {
                System.out.print("[" + current.key + "=" + current.value + "] ");
                current = current.next;
            }
            System.out.println();
        }
    }

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}