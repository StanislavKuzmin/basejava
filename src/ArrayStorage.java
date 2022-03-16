/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Array of resumes is full, can't save resume");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
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
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] storageWithoutNull = new Resume[size];
        for (int i = 0; i < size; i++) {
            storageWithoutNull[i] = storage[i];
        }
        return storageWithoutNull;
    }

    int size() {
        return size;
    }
}
