package com.tekdev.pagila.pagilaweb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.tekdev.pagila.pagilaweb.dto.Actor;
import com.tekdev.pagila.pagilaweb.service.ActorDataService;

@Controller
@RequestMapping("/actor_app")
public class ActorAppController {

	@Autowired
	ActorDataService actorDataService;
	
	@GetMapping("/actor")
	public String getActor( Model model) {
		
	
		return "actorinfo";
	}
	
	@GetMapping("/actor/{actorid}")
	public String getActor(@PathVariable Long actorid, Model model) {
		model.addAttribute("actor", actorDataService.getActorById(actorid));
		return "actorinfo";
	}
	
	@PostMapping(path = "/actor")
	public String addActor(@ModelAttribute("actorFormData") Actor actor,  ModelMap model) {
		actorDataService.saveActor(actor);
        model.addAttribute("actor", actor);
		model.addAttribute("statusMessage", "actor add is Successful.");
		return "actorinfo";
	}
	
	// put method is not suppored by html 1.x form method
	@PostMapping(path = "/update_actor/{actorid}")
	public RedirectView updateActor(@ModelAttribute("actorFormData") Actor actor,  ModelMap model) {
		actorDataService.updateActor(actor);
        model.addAttribute("actor", actor);
		model.addAttribute("statusMessage", "actor Update is Successful.");
		return new RedirectView("/actor_app/actor/" + actor.getActorid());
	}
}
