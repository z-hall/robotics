/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.pid;

import edu.wpi.first.wpilibj.PIDController;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class Controllers {

    private static PnpXOutput xOutput;
    private static PnpXSource xSource;

    private static PnpZOutput zOutput;
    private static PnpZSource zSource;

    private static PnpYawOutput yawOutput;
    private static PnpYawSource yawSource;

    public static PIDController xController, hypController, yawController, zController;

    public static void initialize() {
        xSource = new PnpXSource();
        xOutput = new PnpXOutput();
        xController = new PIDController(Robot.xTuner.getP(), Robot.xTuner.getI(),
                Robot.xTuner.getD(), xSource, xOutput);
        xController.setInputRange(-30, 30);
        xController.setOutputRange(-0.35, 0.35);
        xController.setPercentTolerance(2);

        zSource = new PnpZSource();
        zOutput = new PnpZOutput();
        zController = new PIDController(Robot.zTuner.getP(), Robot.zTuner.getI(),
                Robot.zTuner.getD(), zSource, zOutput);
        zController.setInputRange(-80, 0);
        zController.setOutputRange(-0.35, 0.35);
        zController.setPercentTolerance(2);

        yawSource = new PnpYawSource();
        yawOutput = new PnpYawOutput();
        yawController = new PIDController(0, 0, 0, yawSource, yawOutput);
        yawController.setInputRange(-120, 120);
        yawController.setOutputRange(-0.25, 0.25);
        yawController.setPercentTolerance(1);
    }
}
