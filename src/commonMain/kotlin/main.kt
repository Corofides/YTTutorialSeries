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
        val image = image(bitmap);

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
