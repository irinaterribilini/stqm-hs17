package ch.fhnw.swc.mrs.view;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

/**
 * Created by François Martin on 06.12.17.
 */
public class RentMovieTabTest extends ApplicationTest {
  @Override public void start(Stage stage) {
    Parent sceneRoot = new ClickApplication.ClickPane();
    Scene scene = new Scene(sceneRoot, 100, 100);
    stage.setScene(scene);
    stage.show();
  }

  @Before
  public void setup() throws Exception {
    FxToolkit.registerPrimaryStage();
    FxToolkit.setupApplication(ClickApplication.class);
  }

  @Test
  public void should_contain_button() {
    // expect:
    verifyThat(".button", hasText("click me!"));
  }

  @Test
  public void should_click_on_button() {
    // when:
    clickOn(".button");


    // then:
    verifyThat(".button", hasText("clicked!"));
  }
}
