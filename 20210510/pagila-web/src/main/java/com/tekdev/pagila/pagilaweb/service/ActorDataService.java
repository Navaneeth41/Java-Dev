package com.tekdev.pagila.pagilaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekdev.pagila.pagilaweb.dao.PagilaDao;
import com.tekdev.pagila.pagilaweb.dto.Actor;

@Service
public class ActorDataService {
	
		@Autowired
		PagilaDao dao;
		
		public Object getActorById(Long actorid) {
			return dao.getActorById(actorid);
		}
		
		@Transactional
		public Long saveActor(Actor actor) {
			return dao.saveActor(actor);
		}

		public Long updateActor(Actor actor) {
			return dao.updateActor(actor);		
		}

		public boolean deleteActor(Long actorid) {
			return dao.deleteActor(actorid);
		}
	}



