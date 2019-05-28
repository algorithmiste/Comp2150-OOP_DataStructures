/** This interface will be implemented by any classes representing game objects that you want to draw on the screen. 
 * This makes it so the game engine can treat every object you want to show on the screen as type Drawable, 
 * and it can simply call draw() on each of those objects to render it.
 * @author casey
 *
 */
public interface Drawable {
	void draw();
}
