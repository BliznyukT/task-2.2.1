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
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Ivan", "Ivanov", "ivan@mail.ru");
      User user2 = new User("Petr", "Sidorov", "petr@gmail.com");
      User user3 = new User("Oleg", "Gromov", "olegg@gmail.com");

      Car car1 = new Car("Niva", 21, user1);
      Car car2 = new Car("Lada", 6, user2);
      Car car3 = new Car("Nissan", 3, user3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      userService.add(car1);
      userService.add(car2);
      userService.add(car3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(users.get(1));

      System.out.println(userService.getUser("Niva", 21));
      System.out.println(userService.getUser("Lada", 6));
      System.out.println(userService.getUser("Nissan", 3));

      context.close();
   }
}
