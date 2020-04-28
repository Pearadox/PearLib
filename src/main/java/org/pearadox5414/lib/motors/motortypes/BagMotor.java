package org.pearadox5414.lib.motors.motortypes;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.SpeedController;

public class BagMotor extends MotorConfiguration {
  BagMotor() {
    this(false, false, null, null);
  }

  BagMotor(boolean coast, boolean inverted, SpeedController master, FeedbackSensor feedbackDevice) {
    brushed = false;
    stallLimit = 30;
    freeLimit = 45;
    stallThreshold = 30;
    peakCurrentTime = 1000;
    this.coast = coast;
    this.inverted = inverted;
    this.master = master;
    this.feedbackDevice = feedbackDevice;
  }

  @Override
  public MotorConfiguration setIdleMode(boolean coast) {
    return new BagMotor(coast, inverted, master, feedbackDevice);
  }

  @Override
  public MotorConfiguration setInverted(boolean inverted) {
    return new BagMotor(coast, inverted, master, feedbackDevice);
  }

  @Override
  public MotorConfiguration withMaster(SpeedController master) {
    return new BagMotor(coast, inverted, master, feedbackDevice);
  }

  @Override
  public MotorConfiguration withFeedbackDevice(FeedbackSensor feedbackDevice) {
    if (feedbackDevice.device == FeedbackDevice.IntegratedSensor) {
      throw new IllegalArgumentException("Bag motor has no integrated encoder");
    }
    return new BagMotor(coast, inverted, master, feedbackDevice);
  }
}