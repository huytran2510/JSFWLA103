package fa.training.service;

import fa.training.entities.Content;
import fa.training.repository.ContentRepository;
import fa.training.service.templates.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public List<Content> getAll() {
        return contentRepository.findAll();
    }

    @Override
    public void save(Content content){
        contentRepository.save( content);
    }
}
