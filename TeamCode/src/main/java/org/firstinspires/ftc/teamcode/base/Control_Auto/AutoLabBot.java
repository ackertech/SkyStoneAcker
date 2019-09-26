package org.firstinspires.ftc.teamcode.base.Control_Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.base.Robots.LabBot;


@Autonomous(name = "Auto - LabBot Mecanum")
//@Disabled
public class AutoLabBot extends LinearOpMode {

    // Object Construction
   public ElapsedTime runtime = new ElapsedTime();
   public LabBot Bot = new LabBot();

   // Variables & Constants Specific to Autonomous
   public final double SPD_DRIVE_LOW = 0.38;
   public final double SPD_DRIVE_MED = 0.50;
   public final double SPD_DRIVE_HIGH = 0.75;
   public final double SPD_DRIVE_MAX = 1.0;
   public final long sleepTime = 100;


    @Override
    public void runOpMode() throws InterruptedException {

        //Hardware Initialization from Robot Class
        Bot.initRobot(hardwareMap);

        waitForStart();

        idle();

        while (opModeIsActive()) {


            idle();

            Bot.HoodOpen();
            telemetry.addData("Status", "Hood Opened");
            telemetry.update();
            sleep(2000);

            Bot.HoodClose();
            telemetry.addData("Status", "Hood Closed");
            telemetry.update();
            sleep(2000);

            Bot.driveForward(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Drive Forward with Encoders");
            telemetry.update();

            Bot.HoodSmile();
            telemetry.addData("Status", "Hood Smiling");
            telemetry.update();
            sleep(2000);

            Bot.driveBackward(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Drive Backward with Encoders");
            telemetry.update();

            Bot.stopMotors();
            sleep(1000);


            Bot.stopMotors();
            sleep(1000);

            Bot.stopMotors();
            sleep(1000);

            Bot.stopMotors();
            sleep(1000);

            Bot.strafeLeft(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Strafe Left with Encoders");
            telemetry.update();

            Bot.strafeRight(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Strafe Right with Encoders");
            telemetry.update();

            Bot.stopMotors();
            telemetry.addData("Status", "Stop All Motors");
            telemetry.update();

            idle();

            requestOpModeStop();
        }

        idle();
    }
}