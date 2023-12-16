package platform;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import platform.models.CodeSnippet;

import java.util.List;

@Component
public interface CodeRepository extends CrudRepository<CodeSnippet, String> {

    List<CodeSnippet> findByViewsAndLimitTimeIsNull(Long views, Pageable pageable);
}
