package rent.common.util;

import rent.common.ValidationException;

import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.collect.Lists.newArrayList;


public class Validators {

    public static final String VALUE_FOR = "Value for: '";

    public static void notNullNotEmpty(String str, String propertyName) {
        notNull(str, propertyName);
        if (str.isEmpty()) {
            throw new ValidationException(VALUE_FOR + propertyName + "' cannot be an empty String!");
        }
    }

    public static void notNull(Object obj, String propertyName) {
        if (obj == null) {
            throw new ValidationException(VALUE_FOR + propertyName + "' cannot be null!");
        }
    }

    public static void nullOrEmpty(String str, String propertyName) {
        if (str != null && !str.isEmpty()) {
            throw new ValidationException(VALUE_FOR + propertyName + "' must be null or empty");
        }
    }

    public static void notNullOrEmpty(List<?> list, String propertyName) {
        notNull(list, propertyName);
        if (list.isEmpty()) {
            throw new ValidationException(VALUE_FOR + propertyName + "' cannot be an empty list");
        }
    }

    public static void noNullElements(List<?> list, String listName) {
        for (Object element : list) {
            if (element == null) {
                throw new ValidationException("'" + listName + "' cannot contain null elements");
            }
        }
    }

    public static void noEmptyElements(List<String> list, String listName) {
        for (String element : list) {
            if (element.isEmpty()) {
                throw new ValidationException("'" + listName + "' cannot contain empty elements");
            }
        }
    }

    public static void notNegative(int val, String propertyName) {
        if (val < 0) {
            throw new ValidationException(VALUE_FOR + propertyName + "' cannot be less than zero");
        }
    }

    public static void notNegative(double val, String propertyName) {
        if (val < 0) {
            throw new ValidationException(VALUE_FOR + propertyName + "' cannot be less than zero");
        }
    }

    public static void areEqual(int firstValue, int secondValue, String firstPropertyName, String secondPropertyName) {
        if (firstValue != secondValue) {
            throw new ValidationException(firstPropertyName + " must be qual to " + secondPropertyName);
        }
    }

    public static void sameSize(String propertiesNames, List<?>... lists) {
        long count = newArrayList(lists)
                .stream()
                .map(List::size)
                .distinct()
                .count();
        if (count != 1) {
            throw new ValidationException(propertiesNames + " must be the same size");
        }
    }

    public static void longerOrEqualSize(List<?> first, List<?> second, String firstListName, String secondListName) {
        if (first.size() < second.size()) {
            throw new ValidationException(firstListName + " must be longer or equal size to " + secondListName);
        }
    }

    public static void sameElements(List<?> first, List<?> second, String firstListName, String secondListName) {
        sameSize(firstListName + " and " + secondListName + "lists", first, second);
        boolean itemsEqual = IntStream.range(0, first.size())
                .allMatch(i -> first.get(i).equals(second.get(i)));
        if (!itemsEqual) {
            throw new ValidationException(firstListName + " has to have same elements as " + secondListName);
        }
    }

    public static void greaterThanOrEqual(double first, double second, String firstComment, String secondComment) {
        if (first > second) {
            throw new ValidationException(firstComment + " cannot be greater than " + secondComment);
        }
    }
}
