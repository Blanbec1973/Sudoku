package resolution;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResolutionMethod {
    // Optionnel : on peut ajouter des attributs si besoin
    ZoneType[] zones() default {};
}

