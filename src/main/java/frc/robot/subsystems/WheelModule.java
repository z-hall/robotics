/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class WheelModule extends Subsystem {
    private TalonSRX angleMotor;
    private TalonSRX speedMotor;

    private String id;
    private double lastAngle = 0;

    /**
     * Constructor that initializes the two motors and configures them with the desired information
     * 
     * @param anglePort port for the angle motor
     * @param speedPort port for the speed motor
     * @param id        of the module (to identify which one is which through the code)
     * @param inverted  should the angle motor be inverted
     */
    public WheelModule(int anglePort, int speedPort, String id, boolean angleInverted,
            boolean speedInverted) {
        this.id = id;

        angleMotor = new TalonSRX(anglePort);
        speedMotor = new TalonSRX(speedPort);

        speedMotor.configPeakOutputForward(1);
        speedMotor.configPeakOutputReverse(-1);
        speedMotor.setInverted(speedInverted);

        angleMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,
                RobotMap.TIMEOUT);

        angleMotor.setSensorPhase(false);
        angleMotor.configAllowableClosedloopError(0, 0, RobotMap.TIMEOUT);
        angleMotor.config_kP(0, RobotMap.angleP, RobotMap.TIMEOUT);
        angleMotor.config_kI(0, RobotMap.angleI, RobotMap.TIMEOUT);
        angleMotor.config_kD(0, RobotMap.angleD, RobotMap.TIMEOUT);
        angleMotor.config_kF(0, RobotMap.angleF, RobotMap.TIMEOUT);
        angleMotor.config_IntegralZone(0, 50, RobotMap.TIMEOUT);
        angleMotor.configMotionCruiseVelocity(RobotMap.angleV, RobotMap.TIMEOUT);
        angleMotor.configMotionAcceleration(RobotMap.angleA, RobotMap.TIMEOUT);
        angleMotor.setInverted(angleInverted);
    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new MySpecialCommand());
    }

    /*
     * ===================== motor methods =====================
     */

    /**
     * Adjusts the angle to fix wheel wrapping
     * 
     * @param angle for the motor to be set to
     * @return adjusted angle to account for wrapping
     */
    public double adjustAngle(double angle) {
        double target = 0;
        double current = Math.round(angleMotor.getSelectedSensorPosition(0) / RobotMap.COUNTPERDEG);
        double Rcurrent = 0;
        double dir = Math.abs(current) % 360;
        if (current >= 180) { // OLD: > and <
            if (dir > 180) {
                Rcurrent = dir - 360;
            } else {
                Rcurrent = dir;
            }
        } else if (current <= -180) {
            if (dir > 180) {
                Rcurrent = -1 * dir + 360;
            } else {
                Rcurrent = dir * -1;
            }
        } else {
            Rcurrent = current;
        }

        if (angle - Rcurrent <= 180 && angle - Rcurrent >= -180) {
            target = angle - Rcurrent + current;
        } else if (angle - Rcurrent >= 180) { // OLD: >
            target = angle - Rcurrent - 360 + current;
        } else {
            target = angle - Rcurrent + 360 + current;
        }

        return target;
    }

    /**
     * Called through the calculateVector method in the SwerveDrive class. Signals the two motors to
     * start moving
     * 
     * @param speed of the module
     * @param angle of the module
     */
    public void drive(double speed, double angle) {
        angle = adjustAngle(angle);

        if (angle > lastAngle && angle - lastAngle > 90) { // wheel optimizations. Takes the
                                                           // shortest route
            angle = angle - 180;
            speed = -speed;
        } else if (angle < lastAngle && lastAngle - angle > 90) {
            angle = angle + 180;
            speed = -speed;
        }
        lastAngle = angle;

        angle *= RobotMap.COUNTPERDEG;

        speedMotor.set(ControlMode.PercentOutput, speed);
        angleMotor.set(ControlMode.MotionMagic, angle);
    }

    public void setAngle(double angle) {
        angle = adjustAngle(angle);
        angle *= RobotMap.COUNTPERDEG;

        angleMotor.set(ControlMode.MotionMagic, angle);
    }

    public void setDrivePosition(double position) {
        speedMotor.setSelectedSensorPosition(0);
        speedMotor.set(ControlMode.MotionMagic, position);
    }


    /*
     * ===================== helper methods =====================
     */

    public int getAngleError() {
        return angleMotor.getClosedLoopError(0);
    }

    public int getAnglePosition() {
        return angleMotor.getSelectedSensorPosition(0);
    }

    public int getDrivePosition() {
        return speedMotor.getSelectedSensorPosition(0);
    }

    public int getDriveError() {
        return speedMotor.getClosedLoopError(0);
    }

    public double getVoltage() {
        return speedMotor.getMotorOutputVoltage();
    }
}
