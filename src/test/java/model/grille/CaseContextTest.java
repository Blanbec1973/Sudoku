package model.grille;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CaseContextTest {

    @Test
    void testConstructionFromNumCase() {
        CaseContext context = new CaseContext(41); // Case (4,4)

        assertEquals(41, context.getNumCase());
        assertEquals(4, context.getX());
        assertEquals(4, context.getY());
        assertEquals(5, context.getNumRegion()); // selon calculNumeroRegion
        assertEquals(3, context.getxRegion());
        assertEquals(3, context.getyRegion());
    }

    @Test
    void testConstructionFromCoordinates() {
        CaseContext context = new CaseContext(2, 3); // numCase = 30

        assertEquals(30, context.getNumCase());
        assertEquals(2, context.getX());
        assertEquals(3, context.getY());
        assertEquals(4, context.getNumRegion());
        assertEquals(0, context.getxRegion());
        assertEquals(3, context.getyRegion());
    }

    @Test
    void testEditionCoordinates() {
        CaseContext context = new CaseContext(0, 0);
        assertEquals("1", context.getXEdition());
        assertEquals("1", context.getYEdition());
    }
}