package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        Car car1 = new Car("Car1", 10);
        user1.setUserCar(car1);
        userService.add(user1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        Car car2 = new Car("Car2", 20);
        user2.setUserCar(car2);
        userService.add(user2);

        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        Car car3 = new Car("Car3", 30);
        user3.setUserCar(car3);
        userService.add(user3);

        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        Car car4 = new Car("Car4", 40);
        user4.setUserCar(car4);
        userService.add(user4);

            List<User> users = userService.listUsers();
            for (User user : users) {
                System.out.println("User_id = " + user.getId());
                System.out.println("First_name = " + user.getFirstName());
                System.out.println("Last_name = " + user.getLastName());
                System.out.println("Email = " + user.getEmail());
                System.out.println("User_car:");
                System.out.println("Car_model = " + user.getUserCar().getModel());
                System.out.println("Car_series = " + user.getUserCar().getSeries());
                System.out.println("______________________");
            }

            String model = "Car3";
            int series = 30;
            User userWithAuto = userService.getUserByModelAndSeriesCar(model, series);
            System.out.println("Выбор юзера по модели и серии автомобиля: " +
                    "\nUser_id = " + userWithAuto.getId() +
                    "\nFirst_name = " + userWithAuto.getFirstName() +
                    "\nLast_name = " + userWithAuto.getLastName() +
                    "\nEmail = " + userWithAuto.getEmail());
        }
    }
}
