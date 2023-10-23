import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMatchesMeetingTopic() {
        Meeting meeting = new Meeting(
                406,
                "Тестировка",
                "Нетология",
                "В четверг после дождя"
        );
        boolean expected = true;
        boolean actual = meeting.matches("Тестировка");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMatchesMeetingProject() {
        Meeting meeting = new Meeting(
                406,
                "Тестировка",
                "Нетология",
                "В четверг после дождя"
        );
        boolean expected = true;
        boolean actual = meeting.matches("Нетология");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindMatchesMeeting() {
        Meeting meeting = new Meeting(
                406,
                "Тестировка",
                "Нетология",
                "В четверг после дождя"
        );
        boolean expected = false;
        boolean actual = meeting.matches("четверг");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMatchesSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(675, "Купить продукты");
        boolean expected = true;
        boolean actual = simpleTask.matches("продукты");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindMatchesSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(675, "Купить продукты");
        boolean expected = false;
        boolean actual = simpleTask.matches("сосиски");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMatchesEpic() {
        String[] subtasks = {"Наушники", "Микрофон", "Пульт"};
        Epic epic = new Epic(985, subtasks);
        boolean expected = true;
        boolean actual = epic.matches("Пульт");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindMatchesEpic() {
        String[] subtasks = {"Наушники", "Микрофон", "Пульт"};
        Epic epic = new Epic(985, subtasks);
        boolean expected = false;
        boolean actual = epic.matches("Динамики");
        Assertions.assertEquals(expected, actual);
    }

    // Тест на нахождение одной задачи
    @Test
    public void shouldFindFromAllTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {epic};
        Task[] actual = todos.search("Хлеб");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на нахождение двух задач сразу
    @Test
    public void shouldFindFromTwoTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб", "Позвонить родителям"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("Позвонить родителям");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на ненахождение задач
    @Test
    public void shouldNotFindAny() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб", "Позвонить родителям"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {};
        Task[] actual = todos.search("Смена языка");
        Assertions.assertArrayEquals(expected, actual);
    }
}