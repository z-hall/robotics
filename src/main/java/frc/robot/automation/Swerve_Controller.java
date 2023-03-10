/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.automation;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.pid.Controllers;

public class Swerve_Controller extends Command {

    public Swerve_Controller() {
        requires(Robot.swerve);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Controllers.xController.setP(Robot.xTuner.getP()); // once the PID values are known, replace
                                                           // Robot.{}Tuner.get{}() with a value
                                                           // stored in RobotMap
        Controllers.xController.setI(Robot.xTuner.getI());
        Controllers.xController.setD(Robot.xTuner.getD());

        Controllers.zController.setP(Robot.zTuner.getP());
        Controllers.zController.setI(Robot.zTuner.getI());
        Controllers.zController.setD(Robot.zTuner.getD());

        Controllers.yawController.setP(Robot.yawTuner.getP());
        Controllers.yawController.setI(Robot.yawTuner.getI());
        Controllers.yawController.setD(Robot.yawTuner.getD());

        setTimeout(1000);
        Controllers.xController.setSetpoint(0);
        Controllers.zController.setSetpoint(-20); // BUMPERS!!!
        Controllers.yawController.setSetpoint(Math.signum(Robot.swerve.getYaw()) * 90);

        Controllers.xController.enable();
        Controllers.zController.enable();
        Controllers.yawController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.swerve.calculateVisionVectors(RobotMap.pidZTarget);
        SmartDashboard.putNumber("xErr", Controllers.xController.getError());
        SmartDashboard.putNumber("zErr", Controllers.zController.getError());
        SmartDashboard.putNumber("yawErr", Controllers.yawController.getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        boolean conditionA = Controllers.xController.onTarget()
                && Controllers.yawController.onTarget() && Controllers.yawController.onTarget();
        boolean eStop = Math.abs(Robot.oi.getZ()) > 0.2; // remove this condition when not testing

        if (conditionA || eStop) {
            Controllers.xController.disable();
            Controllers.zController.disable();
            Controllers.yawController.disable();
            return true;
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
