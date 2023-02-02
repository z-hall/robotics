/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Swerve_GyroReset;
import frc.robot.commands.Swerve_ToggleFieldCentric;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands
 * and command groups that allow control of the robot.
 */
public class OI {

    Joystick driveStick;
    Joystick controlStick;

    Button driveResetGyro, toggleFieldCentric;

    public OI() {
        driveStick = new Joystick(0);
        controlStick = new Joystick(1);

        /*
         * =============================== Driving Buttons ===============================
         */
        driveResetGyro = new JoystickButton(driveStick, 5);
        driveResetGyro.whenPressed(new Swerve_GyroReset());

        toggleFieldCentric = new JoystickButton(driveStick, 12);
        toggleFieldCentric.whenPressed(new Swerve_ToggleFieldCentric());
        toggleFieldCentric.whenReleased(new Swerve_ToggleFieldCentric());
    }

    public double getX() {
        return Math.abs(driveStick.getX()) > RobotMap.DEADZONE_XY ? driveStick.getX() : 0;
    }

    public double getY() {
        return Math.abs(driveStick.getY()) > RobotMap.DEADZONE_XY ? driveStick.getY() : 0;
    }

    public double getZ() {
        double z = driveStick.getZ();
        return Math.abs(z) > RobotMap.DEADZONE_Z
                ? (z * Math.pow(z, RobotMap.SENSITIVITY_Z)) / RobotMap.REDUCER_Z
                : 0;
    }

    public double getSlider() {
        return driveStick.getThrottle();
    }
}
