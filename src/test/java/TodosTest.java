import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям во вторник");

    String[] subtasks = {"Молоко", "Яйца", "Хлеб", "Приложение", "Во вторник"};
    Epic epic = new Epic(55, subtasks);

    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения во вторник",
            "Приложение НетоБанка",
            "Во вторник после обеда"
    );

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryInMeeting() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = todos.search("НетоБанка");
        Task[] actual = {meeting};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryInSimpleTask() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = todos.search("Позвонить");
        Task[] actual = {simpleTask};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryInEpic() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = todos.search("Молоко");
        Task[] actual = {epic};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryTwoCoincidence() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = todos.search("Приложение");
        Task[] actual = {epic, meeting};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryThreeCoincidence() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = todos.search("вторник");
        Task[] actual = {simpleTask, epic, meeting};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQueryNoneCoincidence() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = todos.search("Спать");
        Task[] actual = {};
        Assertions.assertArrayEquals(expected, actual);
    }
}
