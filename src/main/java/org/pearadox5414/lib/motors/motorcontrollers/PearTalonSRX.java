package org.pearadox5414.lib.motors.motorcontrollers;

import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import org.pearadox5414.lib.control.pid.PIDCoefficients;
import org.pearadox5414.lib.motors.motortypes.MotorConfiguration;
import org.pearadox5414.lib.motors.motortypes.MotorControllerFactory;

public class PearTalonSRX implements SmartCANMC<WPI_TalonSRX> {
  private final WPI_TalonSRX base;
  private final MotorConfiguration configuration;
  private double arbFF;

  private PearTalonSRX(WPI_TalonSRX base, MotorConfiguration config) {
    this.base = base;
    this.configuration = config;
  }

  public static PearTalonSRX fromConfiguration(int canID, MotorConfiguration configuration) {
    return new PearTalonSRX(MotorControllerFactory.createTalonSRX(canID, configuration), configuration);
  }

  public static PearTalonSRX fromRawController(WPI_TalonSRX controller) {
    return new PearTalonSRX(controller, null);
  }

  @Override
  public MotorConfiguration getMotorConfiguration() {
    return configuration;
  }

  @Override
  public WPI_TalonSRX getBaseController() {
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
    if (master.getBaseController() instanceof IMotorControllerEnhanced) {
      base.follow((IMotorControllerEnhanced) master.getBaseController());
    } else {
      base.set(TalonSRXControlMode.Follower, master.getCANID());
    }
  }

  @Override
  public double getInputVoltage() {
    return base.getBusVoltage();
  }

  @Override
  public double getInputCurrent() {
    return base.getSupplyCurrent();
  }

  @Override
  public double getOutputVoltage() {
    return base.getMotorOutputVoltage();
  }

  @Override
  public double getOutputCurrent() {
    return base.getStatorCurrent();
  }

  @Override
  public void setNeutralMode(NeutralMode mode) {
    if (mode == NeutralMode.BRAKE) {
      base.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
    } else if (mode == NeutralMode.COAST) {
      base.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
    }
  }

  @Override
  public int getCANID() {
    return base.getDeviceID();
  }

  @Override
  public void wipeSettings() {
    base.configFactoryDefault();
  }

  @Override
  public double getSensorPosition() {
    return base.getSelectedSensorPosition();
  }

  @Override
  public double getSensorVelocity() {
    return base.getSelectedSensorVelocity();
  }

  @Override
  public void setTargetPosition(double position) {
    base.set(TalonSRXControlMode.Position, position, DemandType.ArbitraryFeedForward, arbFF);
  }

  @Override
  public void setTargetVelocity(double velocity) {
    base.set(TalonSRXControlMode.Velocity, velocity, DemandType.ArbitraryFeedForward, arbFF);
  }

  @Override
  public void configurePID(PIDCoefficients coefficients) {
    base.config_kP(1, coefficients.kP);
    base.config_kI(1, coefficients.kI);
    base.config_kD(1, coefficients.kD);
    base.config_kF(1, coefficients.kFF);
  }
}
