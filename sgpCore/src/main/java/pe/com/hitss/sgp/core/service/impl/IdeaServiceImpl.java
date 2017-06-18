package pe.com.hitss.sgp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.hitss.sgp.core.dao.IdeaDao;
import pe.com.hitss.sgp.core.domain.Idea;
import pe.com.hitss.sgp.core.service.IdeaService;
import pe.com.hitss.sgp.core.util.Resultado;

@Service("ideaService")
public class IdeaServiceImpl implements IdeaService {

	@Autowired
	private IdeaDao ideaDao;
	
	@Override
	public List<Idea> listarIdea(Idea idea) throws Exception {
		return ideaDao.listarIdea(idea);
	}

	@Override
	public Resultado grabarIdea(Idea idea) throws Exception {
		return ideaDao.grabarIdea(idea);
	}

}
