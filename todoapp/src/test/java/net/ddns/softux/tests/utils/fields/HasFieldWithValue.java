package net.ddns.softux.tests.utils.fields;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.lang.reflect.Field;

public class HasFieldWithValue<T> extends TypeSafeDiagnosingMatcher<T> {
    private final String fieldName;
    private final Matcher<Object> valueMatcher;

    public HasFieldWithValue(String fieldName, Matcher<?> valueMatcher) {
        this.fieldName = fieldName;
        this.valueMatcher = nastyGenericsWorkaround(valueMatcher);
    }

    @SuppressWarnings("unchecked")
    private static Matcher<Object> nastyGenericsWorkaround(Matcher<?> valueMatcher) {
        return (Matcher<Object>) valueMatcher;
    }

    @Factory
    public static <T> Matcher<T> hasField(String fieldName, Matcher<?> valueMatcher) {
        return new HasFieldWithValue<T>(fieldName, valueMatcher);
    }

    private Field getField(String fieldName, Object fromObj) throws IllegalArgumentException {
        for (Field f : fromObj.getClass().getFields()) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }

        return null;
    }

    @Override
    public boolean matchesSafely(T pojo, Description mismatch) {
        try {
            Field field = getField(fieldName, pojo);
            return valueMatcher.matches(field.get(pojo));
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("hasField(").appendValue(fieldName).appendText(", ")
                .appendDescriptionOf(valueMatcher).appendText(")");
    }
}
