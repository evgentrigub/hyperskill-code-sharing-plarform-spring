package platform.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeSnippetNewDTORequest {
    public String code;
    public Long views;
    public Long time;
}
