package wyy.activity;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.FrameLayout.LayoutParams;

import com.example.imagemanager6.R;
import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.Logger;
import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;
import com.threed.jpct.util.MemoryHelper;

/**
 * A simple demo. This shows more how to use jPCT-AE than it shows how to write
 * a proper application for Android. It includes basic activity management to
 * handle pause and resume...
 * 
 * @author EgonOlsen
 * 
 */
public class ModelWatchActivity extends Activity {

	// Used to handle pause and resume...
	private static ModelWatchActivity master = null;

	private GLSurfaceView mGLView;
	private MyRenderer renderer = null;
	private FrameBuffer fb = null;
	private World world = null;
	private RGBColor back = new RGBColor(50, 50, 100);

	private float touchTurn = 0;
	private float touchTurnUp = 0;

	private float xpos = -1;
	private float ypos = -1;

	private Object3D[] cube = null;
	private int fps = 0;
	private boolean gl2 = true;

	private Light sun = null;

	private Camera cam;
	private String name3ds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Logger.log("onCreate");

		if (master != null) {
			copy(master);
		}

		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		name3ds = intent.getStringExtra("name");

		mGLView = new GLSurfaceView(this);
		// this.setTitle("title");
		FrameLayout frameLayout = new FrameLayout(this);
		LayoutParams parmas = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		frameLayout.setLayoutParams(parmas);
		frameLayout.addView(mGLView);
		// Button scaleSmall = new Button(this);
		ImageView scaleSmall = new ImageView(this);
		// scaleSmall.setText("缩小");
		scaleSmall.setImageResource(R.drawable.zoomdown);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lp.setMargins(20, 20, 20, 20);
		scaleSmall.setLayoutParams(lp);
		scaleSmall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cam.moveCamera(Camera.CAMERA_MOVEOUT, 10);

			}
		});
		// Button scaleLarge = new Button(this);
		ImageView scaleLarge = new ImageView(this);
		// scaleLarge.setText("放大");
		scaleLarge.setImageResource(R.drawable.zoomup);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.RIGHT;
		layoutParams.setMargins(20, 20, 20, 20);
		scaleLarge.setLayoutParams(layoutParams);
		scaleLarge.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cam.moveCamera(Camera.CAMERA_MOVEOUT, -10);

			}
		});
		// Button scaleBack = new Button(this);
		// scaleBack.setText("居中");
		ImageView scaleBack = new ImageView(this);
		scaleBack.setImageResource(R.drawable.zoom);
		LayoutParams layoutParams1 = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams1.gravity = Gravity.CENTER_HORIZONTAL;
		layoutParams1.setMargins(20, 20, 20, 20);
		scaleBack.setLayoutParams(layoutParams1);
		scaleBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cam.setPositionToCenter(cube[0]);
				cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
			}
		});

		frameLayout.addView(scaleSmall);
		frameLayout.addView(scaleLarge);
		frameLayout.addView(scaleBack);
		if (gl2) {
			mGLView.setEGLContextClientVersion(2);
		} else {
			mGLView.setEGLConfigChooser(new GLSurfaceView.EGLConfigChooser() {
				public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
					// Ensure that we get a 16bit framebuffer. Otherwise, we'll
					// fall back to Pixelflinger on some device (read: Samsung
					// I7500). Current devices usually don't need this, but it
					// doesn't hurt either.
					int[] attributes = new int[] { EGL10.EGL_DEPTH_SIZE, 16,
							EGL10.EGL_NONE };
					EGLConfig[] configs = new EGLConfig[1];
					int[] result = new int[1];
					egl.eglChooseConfig(display, attributes, configs, 1, result);
					return configs[0];
				}
			});

		}
		Log.i("wyy", "" + 1);
		renderer = new MyRenderer();
		Log.i("wyy", "" + 2);
		mGLView.setRenderer(renderer);
		// renderer.setCamCenter();
		Log.i("wyy", "" + 3);
		setContentView(frameLayout);
		Log.i("wyy", "" + 4);

	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	private void copy(Object src) {
		try {
			Logger.log("Copying data from master Activity!");
			Field[] fs = src.getClass().getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				f.set(this, f.get(src));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean onTouchEvent(MotionEvent me) {

		if (me.getAction() == MotionEvent.ACTION_DOWN) {
			xpos = me.getX();
			ypos = me.getY();
			return true;
		}

		if (me.getAction() == MotionEvent.ACTION_UP) {
			xpos = -1;
			ypos = -1;
			touchTurn = 0;
			touchTurnUp = 0;
			return true;
		}

		if (me.getAction() == MotionEvent.ACTION_MOVE) {
			float xd = me.getX() - xpos;
			float yd = me.getY() - ypos;

			xpos = me.getX();
			ypos = me.getY();

			touchTurn = xd / -100f;
			touchTurnUp = yd / -100f;
			return true;
		}

		try {
			Thread.sleep(15);
		} catch (Exception e) {
			// No need for this...
		}

		return super.onTouchEvent(me);
	}

	protected boolean isFullscreenOpaque() {
		return true;
	}

	class MyRenderer implements GLSurfaceView.Renderer {

		private long time = System.currentTimeMillis();

		public MyRenderer() {
		}

		public void onSurfaceChanged(GL10 gl, int w, int h) {
			if (fb != null) {
				fb.dispose();
			}

			if (gl2) {
				fb = new FrameBuffer(w, h); // OpenGL ES 2.0 constructor
			} else {
				fb = new FrameBuffer(gl, w, h); // OpenGL ES 1.x constructor
			}

			if (master == null) {

				world = new World();
				world.setAmbientLight(20, 20, 20);

				sun = new Light(world);
				sun.setIntensity(250, 250, 250);

				// Create a texture out of the icon...:-)
				// Texture texture = new Texture(BitmapHelper.rescale(
				// BitmapHelper.convert(getResources().getDrawable(
				// R.drawable.icon)), 64, 64));
				// TextureManager.getInstance().addTexture("texture", texture);
				//
				// cube = Primitives.getCube(10);
				// cube.calcTextureWrapSpherical();
				// cube.setTexture("texture");
				// cube.strip();
				// cube.build();

				try {
					// cube = Loader.loadOBJ(getAssets().open("test1.obj"),
					// getAssets().open("test1.mtl"), 1f);
					cube = Loader.load3DS(getAssets().open(name3ds + ".3DS"),
							1f);
					world.addObjects(cube);
					// world.addObject(cube);

					// cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
					//
					Log.i("wyy", 5 + "");
					cam = world.getCamera();
					cam.setPositionToCenter(cube[0]);
					cam.moveCamera(Camera.CAMERA_MOVEOUT, 100);
					cam.lookAt(cube[0].getTransformedCenter());

					SimpleVector sv = new SimpleVector();
					sv.set(cube[0].getTransformedCenter());
					sv.y -= 100;
					sv.z -= 100;
					sun.setPosition(sv);
					MemoryHelper.compact();

					if (master == null) {
						Logger.log("Saving master Activity!");
						master = ModelWatchActivity.this;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			Log.i("wyy", 6 + "");
		}

		public void onDrawFrame(GL10 gl) {
			if (touchTurn != 0) {
				cube[0].rotateY(touchTurn);
				touchTurn = 0;
			}

			if (touchTurnUp != 0) {
				cube[0].rotateX(touchTurnUp);
				touchTurnUp = 0;
			}

			fb.clear(back);
			world.renderScene(fb);
			world.draw(fb);
			fb.display();

			if (System.currentTimeMillis() - time >= 1000) {
				Logger.log(fps + "fps");
				fps = 0;
				time = System.currentTimeMillis();
			}
			fps++;
		}

		public void setCamCenter() {
			// if (cam != null) {
			cam.setPositionToCenter(cube[0]);
			cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
			// }
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
			master = null;
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
