package org.pearadox5414.lib.motors.motorcontrollers;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.SpeedController;
import org.pearadox5414.lib.control.pid.PIDCoefficients;
import org.pearadox5414.lib.motors.motortypes.MotorConfiguration;
import org.pearadox5414.lib.motors.motortypes.MotorControllerFactory;

public class PearSparkMax implements SmartCANMC<CANSparkMax> {
  private final CANSparkMax base;
  private final MotorConfiguration configuration;
  private CANEncoder encoder;
  private CANPIDController pidController;
  private double arbFF;


  private PearSparkMax(CANSparkMax base, MotorConfiguration config) {
    this.base = base;
    if (config != null) {
      this.configuration = config;
      if (config.feedbackDevice.device == FeedbackDevice.QuadEncoder && config.isBrushed()) {
        this.encoder = base.getEncoder(EncoderType.kQuadrature, config.feedbackDevice.CPR);
      } else if (config.feedbackDevice.device == FeedbackDevice.QuadEncoder) {
        this.encoder = base.getAlternateEncoder(AlternateEncoderType.kQuadrature, config.feedbackDevice.CPR);
      } else if (!config.isBrushed()) {
        this.encoder = base.getEncoder();
      }
    } else {
      this.configuration = null;
    }
  }

  public static PearSparkMax fromConfiguration(int canID, MotorConfiguration configuration) {
    return new PearSparkMax(MotorControllerFactory.createSparkMax(canID, configuration), configuration);
  }

  public static PearSparkMax fromRawController(CANSparkMax controller) {
    return new PearSparkMax(controller, null);
  }

  @Override
  public MotorConfiguration getMotorConfiguration() {
    return configuration;
  }

  @Override
  public CANSparkMax getBaseController() {
    return base;
  }

  @Override
  public void set(double percent) {
    base.set(percent);
  }

  @Override
  public void stop() {
    base.set(0);
  }

  @Override
  public void setVoltage(double voltage) {
    base.setVoltage(voltage);
  }

  @Override
  public void follow(SmartCANMC<SpeedController> master) {
    if (master.getBaseController() instanceof CANSparkMax) {
      base.follow((CANSparkMax) master.getBaseController());
    } else if (master.getBaseController() instanceof IMotorControllerEnhanced) {
      base.follow(CANSparkMax.ExternalFollower.kFollowerPhoenix, master.getCANID());
    } else {
      base.follow(CANSparkMax.ExternalFollower.kFollowerDisabled, master.getCANID());
    }
  }

  @Override
  public double getInputVoltage() {
    return base.getBusVoltage();
  }

  @Override
  public double getInputCurrent() {
    return base.getOutputCurrent();
  }

  @Override
  public double getOutputVoltage() {
    return base.getAppliedOutput();
  }

  @Override
  public double getOutputCurrent() {
    return base.getOutputCurrent();
  }

  @Override
  public void setNeutralMode(NeutralMode mode) {
    if (mode == NeutralMode.BRAKE) { base.setIdleMode(CANSparkMax.IdleMode.kBrake); }
    else if (mode == NeutralMode.COAST) { base.setIdleMode(CANSparkMax.IdleMode.kCoast); }
  }

  @Override
  public int getCANID() {
    return base.getDeviceId();
  }

  @Override
  public void wipeSettings() {
    base.restoreFactoryDefaults();
  }

  @Override
  public double getSensorPosition() {
    if (encoder != null) {
      return encoder.getPosition();
    } else {
      return Double.NaN;
    }
  }

  @Override
  public double getSensorVelocity() {
    if (encoder != null) {
      return encoder.getPosition();
    } else {
      return Double.NaN;
    }
  }

  @Override
  public void setTargetPosition(double position) {
    pidController.setReference(position, ControlType.kPosition, 0, arbFF);
  }

  @Override
  public void setTargetVelocity(double velocity) {
    pidController.setReference(velocity, ControlType.kVelocity, 0, arbFF);
  }


  @Override
  public void configurePID(PIDCoefficients coefficients) {
    pidController.setP(coefficients.kP);
    pidController.setI(coefficients.kI);
    pidController.setD(coefficients.kD);
    pidController.setFF(coefficients.kFF);
    arbFF = coefficients.kArbFF;
  }
}
