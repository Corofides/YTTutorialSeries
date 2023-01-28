import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.interpolation.*

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer()

	sceneContainer.changeTo({ ImageScene() })
}

class ImageScene : Scene() {

    override suspend fun SContainer.sceneMain() {

        val bitmap = resourcesVfs["korge.png"].readBitmap();
        // x, y always seem to start from the top left of whatever you are placing. Probably a way to change.
        val image = image(bitmap).scale(.25).apply {
            // rotationDegrees = 50.0
            rotation = (50.0).degrees
            // I feel like there's a lot of changes between versions of this. I'm assuming a
            // relatively young library.
        };

        // Sanity check, can we skip the apply / scale functions?
        val image2 = image(bitmap) {
            x = 512.0 / 2;
            y = 512.0 / 2;
            scale = 0.25;
            rotation = (50.0).degrees;
            // I'm quickly working out JS is annoying for Kotlin, I'm auto treating these as
            // objects and constantly adding comma instead of semi colons.
            addUpdater {
                //Okay, yeah so add updater is how you are supposed to do stuff, I really need to
                // figure out how to change the reference point so I can centre it and rotate around
                // the centre. I assume this will be a thing.
                rotation += (1.0).degrees;

                // Can we replace this with a function call?
            }

        }
        // This works, I think what is happening here is you can define in a class an extension
        // function in Kotlin. It can then pass in various properties including a this so anything
        // after that is treated as a codeblock. The bracket / braces notation is confusing with the
        // functions but that can be treated as a problem for future me.

    }

}

class CircleScene : Scene() {
	override suspend fun SContainer.sceneMain() {

        // Create circle and add midpoint as the offcenter was annoying me.
        val midpointY = (512.0 / 2) - 20;
        val midpointX = (512.0 / 2) - 20;

        val circle = circle(20.0, Colors.GOLD).xy(midpointX, midpointY);

        /* Todo: Investigate this syntax as I'm not sure what it's doing. I know I can
                 update the properties of the object before but I'm not sure why this
                 works or how. Kotlin issues.

                 There's something referred to as an extension function. I think this is
                 what we are seeing here but I'm not certain.
         */
        // This updates the circle in the view? (scene?) directly
        circle.addUpdater {
            radius += 1.0;
        }

        // This updates the (I'm going to refer to this as the scene unless contradicted later on) scene
        // directly.
        addUpdater {
            circle.x -= 1.0;
            circle.y -= 1.0;
        }

	}
}
