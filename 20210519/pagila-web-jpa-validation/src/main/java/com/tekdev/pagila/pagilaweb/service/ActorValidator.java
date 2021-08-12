package com.tekdev.pagila.pagilaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tekdev.pagila.pagilaweb.dao.ActorRepository;
import com.tekdev.pagila.pagilaweb.entity.Actor;

@Component
public class ActorValidator implements Validator {
	@Autowired
	ActorRepository actorRepository;

	@Override
	public boolean supports(Class clazz) {
		return Actor.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Actor a = (Actor) target;
		if (a.getActorId() != null) {
			Actor existingActor = actorRepository.getOne(a.getActorId());
			if (existingActor == null) {
				errors.rejectValue("123", "invalid actor");
			}
		}
	}

}
