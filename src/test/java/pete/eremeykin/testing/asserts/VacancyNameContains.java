package pete.eremeykin.testing.asserts;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import pete.eremeykin.testing.apiclient.Vacancy;


class VacancyNameContains extends TypeSafeMatcher<Vacancy> {

    private String substring;

    VacancyNameContains(String substring) {
        this.substring = substring.toLowerCase();
    }

    @Override
    protected boolean matchesSafely(Vacancy vacancy) {
        return vacancy.getName().contains(substring);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("vacancy name contains " + substring);
    }
}
