package frc.robot.utility;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.RobotMap;
import frc.robot.utility.autonomous.RobotMath;

public class Vision {

    private NetworkTableInstance nt;
    private NetworkTable limelight;
    private NetworkTableEntry camtran;
    private NetworkTableEntry tx;

    public enum Data { // maps array index to named field
        X(0), Y(1), Z(2), PITCH(3), YAW(4), ROLL(5);

        private final int value;

        Data(int value) {
            this.value = value;
        }

        public int indexOf() {
            return value;
        }
    }

    public Vision() {
        nt = NetworkTableInstance.getDefault();
        limelight = nt.getTable("limelight");

        camtran = limelight.getEntry("camtran");
        tx = limelight.getEntry("tx");
    }

    public double getTX() {
        return tx.getDouble(0);
    }

    public double convertYaw(double yaw) {
        double z = getTranslationalZ();
        return Math.atan((z * Math.tan(RobotMath.deg2rad(yaw)) - RobotMap.camDist) / z);
    }

    public double[] getTranslationalData() {
        return camtran.getDoubleArray(new double[6]);
    }

    public double getTranslationalX() {
        return getTranslationalData()[0];
    }

    public double getTranslationalY() {
        return getTranslationalData()[1];
    }

    public double getTranslationalZ() {
        return getTranslationalData()[2];
    }

    public double getTranslationalPitch() {
        return getTranslationalData()[3];
    }

    public double getTranslationalYaw() {
        return getTranslationalData()[4];
    }

    public double getTranslationalRoll() {
        return getTranslationalData()[5];
    }

    public double getHypotenuse() {
        double x = getTranslationalX();
        double z = getTranslationalZ();

        return Math.sqrt(x * x + z * z);
    }
}
