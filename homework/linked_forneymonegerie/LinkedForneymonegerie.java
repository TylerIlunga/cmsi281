package linked_forneymonegerie;

import java.util.NoSuchElementException;

public class LinkedForneymonegerie implements LinkedForneymonegerieInterface {
    private ForneymonType head, tail;
    private int size;
    private int typeSize;
    private int modCount;

    LinkedForneymonegerie() {
        head = null;
        size = 0;
        typeSize = 0;
        modCount = 0;
    }

    // Methods
    // ----------------------------------------------------------
    public boolean empty () {
        return size == 0;
    }

    public int size () {
        return size;
    }

    public int typeSize () {
        return typeSize;
    }

    public boolean collect (String typeToAdd) {
        return insertForneymon(typeToAdd);
    }

    public boolean release (String typeToRelease) {
        return removeForneymon(typeToRelease, 1);
    }

    public void releaseType (String typeToNuke) {
        ForneymonType current = find(typeToNuke);
        if (current != null) {
          removeForneymon(typeToNuke, current.count);
        }
    }

    public int countType (String typeToCount) {
        ForneymonType current = find(typeToCount);
        if (current != null) {
          return current.count;
        }
        return 0;
    }

    public boolean contains (String typeToCheck) {
        return find(typeToCheck) != null;
    }

    public String rarestType () {
        if(empty()) { return null; }

        String rarest = null;
        ForneymonType current = head;
        int min = current.count;
        while(current != null) {
            if(min > current.count) {
              min = current.count;
              rarest = current.text;
            }
            if (current.next == null && rarest == null) {
              rarest = current.text;
            }
            current = current.next;
        }
        return rarest;
    }

    public LinkedForneymonegerie clone () {
        LinkedForneymonegerie clone = new LinkedForneymonegerie();
        populateClone(clone);
        clone.size = size;
        clone.typeSize = typeSize;
        return clone;
    }

    public void trade (LinkedForneymonegerie other) {
        ForneymonType tempHead = head;
        ForneymonType tempTail = tail;
        int tempSize = size;
        int tempTypeSize = typeSize;
        int tempModCount = modCount;

        head = other.head;
        tail = other.tail;
        size = other.size;
        typeSize = other.typeSize;
        modCount = other.modCount;

        other.head = tempHead;
        other.tail = tempTail;
        other.size = tempSize;
        other.typeSize = tempTypeSize;
        other.modCount = tempModCount;
    }

    public LinkedForneymonegerie.Iterator getIterator() {
        return new Iterator(LinkedForneymonegerie.this);
    }

    @Override
    public String toString() {
        String output = "[ ";
        ForneymonType current = head;
        while (current != null) {
            if (current.next == null) {
                output += String.format("\"%s\": %d ]", current.text, current.count);
            } else {
                output += String.format("\"%s\": %d, ", current.text, current.count);
            }
            current = current.next;
        }
        return output;
    }

    // Static methods
    // ----------------------------------------------------------
    public static LinkedForneymonegerie diffMon (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
        LinkedForneymonegerie diff = y1.clone();
        ForneymonType current = y2.head;
        while (current != null) {
          diff.removeForneymon(current.text, current.count);
          current = current.next;
        }
        return diff;
    }

    public static boolean sameCollection (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
        return diffMon(y1, y2).empty() && y1.size == y2.size && y1.typeSize == y2.typeSize;
    }

    // Private helper methods
    // ----------------------------------------------------------
    private ForneymonType createForneymonType(String text, int count) {
        return new ForneymonType(text, count);
    }

    private boolean insertForneymon(String text) {
        boolean newType = true;
        if (size == 0) {
            head = createForneymonType(text, 1);
            tail = head;
            size++;
            typeSize++;
            modCount++;
            return newType;
        }

        ForneymonType current = head;
        while(current != null) {
            if(current.text == text) {
                current.count++;
                size++;
                modCount++;
                newType = false;
                break;
            }
            if (current == tail) {
                ForneymonType newFM = createForneymonType(text, 1);
                current.next = newFM;
                newFM.prev = current;
                tail = newFM;
                size++;
                typeSize++;
                modCount++;
                break;
            }
            current = current.next;
        }
        return newType;
    }

    private ForneymonType find(String s) {
        ForneymonType current = head;
        while(current != null) {
            if (current.text == s) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private boolean removeForneymon(String text, int count) {
        ForneymonType current = find(text);
        if (current == null) {
            return false;
        }

        if (count > current.count) {
            throw new IllegalArgumentException();
        }

        int newCount = current.count - count;
        if (newCount > 0) {
            current.count = newCount;
            size -= count;
            modCount++;
            return true;
        }

        if (typeSize < 2) {
            size -= count;
            modCount++;
            head = null;
            tail = null;
            typeSize = 0;
            return true;
        }

        if (current == head) {
            head = current.next;
            head.prev = null;
            size -= count;
            typeSize--;
            modCount++;
            return true;
        }

        if (current == tail) {
            tail = tail.prev;
            tail.next = null;
            size -= count;
            typeSize--;
            modCount++;
            return true;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size -= count;
        typeSize--;
        modCount++;
        return true;
    }

    private LinkedForneymonegerie.Iterator getIteratorAt(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }

        Iterator iter = new Iterator(LinkedForneymonegerie.this);
        while(index > 0) {
            iter.next();
            index--;
        }

        return iter;
    }

    private void populateClone(LinkedForneymonegerie clone) {
        ForneymonType current = head;
        while(current != null) {
            if (current.count > 1) {
                for (int i = 0; i < current.count; i++) {
                    clone.collect(current.text);
                }
            } else {
                clone.collect(current.text);
            }
            current = current.next;
        }
    }

    public class Iterator implements LinkedForneymonegerieIteratorInterface {
        private ForneymonType current;
        private int leftToAccess;
        private LinkedForneymonegerie owner;
        private int itModCount;

        Iterator(LinkedForneymonegerie y) {
            owner = y;
            current = y.head;
            leftToAccess = current.count;
            itModCount = y.modCount;
        }

        public boolean isValid() {
            return owner.modCount == itModCount;
        }

        public boolean hasNext() {
            return isValid() && current != null &&
                    (current.next != null || leftToAccess > 1);
        }

        public boolean hasPrev() {
            return isValid() && current != null &&
                    (current.prev != null || leftToAccess > 1);
        }

        public String getType() {
            if (!isValid()) { return null; }
            return current.text;
        }

        public void next() {
            if (!isValid()) { throw new IllegalStateException(); }
            if (current.next == null && leftToAccess == 1) {
              throw new NoSuchElementException();
            }
            evaluateCurrentPosition(current.next);
        }

        public void prev() {
            if (!isValid()) { throw new IllegalStateException(); }
            if (current.prev == null && leftToAccess == 1) {
              throw new NoSuchElementException();
            }
            evaluateCurrentPosition(current.prev);
        }

        public void replaceAll(String typeToReplaceWith) {
            if (!isValid()) { throw new IllegalStateException(); }

            if (current != null && current.text != typeToReplaceWith) {
                ForneymonType temp = find(typeToReplaceWith);
                if (temp != null) {
                    temp.count = temp.count + current.count;
                } else {
                    temp = owner.head;
                    while(temp != null) {
                        if (temp.next == null) {
                            temp.next = createForneymonType(typeToReplaceWith, current.count);
                            break;
                        }
                        temp = temp.next;
                   }
                }
                itModCount++;
                owner.modCount++;
            }
        }

        private void evaluateCurrentPosition(ForneymonType nextToEvaluate) {
            if (leftToAccess == 1) {
                current = nextToEvaluate;
                leftToAccess = current.count;
            } else {
                leftToAccess--;
            }
        }

        private ForneymonType find(String s) {
            ForneymonType current = owner.head;
            while(current != null) {
                if (current.text == s) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }

    }

    public class ForneymonType {
        ForneymonType next, prev;
        String text;
        int count;

        ForneymonType (String t, int c) {
            text = t;
            count = c;
        }

        public String toString() {
            return String.format("[ \"%s\": %d ]", text, count);
        }

    }
}
