package org.pearadox5414.lib.motors.motortypes;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.SpeedController;

public class Neo550Motor extends MotorConfiguration {
  Neo550Motor() { this(false, false, null, new FeedbackSensor(FeedbackDevice.IntegratedSensor, 42)); }

  Neo550Motor(boolean coast, boolean inverted, SpeedController master, FeedbackSensor feedbackDevice) {
    brushed = false;
    stallLimit = 20;
    freeLimit = 35;
    stallThreshold = 30;
    peakCurrentTime = 1000;
    this.coast = coast;
    this.inverted = inverted;
    this.master = master;
    this.feedbackDevice = feedbackDevice;
  }

  @Override
  public MotorConfiguration setIdleMode(boolean coast) {
    return new Neo550Motor(coast, inverted, master, feedbackDevice);
  }

  @Override
  public MotorConfiguration setInverted(boolean inverted) {
    return new Neo550Motor(coast, inverted, master, feedbackDevice);
  }

  @Override
  public MotorConfiguration withMaster(SpeedController master) {
    return new Neo550Motor(coast, inverted, master, feedbackDevice);
  }

  @Override
  public MotorConfiguration withFeedbackDevice(FeedbackSensor feedbackDevice) {
    return new Neo550Motor(coast, inverted, master, feedbackDevice);
  }
}