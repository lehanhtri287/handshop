package com.example.demo.controller;

import org.springframework.stereotype.Controller;

@Controller
public class MainxController {

	// private static List<Person> persons = new ArrayList<Person>();
	//
	// static {
	// persons.add(new Person("Bill", "Gates"));
	// persons.add(new Person("Steve", "Jobs"));
	// }
	//
	// // Được tiêm vào (inject) từ application.properties.
	// @Value("${welcome.message}")
	// private String message;
	//
	// @Value("${error.message}")
	// private String errorMessage;
	//
	// @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	// public String index(Model model) {
	//
	// model.addAttribute("message", message);
	//
	// return "index2";
	// }
	//
	// @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
	// public String personList(Model model) {
	//
	// model.addAttribute("persons", persons);
	//
	// return "personList";
	// }
	//
	// @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
	// public String showAddPersonPage(Model model) {
	//
	// PersonForm personForm = new PersonForm();
	// model.addAttribute("personForm", personForm);
	//
	// return "addPerson";
	// }
	//
	// @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
	// public String savePerson(Model model, //
	// @ModelAttribute("personForm") PersonForm personForm) {
	//
	// String firstName = personForm.getFirstName();
	// String lastName = personForm.getLastName();
	//
	// if (firstName != null && firstName.length() > 0 //
	// && lastName != null && lastName.length() > 0) {
	// Person newPerson = new Person(firstName, lastName);
	// persons.add(newPerson);
	//
	// return "redirect:/personList";
	// }
	//
	// model.addAttribute("errorMessage", errorMessage);
	// return "addPerson";
	// }
}