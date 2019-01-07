package pete.eremeykin.testing;

import org.hamcrest.Matcher;
import org.hamcrest.core.Every;
import pete.eremeykin.testing.apiclient.Response;
import pete.eremeykin.testing.apiclient.Vacancy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static pete.eremeykin.testing.asserts.ResponseMatchers.statusIsOk;
import static pete.eremeykin.testing.asserts.VacancyMatchers.nameContains;

class MetaParameter {

    private static final int MAX_LEN = 50;
    private final String name;
    private final List<Matcher<Response>> responseMatchers;
    private List<Matcher<? super Collection<Vacancy>>> vacanciesMatchers;

    private MetaParameter(Builder builder) {
        name = builder.name;
        this.responseMatchers = builder.responseMatchers;
        this.vacanciesMatchers = builder.vacanciesMatchers;
    }

    static class Builder {
        private final String name;
        private List<Matcher<Response>> responseMatchers;
        private List<Matcher<? super Collection<Vacancy>>> vacanciesMatchers;

        public Builder(String name) {
            this.name = name;
            this.responseMatchers = new ArrayList<>();
            this.vacanciesMatchers = new ArrayList<>();
        }

        public static Builder defaultOf(String name) {
            Builder builder = new Builder(name);
            builder.checkResponse(statusIsOk());
            builder.checkVacancies(hasSize(20));
            return builder;
        }

        public static Builder containsOf(String name) {
            Matcher<Iterable<Vacancy>> matcher = Every.everyItem(nameContains(""));
            return defaultOf(name).checkVacancies(matcher);
        }


        public Builder checkResponse(Matcher<Response> responseMatcher) {
            this.responseMatchers.add(responseMatcher);
            return this;
        }

        public Builder checkVacancies(Matcher<? super Collection<Vacancy>> vacanciesMatcher) {
            this.vacanciesMatchers.add(vacanciesMatcher);
            return this;
        }

        public MetaParameter build() {
            return new MetaParameter(this);
        }
    }

    public String getName() {
        return name;
    }

    public List<Matcher<Response>> getResponseMatchers() {
        return responseMatchers;
    }

    public List<Matcher<? super Collection<Vacancy>>> getVacanciesMatchers() {
        return vacanciesMatchers;
    }

    @Override
    public String toString() {
        int len = name.length();
        if (len < MAX_LEN) {
            return "name='" + name + "';";
        }
        return "name='" + name.substring(0, MAX_LEN) + "...';";
    }
}
