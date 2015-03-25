import java.util.ArrayList;
import com.leapmotion.leap.*;

public class LeapListener extends Listener {

	private CreateAndShowGui showGUI = null;
	
	private EstablishConnection establishCon = null;
	
	private boolean systemStatsFlag = true;
	
	private boolean leapDataFlag = true;
	
	private float x;
	private float y;
	private float z;
	
	private String handType;
	
	private float pinch;
	
	private String data;
	
	private Vector normal;
	
	private Vector direction;
	
	private Frame frame;
	
	protected ArrayList<String> recordedData = new ArrayList<>();
	
	public LeapListener(CreateAndShowGui showGUI, EstablishConnection establishCon){
		
		this.showGUI = showGUI;
		this.establishCon = establishCon;
		
	}
	
	public void onInit(Controller controller){
		
		if(isSystemStatsFlag() == true){
			showGUI.printStatus("Initialised");
			//System.out.println("Initialised");	
		}
	}
	
	public void onConnect(Controller controller){
		if(isSystemStatsFlag() == true){
			showGUI.printStatus("Connected to Motion Sensor");
			//System.out.println("Connected to Motion Sensor");
		}
		
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);

	}
	
	public void onDisconnect(Controller controller){
		
		if(isSystemStatsFlag() == true){
			showGUI.printStatus("Motion Sensor Disconnected");
			//System.out.println("Motion Sensor Disconnected");
		}

		
	}
	
	public void OnExit(Controller controller){
	
		if(isSystemStatsFlag() == true){
			showGUI.printStatus("Exited");
			//System.out.println("Exited");
		}
	}
	
	public void onFrame(Controller controller){
		
		/*Frame Data */
		frame = controller.frame();
		
		/* Hand Data */
		for(Hand hand: frame.hands()){
			
			handType = hand.isLeft() ? "Left Hand" : "Right Hand";
			
			pinch = hand.pinchStrength();
			pinch = pinch*100;
			
			//fix this showGUI.printStringData(data);
						
			normal = hand.palmPosition();
			direction = hand.direction();
			
			x = normal.getX();
			y = normal.getY();
			z = normal.getZ();
			
			data = (int)x + ":" + (int)y + ":" + (int)z + ":" + (int)pinch;
			
			if(isLeapDataFlag() == true){
				
				showGUI.printFrameID("" + frame.id());
				showGUI.printHands("" + frame.hands().count());
				showGUI.printFingers("" + frame.fingers().count());
				showGUI.printTools("" + frame.tools().count());
				showGUI.printGestures("" + frame.gestures().count());
				
				showGUI.printLeftRight(handType);
				showGUI.printPalmPosition("" + hand.palmPosition());
				
				showGUI.printX("" + normal.getX());
				showGUI.printY("" + normal.getY());
				showGUI.printZ("" + normal.getZ());
				
				showGUI.printYaw("" + Math.toDegrees(direction.yaw()));
				showGUI.printRoll("" + Math.toDegrees(normal.roll()));
			}
			
			if(establishCon.isInService() == true && establishCon.isInRecordService() == false) {
				establishCon.sendTheData(data);
			}
			if(establishCon.isInService() == false && establishCon.isInRecordService() == true) {
				System.out.println("In here");
				recordedData.add(data);
			}
		}

		/* Finger Data */
//		for(Finger finger: frame.fingers() ){
//			
//			System.out.println("Finger Type: " +finger.type()
//										+ " Finger ID: " + finger.id()
//										+ " Finger Length" + finger.length()
//										+ " Finger Width" + finger.width());
//		
//			/* Bone Data */
//			for (Bone.Type boneType: Bone.Type.values()){
//				
//				Bone bone = finger.bone(boneType);
//				System.out.println("Bone Type: " + bone.type()
//									+ " Start: " + bone.prevJoint()
//									+ " End: " + bone.nextJoint()
//									+ " Direction: " + bone.direction() );
//				
//			}
//			
//		}
		
		
		/* Tool Data (Doesn't seem to Detect the tools) */
//		for(Tool tool: frame.tools()){
//			
//			System.out.println("Tool Id: " + tool.id()
//								+ " Tip Position: " + tool.tipPosition()
//								+ " Direction: " + tool.direction()
//								+ " Width: " + tool.width()
//								+ " Touch Distance: " + tool.touchDistance());
//			
//		}
		
		/* Gesture Data */
//		GestureList gestures = frame.gestures();
//		for(int i = 0; i < gestures.count(); i++){
//			
//			Gesture gesture = gestures.get(i);
//			
//			switch(gesture.type()){
//			
//				case TYPE_CIRCLE:
//					CircleGesture circle = new CircleGesture(gesture);
//					
//					String clockwise;
//					
//					if(circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/4){
//						clockwise = "Clockwise";
//					}else{
//						clockwise = "Anti-clockwise";
//					}
//					
//					double sweptAngle = 0;
//					if(circle.state() != State.STATE_START){
//						
//						CircleGesture previous = new CircleGesture(controller.frame(1).gesture(circle.id()));
//						sweptAngle = (circle.progress() - previous.progress()) * 2 * Math.PI;
//						
//					}
//					
//					System.out.println("Circle ID: " + circle.id()
//											+ " Circle State: " + circle.state()
//											+ " Progress: " + circle.progress()
//											+ " Radius : " + circle.radius()
//											+ " SweptAngle: " + Math.toDegrees(sweptAngle)
//											+ " " + clockwise);
//					
//					break;
//					
//				case TYPE_SWIPE:
//					SwipeGesture swipe = new SwipeGesture(gesture);
//					System.out.println("Swipe ID: " + swipe.id()
//										 + " State: " + swipe.state()
//										 + " Swipe Position: " + swipe.position()
//										 + " Direction: " + swipe.direction()
//										 + " Speed: " + swipe.speed());
//						
//					break;
//					
//				case TYPE_SCREEN_TAP:
//					ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
//					System.out.println("ScreenTap ID: " + screenTap.id()
//										+ " State: " + screenTap.state()
//										+ " Position: " + screenTap.position()
//										+ " Direction: " + screenTap.direction());
//					
//					break;
//					
//				case TYPE_KEY_TAP:
//					KeyTapGesture keyTap = new KeyTapGesture(gesture);
//					System.out.println(" KeyTap ID: " + keyTap.id()
//										+ " State: " + keyTap.state()
//										+ " Postion: " + keyTap.position()
//										+ " Direction: " + keyTap.direction());
//					
//				default: 
//					System.out.println("No Gesture Recognised");
//					
//					break;
//			
//			}
//			
//			
//			
//		}
			
	}
	
	public void sendRecordedData() {
		
		for(String arrayData: recordedData) {
			
			//establishCon.sendTheData(arrayData);
			System.out.println(arrayData);
		}
	}
	
	public boolean isSystemStatsFlag() {
		return systemStatsFlag;
	}

	public void setSystemStatsFlag(boolean systemStatsFlag) {
		this.systemStatsFlag = systemStatsFlag;
	}

	public boolean isLeapDataFlag() {
		return leapDataFlag;
	}

	public void setLeapDataFlag(boolean leapDataFlag) {
		this.leapDataFlag = leapDataFlag;
	}	
}
