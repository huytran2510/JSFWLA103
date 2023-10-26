package fa.training.service.templates;

import fa.training.entities.Content;

import java.util.List;

public interface ContentService {
    List<Content> getAll();

    void save(Content content);
}
