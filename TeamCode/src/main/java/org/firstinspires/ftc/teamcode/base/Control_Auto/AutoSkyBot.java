package org.firstinspires.ftc.teamcode.base.Control_Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.base.Robots.SkyBot;
import org.firstinspires.ftc.teamcode.base.Subsystems.Camera;


@Autonomous(name = "Auto - TestBot")
//@Disabled
public class AutoSkyBot extends LinearOpMode {

    // Object Construction
   public ElapsedTime runtime = new ElapsedTime();
   public SkyBot Bot = new SkyBot();
   public Camera Cam = new Camera();

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
        Cam.initCamera(hardwareMap);
        Bot.gyroReset();
        Cam.activateTracking();

        waitForStart();

        idle();

        while (opModeIsActive()) {


            idle();

            Bot.driveForward(SPD_DRIVE_MAX);
            sleep(1000);
            telemetry.addData("Status", "Drive Forward with Power");
            telemetry.update();

            Bot.driveBackward(SPD_DRIVE_MAX);
            sleep(1000);
            telemetry.addData("Status", "Drive Backward with Power");
            telemetry.update();

            Bot.rotateRight(SPD_DRIVE_LOW);
            sleep(5000);
            telemetry.addData("Status", "Rotate Right Power");
            telemetry.update();

            Bot.rotateLeft(SPD_DRIVE_LOW);
            sleep(5000);
            telemetry.addData("Status", "Rotate Left with Power");
            telemetry.update();

            Bot.stopMotors();
            sleep(5000);
            telemetry.addData("Status", "Stop Motors");
            telemetry.update();

            idle();

            requestOpModeStop();
        }

        idle();

        Cam.deActivateTracking();
    }
}