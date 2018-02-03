package ch.fhnw.swc.mrs.view;

import ch.fhnw.swc.mrs.MovieRentalSystem;
import ch.fhnw.swc.mrs.data.DbMRSServices;
import ch.fhnw.swc.mrs.model.MRSServices;
import ch.fhnw.swc.mrs.model.PriceCategory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

/**
 * Created by François Martin on 06.12.17.
 */
public class RentMovieTabTest extends ApplicationTest {

  private MRSServices backend = new DbMRSServices();

  @Override public void start(Stage stage) throws IOException {
    PriceCategory.init();
    backend.init();
    stage.setTitle("Software Construction Lab");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(MovieRentalSystem.class.getResource("view/MRS.fxml"));
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    stage.show();
    MRSController controller = loader.getController();
    controller.initTabs(backend);
  }

  @Before
  public void setup() throws Exception {
    FxToolkit.registerPrimaryStage();
    FxToolkit.setupApplication(MovieRentalSystem.class);
  }

  @Test
  public void verifyStartupState() {
    // expect:
    verifyThat(lookup("User ID"), hasText("User ID"));
    verifyThat(lookup("Movie 1"), hasText("Movie 1"));
  }

  @Test
  public void clickMovie() {
    // when:
    clickOn(hasText("Movie 1"));

    // then:
    verifyThat(lookup("Movie 1"), Node::isFocused);
  }
}
