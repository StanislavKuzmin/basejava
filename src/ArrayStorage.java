/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int size = size();
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

     void save(Resume resume) {
         int size = size();
         if (size == storage.length) {
             throw new ArrayIndexOutOfBoundsException("Array of resume is full, can't save resume");
         }
         storage[size] = resume;
    }

    Resume get(String uuid) {
        int size = size();
        Resume resume = null;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resume = storage[i];
            }
        }
        return resume;
    }

    void delete(String uuid) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                for (int j = i; j < size; j++) {
                    if (j < storage.length - 2) {
                        storage[j] = storage[j + 1];
                    }
                    if (j == storage.length - 1) {
                        storage[j] = null;
                    }
                }
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = size();
        Resume[] storageWithoutNull = new Resume[size];
        for (int i = 0; i < size; i++) {
            storageWithoutNull[i] = storage[i];
        }
        return storageWithoutNull;
    }

    int size() {
        int count = 0;
        while (storage[count] != null) {
            count++;
            if (count > storage.length - 1) {
                break;
            }
        }
        return count;
    }
}
