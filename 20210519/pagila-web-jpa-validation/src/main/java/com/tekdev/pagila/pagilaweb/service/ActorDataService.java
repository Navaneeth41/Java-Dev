package com.tekdev.pagila.pagilaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekdev.pagila.pagilaweb.dao.ActorRepository;
import com.tekdev.pagila.pagilaweb.entity.Actor;

@Service
public class ActorDataService {

	@Autowired
	ActorRepository actorRepository;

	@Autowired
	ActorValidator validator;

	public List<Actor> getActors() {
		return actorRepository.findAll();
	}

	public Actor getActorById(Long actorId) {
		return actorRepository.getOne(actorId);
	}

	@Transactional
	public Actor saveActor(Actor actor) {
		validator.validate(actor, null);
		return actorRepository.save(actor);
	}

	public Actor updateActor(Actor actor) {
		return saveActor(actor);
	}

	public boolean deleteActor(Long actorId) {
		actorRepository.deleteById(actorId);
		return true;
	}

}
