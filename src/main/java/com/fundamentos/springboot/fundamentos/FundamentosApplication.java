package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependecy;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private final ComponentDependency componentDependency;
	private final MyBean myBean;
	private final MyBeanWithDependecy myBeanWithDependecy;
	private final MyBeanWithProperties myBeanWithProperties;
	private final UserPojo userPojo;
	private final UserRepository userRepository;
	private final UserService userService;


	//take care about write from where class you want to inject the implementation
	public FundamentosApplication
	(@Qualifier("componentTwoImplement")
	 ComponentDependency componentDependency,
	 MyBean myBean,
	 MyBeanWithDependecy myBeanWithDependecy,
	 MyBeanWithProperties myBeanWithProperties,
	 UserPojo userPojo,
	 UserRepository userRepository,
	 UserService userService){

		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependecy = myBeanWithDependecy;
		this.myBeanWithProperties = myBeanWithProperties ;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		previewExamples();
		saveUsersIntoDataBase();
		GetDataJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional() {
		User test1 = new User("TestTransactional1", "TestTransactional1@mail.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2mail.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional3@mail.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional1@mail.com", LocalDate.now());
		User test5 = new User("TestTransactional5", "TestTransactional5@mail.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4, test5);

		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error(e + " Error into Transactional method");
		}

		userService.getAllUsers()
				.stream()
				.peek(user -> LOGGER.info("User inserted: " + user))
				.forEach(user ->
						LOGGER.info("This is the user inside Transactional method: " + user));
;	}

	private void GetDataJpqlFromUser(){
//		LOGGER.info("User by User email: " +
//				userRepository.findByUserEmail(email)
//					.orElseThrow(()->new RuntimeException("There is not a user with email: " + email)));
//
//		userRepository
//				.findAndSort("user", Sort.by("id").ascending())
//					.stream()
//						.forEach(user -> LOGGER.info("User with method sort: " + user));
//
//		userRepository
//				.findByName("Jhon")
//					.stream()
//						.forEach(user -> LOGGER.info("User with query method: " + user));
//
//		LOGGER.info("User by Name and Email by query method: " +
//			userRepository.findByNameAndEmail("Maria", "Maria@mail.com" )
//				.orElseThrow(()->new RuntimeException("There is not a user with this email and name")));
//
//		userRepository.findByNameLike("%J%")
//				.stream()
//				.forEach(user -> LOGGER.info("User found by Like :" + user));
//
//		userRepository.findByNameOrEmail("user6", null)
//					.stream()
//						.forEach(user -> LOGGER.info("User found by email or Name: "+ user));
//
//		userRepository
//				.findByBirthDateBetween(LocalDate.of(2018, 1, 1), LocalDate.of(2021, 5,2))
//					.stream()
//						.forEach(user -> LOGGER.info("User found by BirthDate: " + user));
//
//		userRepository
//				.findByNameLikeOrderByIdDesc("%user%")
//						.stream()
//								.forEach(user -> LOGGER.info("Users found By Like And ordered: " + user));
//
//		userRepository
//				.findByNameContainingOrderByIdAsc("user")
//						.stream()
//								.forEach(user -> LOGGER.info("Users found By Containing And ordered: " + user));

		LOGGER.info("User found By named parameter" +
				userRepository
					.getAllByBithDateAndEmail(LocalDate.of(2022, 2, 28), "Jhon@mail.com")
						.orElseThrow(()-> new RuntimeException("The user was not found :( ")));

		System.out.println("all here is working");
	}

	private void saveUsersIntoDataBase(){
		User user1 = new User("Jhon", "Jhon@mail.com", LocalDate.of(2022, 2, 28));
		User user11 = new User("Jhon", "Jhon1@mail.com", LocalDate.of(2022, 2, 28));
		User user12 = new User("Jhon", "Jhon2@mail.com", LocalDate.of(2022, 2, 28));
		User user2 = new User("Maria", "Maria@mail.com", LocalDate.of(2022, 3, 26));
		User user3 = new User("Juana", "Juana@mail.com", LocalDate.of(2020, 5, 27));
		User user4 = new User("user4", "user4@mail.com", LocalDate.of(2016, 1, 1));
		User user5 = new User("user5", "user5@mail.com", LocalDate.of(2020, 10, 15));
		User user6 = new User("user6", "user6@mail.com", LocalDate.of(2021, 10, 2));
		User user7 = new User("user7", "user7@mail.com", LocalDate.of(2015, 10, 22));
		User user8 = new User("user8", "user8@mail.com", LocalDate.of(2019, 10, 30));
		User user9 = new User("user9", "user9@mail.com", LocalDate.of(2020, 10, 9));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user11, user12);
//		also i can use
//		userRepository.saveAll(list);
		list.forEach(userRepository::save);
	}

	private void previewExamples(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependecy.printWithDependecy();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " " + userPojo.getPassword() + " " + userPojo.getAge());
		try {
			//error
			int value = 10/0;
			LOGGER.debug("My value is: " + value);
		}catch (Exception e){
			LOGGER.error("this is an error of divide by 0: " + e.getMessage());
		}
	}
}
