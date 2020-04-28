package org.pearadox5414.lib.control.pid;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PIDControllerTest {

  @Test
  public void testError() {


    var controller = new PearPIDController();

    controller.update(5, 3, 0.02);
    assertEquals(-2, controller.getError(), 1E-9);
  }

  @Test
  public void testOutput() {
    var controller = new PearPIDController(1, 1, 1, 2, 1);

    controller.update(3, 4, 0.02);
    controller.update(3.1, 4, 0.02);
    assertEquals(4.92, controller.calculate(), 1E-9);
  }
}
