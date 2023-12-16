package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import platform.models.CodeSnippet;
import platform.models.CodeSnippetNewDTORequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public CodeSnippet save(CodeSnippetNewDTORequest dto) {
        CodeSnippet codeSnippet = new CodeSnippet();
        codeSnippet.setId(UUID.randomUUID().toString());
        codeSnippet.setCode(dto.code);
        codeSnippet.setTime(dto.time);
        codeSnippet.setViews(dto.views);
        codeSnippet.setUpdateAt(LocalDateTime.now());
        codeSnippet.setLimitTime();

        return codeRepository.save(codeSnippet);
    }

    public Optional<CodeSnippet> getCodeSnippet(String id) {
        Optional<CodeSnippet> codeOptional = codeRepository.findById(id);
        if (codeOptional.isEmpty()) {
            return Optional.empty();
        }

        CodeSnippet code = codeOptional.get();
        if (code.getLimitTime() !=null && code.getLimitTime().isBefore(LocalDateTime.now())) {
            codeRepository.delete(code);
            return Optional.empty();
        }
        checkSnippetViews(code);
        return codeOptional;
    }

    public List<CodeSnippet> getLatestCodeSnippets() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("updateAt").descending());
        return codeRepository.findByViewsAndLimitTimeIsNull(0L, paging);
    }

    private void checkSnippetViews(CodeSnippet code) {
        if (code.getViews() > 0L) {
            code.setViewLimit(true);
            long currentView = code.getViews();
            currentView -= 1L;
            code.setViews(currentView);
            if (currentView == 0L) {
                codeRepository.delete(code);
            } else {
                codeRepository.save(code);
            }
        }
    }

}
