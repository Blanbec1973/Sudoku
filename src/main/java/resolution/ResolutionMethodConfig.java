package resolution;



import com.fasterxml.jackson.annotation.JsonProperty;

public record ResolutionMethodConfig(
        @JsonProperty("class") String className,
        ZoneType zone,
        int order
) {}



