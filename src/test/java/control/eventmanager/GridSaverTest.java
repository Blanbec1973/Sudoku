package control.eventmanager;

import model.grille.Grille;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

class GridSaverTest {

    @Test
	void testSaveGrille() throws IOException {
        Grille grille =new Grille();
        String inputFileName = "src/test/resources/grillesTest/init67-40.sud";
        grille.init(Paths.get(inputFileName).toAbsolutePath());
        String outputFileName = System.getProperty("user.dir")+"/testSaveSudoku.sud";

        GridSaverService saverService = new GridSaverService();
        saverService.saveGrid(grille, outputFileName);
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		Assertions.assertTrue(FileUtils.contentEquals(inputFile, outputFile), "The files differ!");
		if (outputFile.exists() && !outputFile.delete()) {
			System.err.println("Failed to delete the file: " + outputFile.getAbsolutePath());
		}
	}

}
