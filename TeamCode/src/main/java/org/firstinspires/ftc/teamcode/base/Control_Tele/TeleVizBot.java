package org.firstinspires.ftc.teamcode.base.Control_Tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.base.Robots.SkyBot;
import org.firstinspires.ftc.teamcode.base.Robots.VizBot;
import org.firstinspires.ftc.teamcode.base.Subsystems.Navigation;


//@Disabled
@TeleOp(name = "TeleOp - VizBot")

public class TeleVizBot extends OpMode {

    // Object Construction
    public ElapsedTime TeleOpTime = new ElapsedTime();
    public VizBot Bot = new VizBot();
    public Navigation Nav = new Navigation();

    final double TARGET_DISTANCE =  1;    // Hold robot's center 400 mm from target

    // Variables & Constants specific to TeleLabBot
    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;



    // Runs ONCE when driver presses INIT
    @Override
    public void init() {

        //Hardware Initialization from Robot and Camera Classes
        Nav.initCamera(hardwareMap);
        Bot.initRobot(hardwareMap);
    }


    // Runs Repeatedly when driver presses INIT but before pressing PLAY
    @Override
    public void init_loop() {

    }


    // Runs ONCE when driver presses PLAY
    @Override
    public void start() {

        Nav.activateTracking();
        Bot.gyroReset();
    }


    // RUNS Repeatedly after driver presses PLAY
    @Override
    public void loop() {

        controlHook();
        Bot.angles   = Bot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        telemetry.addData(">", "Press Left Bumper to track target");

        // auto drive or manual drive?
        // In auto drive, the robot will approach any target it can see and then press against it
        // In manual drive the robot responds to the Joystick.
        if (Nav.targetVisible && gamepad1.left_bumper) {
            // Calculate automatic target approach
            Nav.cruiseControl(TARGET_DISTANCE);

        } else {
            // Drive the robot using the joysticks
            drive();
        }

        //  Move the robot according to the pre-determined axis motions
        Bot.moveRobot();
        Nav.trackObjects();
        telemetryOutput();

    }

    // Code to run ONCE after the driver presses STOP
    @Override
    public void stop() {

        Nav.deActivateTracking();

    }



    public void drive () {

            leftStickYVal = -gamepad1.left_stick_y;
            leftStickYVal = Range.clip(leftStickYVal, -1, 1);
            leftStickXVal = gamepad1.left_stick_x;
            leftStickXVal = Range.clip(leftStickXVal, -1, 1);
            rightStickXVal = gamepad1.right_stick_x;
            rightStickXVal = Range.clip(rightStickXVal, -1, 1);

            frontLeftSpeed = leftStickYVal + leftStickXVal + rightStickXVal;
            frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);

            frontRightSpeed = leftStickYVal - leftStickXVal - rightStickXVal;
            frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);

            rearLeftSpeed = leftStickYVal - leftStickXVal + rightStickXVal;
            rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);

            rearRightSpeed = leftStickYVal + leftStickXVal - rightStickXVal;
            rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);


            if (frontLeftSpeed <= powerThreshold && frontLeftSpeed >= -powerThreshold) {
                frontLeftSpeed = 0;
                Bot.frontLeftMotor.setPower(frontLeftSpeed);
            } else {
                Bot.frontLeftMotor.setPower(frontLeftSpeed);
            }

            if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
                frontRightSpeed = 0;
                Bot.frontRightMotor.setPower(frontRightSpeed);
            } else {
                Bot.frontRightMotor.setPower(frontRightSpeed);
            }

            if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
                rearLeftSpeed = 0;
                Bot.rearLeftMotor.setPower(rearLeftSpeed);
            } else {
                Bot.rearLeftMotor.setPower(rearLeftSpeed);
            }

            if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
                rearRightSpeed = 0;
                Bot.rearRightMotor.setPower(rearRightSpeed);
            } else {
                Bot.rearRightMotor.setPower(rearRightSpeed);
            }

    }


    public void controlHook() {
        if (gamepad1.y) {
            Bot.HookRelease();
        }
        else if (gamepad1.a) {
            Bot.HookGrab();
        }

    }


    public void telemetryOutput() {
        telemetry.addData("Camera Visible Target", Nav.targetName);
        telemetry.addData("Camera Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f", Nav.robotX, Nav.robotY, Nav.robotZ);
        telemetry.addData("Camera Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", Nav.targetRoll, Nav.targetPitch, Nav.targetHeading);

        telemetry.addData("Motor ", "Front Left: " + frontLeftSpeed);
        telemetry.addData("Motor ", "Front Right: " + frontRightSpeed);
        telemetry.addData("Motor ", "Rear Left: " + rearLeftSpeed);
        telemetry.addData("Motor ", "Rear Right: " + rearRightSpeed);
        telemetry.addData("Left joystick Y (gp2): ", gamepad2.left_stick_y);
        telemetry.addData("Right joystick Y (gp2): ", gamepad2.right_stick_y);

        telemetry.addData("Color Check", Bot.checkColor(0,7));
        telemetry.addData("Color Alpha", Bot.sensorColor.alpha());
        telemetry.addData("Color Red  ", Bot.sensorColor.red());
        telemetry.addData("Color Green", Bot.sensorColor.green());
        telemetry.addData("Color Blue ", Bot.sensorColor.blue());
        telemetry.addData("Color Hue", Bot.hsvValues[0]);


        telemetry.addData("Gyro Heading", Bot.angles.firstAngle);
        telemetry.addData("Gyro Roll", Bot.angles.secondAngle);
        telemetry.addData("Gyro Pitch", Bot.angles.thirdAngle);
        telemetry.update();

    }





}