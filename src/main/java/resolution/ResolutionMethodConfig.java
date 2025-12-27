package resolution;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ResolutionMethodConfig {
    @JsonProperty("class")
    private String className;
    private ZoneType zone; // Enum ou String, selon ton code
    private int order;

    public ResolutionMethodConfig() {}

    public String getClassName() { return className; }

    public ZoneType getZone() { return zone; }

    public int getOrder() { return order; }
}


