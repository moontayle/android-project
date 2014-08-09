package john.westendarp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{
	
	MyBringBackSurface ourSurfaceView;
	float x, y, sX, sY, fX, fY, dX, dY, animateX, animateY, scaledX, scaledY;
	Bitmap test, plus;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x = y = sX = sY = fX = fY = dX = dY = animateX = animateY = scaledX = scaledY = 0;
		test = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
		plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
		setContentView(ourSurfaceView);
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
		
	}



	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			dX = dY = animateX = animateY = scaledX = scaledY = fX = fY = 0;
			break;
			
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			dX = fX - sX;
			dY = fY - sY;
			scaledX = dX/30;
			scaledY = dY/30;
			x = y = 0;
			break;
		}
		
		return true;
	}

	public class MyBringBackSurface extends SurfaceView implements Runnable {
		
		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;

		public MyBringBackSurface(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			ourHolder = getHolder();
		}
		
		public void pause(){
			isRunning = false;
			while(true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}
		
		public void resume(){
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}
		
		@Override
		public void run() {
			while (isRunning) {
				if(!ourHolder.getSurface().isValid())
					continue;
				
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(2,2,150);
				if (x != 0 && y != 0) {
					canvas.drawBitmap(test, x - (test.getWidth()/2), y -(test.getWidth()/2), null);
				}
				if (sX != 0 && sY != 0) {
					canvas.drawBitmap(plus, sX - (plus.getWidth()/2), sY -(plus.getWidth()/2), null);
				}
				if (fX != 0 && fY != 0) {
					canvas.drawBitmap(test, fX - (test.getWidth()/2) - animateX, fY -(test.getWidth()/2) - animateY, null);
					canvas.drawBitmap(plus, fX-(plus.getWidth()/2), fY-(plus.getWidth()/2), null);
				}
				animateX = animateX + scaledX;
				animateY = animateY + scaledY;
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}
		
	}

	

}


