package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import platform.CodeService;
import platform.models.CodeSnippet;

import java.util.List;
import java.util.Optional;

@Controller
public class CodeController {

    @Autowired
    CodeService codeService;

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewCode() {
        return "postCode";
    }

    @GetMapping(value = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getCode(@PathVariable String id, Model model) {
        Optional<CodeSnippet> codeOptional= codeService.getCodeSnippet(id);
        if(codeOptional.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        model.addAttribute("title", "Code");
        model.addAttribute("codes", List.of(codeOptional.get()));
        return "codeContent";
    }

    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getLatestCodeSnippets(Model model) {
        model.addAttribute("title", "Latest");
        model.addAttribute("codes", codeService.getLatestCodeSnippets());
        return "codeContent";
    }
}
