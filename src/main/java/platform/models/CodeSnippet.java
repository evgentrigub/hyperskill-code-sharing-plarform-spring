package platform.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Getter @Setter @ToString
public class CodeSnippet {
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    private String code;
    private String date;

    private Long views;
    private Long time;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime limitTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime updateAt;

    @JsonIgnore
    @Transient
    private boolean viewLimit = false;

    public CodeSnippet() {
        this.date = getFormattedDateTime(LocalDateTime.now());
    }

    public Long getTime() {
        if (limitTime == null) {
            return 0L;
        }
        return ChronoUnit.SECONDS.between(LocalDateTime.now(),limitTime);
    }

    public void setLimitTime() {
        if (time > 0L) {
            limitTime = updateAt.plusSeconds(time);
        }
    }

    private String getFormattedDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CodeSnippet code = (CodeSnippet) o;
        return Objects.equals(id, code.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
