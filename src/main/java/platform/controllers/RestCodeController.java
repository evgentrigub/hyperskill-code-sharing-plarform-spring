package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.CodeService;
import platform.models.CodeSnippet;
import platform.models.CodeSnippetNewDTORequest;
import platform.models.CodeSnippetNewDTOResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestCodeController {

    @Autowired
    CodeService codeService;

    @PostMapping("/code/new")
    public ResponseEntity<CodeSnippetNewDTOResponse> postCode(@RequestBody CodeSnippetNewDTORequest dto) {
        CodeSnippet responseCode = this.codeService.save(dto);
        return ResponseEntity.ok(new CodeSnippetNewDTOResponse(responseCode.getId()));
    }

    @GetMapping(value = "/code/{id}")
    public ResponseEntity<CodeSnippet> getCodeSnippet(@PathVariable String id) {
        Optional<CodeSnippet> response = codeService.getCodeSnippet(id);
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity is not found");
        }
        return ResponseEntity.ok(response.get());
    }

    @GetMapping(value = "/code/latest")
    public ResponseEntity<List<CodeSnippet>> getLatestCodeSnippets() {
        List<CodeSnippet> list = codeService.getLatestCodeSnippets();
        return ResponseEntity.ok(list);
    }
}
