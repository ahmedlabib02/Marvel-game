package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
Clip clip;
URL soundURL[]= new URL[60];
public Sound() {

	soundURL[1] = getClass().getResource("/sounds/I can do this all day.wav");
	soundURL[2] = getClass().getResource("/sounds/shield throw.wav");
	soundURL[3] = getClass().getResource("/sounds/ca_leader.wav");
	soundURL[4] = getClass().getResource("/sounds/8 bullets.wav");
	soundURL[5] = getClass().getResource("/sounds/try harder.wav");
	soundURL[6] = getClass().getResource("/sounds/firebreath.wav");
	soundURL[7] = getClass().getResource("/sounds/gr_leader.wav");
	soundURL[8] = getClass().getResource("/sounds/hulk smash.wav");
	soundURL[9] = getClass().getResource("/sounds/rage.wav");
	soundURL[10] = getClass().getResource("/sounds/hulk_leader.wav");
	soundURL[11] = getClass().getResource("/sounds/i am ironman.wav");
	soundURL[12] = getClass().getResource("/sounds/unibeam.wav");
	soundURL[13] = getClass().getResource("/sounds/web trap.wav");
	soundURL[14] = getClass().getResource("/sounds/sm_leader.wav");
	soundURL[15] = getClass().getResource("/sounds/bring me thanos.wav");
	soundURL[16] = getClass().getResource("/sounds/thor ability.wav");
	soundURL[17] = getClass().getResource("/sounds/thor_leader.wav");
	soundURL[18] = getClass().getResource("/sounds/head bite.wav");
	soundURL[19] = getClass().getResource("/sounds/we are venom.wav");
	soundURL[20] = getClass().getResource("/sounds/v_leader.wav");
	soundURL[21] = getClass().getResource("/sounds/symbiosis.wav");
	soundURL[22] = getClass().getResource("/sounds/dp_leader.wav");
	soundURL[23] = getClass().getResource("/sounds/ca_leader2.wav");
	soundURL[24] = getClass().getResource("/sounds/grenade sound.wav");
	soundURL[25]= getClass().getResource("/sounds/shield_up.wav");
	soundURL[26]= getClass().getResource("/sounds/storm.wav");
	soundURL[27] = getClass().getResource("/sounds/Game theme.wav");
	soundURL[28] = getClass().getResource("/sounds/Thor.wav");
	soundURL[29] = getClass().getResource("/sounds/treeSound.wav");
	}
public void setFile(int i) {
	try {
		AudioInputStream ais =AudioSystem.getAudioInputStream(soundURL[i]);
		clip = AudioSystem.getClip();
		clip.open(ais);
	}
	catch(UnsupportedAudioFileException e) {
		e.printStackTrace();
	}
	catch(Exception e) {
		
	}
}
public void play() {
	clip.start();
}
public void loop() {
	clip.loop(Clip.LOOP_CONTINUOUSLY);
}
public void stop() {
	clip.stop();
}


}
