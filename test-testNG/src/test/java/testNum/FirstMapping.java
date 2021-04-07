package testNum;

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;
import testNum.services.DataService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstMapping {

    @Test
    public void checkGettingJsonData() throws Exception {
        ImmutableList<Person> people = DataService.getPeople();
        people.forEach(System.out::println);
        assertThat(people).hasSize(1000);
        System.out.println(" ///////////////////////// ");
        String people1 = people.get(0).setFirstName(people.get(1).getFirstName() + "Marie");
        List<Person> persons = new ArrayList<>();
    }

    public static void main(String[] args) {
        String x = "hello";
        x.toUpperCase();
    }

}
